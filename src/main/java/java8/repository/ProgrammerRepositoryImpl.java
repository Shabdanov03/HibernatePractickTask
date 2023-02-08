package java8.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java8.config.HibernateConfig;
import java8.entity.Address;
import java8.entity.Programmer;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class ProgrammerRepositoryImpl implements ProgrammerRepository, AutoCloseable {

    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getManagerFactory();

    @Override
    public String saveProgrammer(Programmer programmer) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(programmer);
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String saveAllProgrammer(List<Programmer> programmers) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            for (Programmer programmer : programmers) {
                entityManager.persist(programmer);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
             return "Successfully saved....";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignProgrammerToAddress(Long programmerId, Long addressId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.createQuery("select p from Programmer p where p.id = :programmerId", Programmer.class).
                    setParameter("programmerId", programmerId).getSingleResult();
            Address address = entityManager.createQuery("select a from Address a where a.id = :add", Address.class).
                    setParameter("add", addressId).getSingleResult();
            programmer.setLocation(address);
            address.setProgrammer(programmer);
            entityManager.merge(programmer);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully assigned to country....!";

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Programmer> getAllProgrammer() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Programmer> programmerList = entityManager.createQuery("select  p from Programmer p ", Programmer.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return programmerList;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String addConstraintToProgrammerEmail() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("alter  table programmers add constraint email unique(email)").executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            return  "Successfully added constraints....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Programmer> findProgrammerById(Long programmerId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.createQuery("select p from Programmer p where p.id = :programmerId", Programmer.class).
                    setParameter("programmerId", programmerId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(programmer);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String deleteProgrammerById(Long programmerId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.createQuery("select p from Programmer p where p.id = :programmerId", Programmer.class).
                    setParameter("programmerId", programmerId).getSingleResult();
           entityManager.remove(programmer);
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteAllProgrammer() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("truncate table programmers").executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
          return "Successfully deleted....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String updateProgrammer(Long programmerId, Programmer programmer) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer oldProgrammer = entityManager.createQuery("select p from Programmer p where p.id = :programmerId", Programmer.class).
                    setParameter("programmerId", programmerId).getSingleResult();
            oldProgrammer.setFullName(programmer.getFullName());
            oldProgrammer.setEmail(programmer.getEmail());
            oldProgrammer.setDateOfBirth(programmer.getDateOfBirth());
            oldProgrammer.setStatus(programmer.getStatus());
            oldProgrammer.setLocation(programmer.getLocation());
            oldProgrammer.setProjects(programmer.getProjects());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Programmer> getProgrammerByCountryId(Long countryId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.createQuery("select p from Programmer p join Address a on a.id = p.id join Country c on c.id = a.id" +
                    " where c.id = :countryId", Programmer.class).setParameter("countryId", countryId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
             return Optional.ofNullable(programmer);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Programmer> getSmallProgrammer() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.createQuery("select fullName,min(dateOfBirth) as age from Programmer", Programmer.class).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(programmer);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Programmer> getOldProgrammer() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.createQuery("select fullName, min(dateOfBirth) as age from Programmer ", Programmer.class).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
           return Optional.ofNullable(programmer);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
