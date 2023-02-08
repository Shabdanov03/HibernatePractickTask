package java8.repository;

import java8.entity.Country;
import java8.entity.Project;
import org.postgresql.copy.CopyOut;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public interface ProjectRepository {

    String saveProject(Project project);
    String saveAllProject(List<Project>projects);
    List<Project> getAllProjects();
    Optional<Project> findProjectById(Long projectId);
    String deleteProjectById(Long projectId);
    String deleteAllProjects();
    String updateProject(Long projectId,Project project);
    String assignProjectToProgrammer(Long projectId,Long programmerId);
    Optional<Project> findExpensiveProject();
    Optional<Project> findShorterTimeWriterProject();


}
