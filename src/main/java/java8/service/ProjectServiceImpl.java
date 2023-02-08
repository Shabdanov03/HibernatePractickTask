package java8.service;
import java8.entity.Project;
import java8.repository.ProjectRepository;
import java8.repository.ProjectRepositoryImpl;
import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class ProjectServiceImpl implements ProjectService{
    ProjectRepository projectRepository = new ProjectRepositoryImpl();
    @Override
    public String saveProject(Project project) {
        return projectRepository.saveProject(project);
    }

    @Override
    public String saveAllProject(List<Project> projects) {
        return projectRepository.saveAllProject(projects);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    @Override
    public Optional<Project> findProjectById(Long projectId) {
        return projectRepository.findProjectById(projectId);
    }

    @Override
    public String deleteProjectById(Long projectId) {
        return projectRepository.deleteProjectById(projectId);
    }

    @Override
    public String deleteAllProjects() {
        return  projectRepository.deleteAllProjects();
    }

    @Override
    public String updateProject(Long projectId, Project project) {
        return projectRepository.updateProject(projectId,project);
    }

    @Override
    public String assignProjectToProgrammer(Long projectId, Long programmerId) {
        return projectRepository.assignProjectToProgrammer(projectId,programmerId);
    }

    @Override
    public Optional<Project> findExpensiveProject() {
        return projectRepository.findExpensiveProject();
    }

    @Override
    public Optional<Project> findShorterTimeWriterProject() {
        return projectRepository.findShorterTimeWriterProject();
    }
}
