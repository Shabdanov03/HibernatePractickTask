package java8.service;

import java8.entity.Programmer;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public interface ProgrammerService {
    String saveProgrammer(Programmer programmer);
    String saveAllProgrammer(List<Programmer> programmers);
    String assignProgrammerToAddress(Long programmerId,Long addressId);
    List<Programmer> getAllProgrammer();
    String addConstraintToProgrammerEmail();
    Optional<Programmer> findProgrammerById(Long programmerId);
    String deleteProgrammerById(Long programmerId);
    String deleteAllProgrammer();
    String updateProgrammer(Long programmerId,Programmer programmer);
    Optional<Programmer> getProgrammerByCountryId(Long countryId);
    Optional<Programmer> getSmallProgrammer();
    Optional<Programmer> getOldProgrammer();
}
