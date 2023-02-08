package java8.service;

import java8.entity.Country;
import java8.repository.CountryRepository;
import java8.repository.CountryRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class CountryServiceImpl implements CountryService{
    CountryRepository countryRepository = new CountryRepositoryImpl();

    @Override
    public String saveCountry(Country country) {
        return countryRepository.saveCountry(country);
    }

    @Override
    public String saveAllCountry(List<Country> countries) {
        return countryRepository.saveAllCountry(countries);
    }

    @Override
    public List<Country> getAllCountry() {
        return countryRepository.getAllCountry();
    }

    @Override
    public Optional<Country> findCountryById(Long countryId) {
        return countryRepository.findCountryById(countryId);
    }

    @Override
    public String deleteCountryById(Long id) {
        return countryRepository.deleteCountryById(id);
    }

    @Override
    public String deleteAllCountry() {
        return countryRepository.deleteAllCountry();
    }

    @Override
    public String updateCountry(Long countryId, Country country) {
        return countryRepository.updateCountry(countryId,country);
    }

    @Override
    public Optional<Country> findLongDescription() {
        return countryRepository.findLongDescription();
    }

    @Override
    public Integer getCountTheCountry(String country) {
        return countryRepository.getCountTheCountry(country);
    }
}
