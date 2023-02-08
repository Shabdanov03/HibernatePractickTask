package java8.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java8.config.HibernateConfig;
import java8.entity.Programmer;
import java8.entity.Project;
import java8.enums.Status;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class ProjectRepositoryImpl implements ProjectRepository,AutoCloseable{
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getManagerFactory();
    @Override
    public String saveProject(Project project) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully saved...!";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String saveAllProject(List<Project> projects) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            for (Project project : projects) {
                entityManager.persist(project);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully saved...!";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Project> resultList = entityManager.createQuery("select p from Project p", Project.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return resultList;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Project> findProjectById(Long projectId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project project = entityManager.createQuery("select p from Project p where p.id = :projectId", Project.class).
                    setParameter("projectId", projectId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(project);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String deleteProjectById(Long projectId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project project = entityManager.createQuery("select p from Project p where p.id = :pro", Project.class).
                    setParameter("pro", projectId).getSingleResult();
            project.getProgrammers().forEach(x->x.setProjects(null));
            project.getProgrammers().forEach(x->x.getLocation().setProgrammer(null));
            entityManager.remove(project);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully deleted....!";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteAllProjects() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("truncate table projects cascade").executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully deleted all projects.....";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String updateProject(Long projectId, Project project) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project oldProject = entityManager.createQuery("select p from Project p where p.id = :projectId", Project.class).
                    setParameter("projectId", projectId).getSingleResult();
            oldProject.setProjectName(project.getProjectName());
            oldProject.setDescription(project.getDescription());
            oldProject.setDateOfStart(project.getDateOfStart());
            oldProject.setDateOfFinish(project.getDateOfFinish());
            oldProject.setPrice(project.getPrice());
            oldProject.setProgrammers(project.getProgrammers());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated....!";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignProjectToProgrammer(Long projectId, Long programmerId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project project = entityManager.createQuery("select p from Project p where p.id = :projectId", Project.class).
                    setParameter("projectId", projectId).getSingleResult();
            Programmer programmer = entityManager.createQuery("select p from Programmer  p where p.id = :programmerId", Programmer.class).
                    setParameter("programmerId", programmerId).getSingleResult();
            for (Programmer projectProgrammer : project.getProgrammers()) {
                if (projectProgrammer.getStatus().equals(programmer.getStatus()) && projectProgrammer.getStatus().equals(Status.OWNER)){
                    return "Owner already exist ....!";
                }
            }
            List<Programmer> programmerList = new ArrayList<>(Arrays.asList(programmer));
            List<Project> projectList = new ArrayList<>(Arrays.asList(project));
            programmer.setProjects(projectList);
            project.setProgrammers(programmerList);
            entityManager.merge(project);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully assigned at programmer.....!";
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Project> findExpensiveProject() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project project = entityManager.createQuery("select p from Project p order by p.price desc limit 1", Project.class).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(project);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Project> findShorterTimeWriterProject() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project singleResult =(Project) entityManager.createNativeQuery("select *,concat(extract(year from date_of_finish) -" +
                    " extract(year from date_of_start)) as times from projects order by times limit 1", Project.class).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(singleResult);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void close() throws Exception {
   entityManagerFactory.close();
    }
}
