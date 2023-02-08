package java8.service;

import java8.entity.Project;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public interface ProjectService {
    String saveProject(Project project);
    String saveAllProject(List<Project> projects);
    List<Project> getAllProjects();
    Optional<Project> findProjectById(Long projectId);
    String deleteProjectById(Long projectId);
    String deleteAllProjects();
    String updateProject(Long projectId,Project project);
    String assignProjectToProgrammer(Long projectId,Long programmerId);
    Optional<Project> findExpensiveProject();
    Optional<Project> findShorterTimeWriterProject();
}
