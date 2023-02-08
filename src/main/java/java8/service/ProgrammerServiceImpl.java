package java8.service;

import java8.entity.Programmer;
import java8.repository.ProgrammerRepository;
import java8.repository.ProgrammerRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class ProgrammerServiceImpl implements ProgrammerService{
    ProgrammerRepository programmerRepository = new ProgrammerRepositoryImpl();

    @Override
    public String saveProgrammer(Programmer programmer) {
        return programmerRepository.saveProgrammer(programmer);
    }

    @Override
    public String saveAllProgrammer(List<Programmer> programmers) {
        return programmerRepository.saveAllProgrammer(programmers);
    }

    @Override
    public String assignProgrammerToAddress(Long programmerId, Long addressId) {
        return programmerRepository.assignProgrammerToAddress(programmerId,addressId);
    }

    @Override
    public List<Programmer> getAllProgrammer() {
        return programmerRepository.getAllProgrammer();
    }

    @Override
    public String addConstraintToProgrammerEmail() {
        return programmerRepository.addConstraintToProgrammerEmail();
    }

    @Override
    public Optional<Programmer> findProgrammerById(Long programmerId) {
        return programmerRepository.findProgrammerById(programmerId);
    }

    @Override
    public String deleteProgrammerById(Long programmerId) {
        return programmerRepository.deleteProgrammerById(programmerId);
    }

    @Override
    public String deleteAllProgrammer() {
        return programmerRepository.deleteAllProgrammer();
    }

    @Override
    public String updateProgrammer(Long programmerId, Programmer programmer) {
        return programmerRepository.updateProgrammer(programmerId,programmer);
    }

    @Override
    public Optional<Programmer> getProgrammerByCountryId(Long countryId) {
        return programmerRepository.getProgrammerByCountryId(countryId);
    }

    @Override
    public Optional<Programmer> getSmallProgrammer() {
        return programmerRepository.getSmallProgrammer();
    }

    @Override
    public Optional<Programmer> getOldProgrammer() {
        return programmerRepository.getOldProgrammer();
    }
}
