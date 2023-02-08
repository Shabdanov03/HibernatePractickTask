package java8.service;

import java8.entity.Country;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public interface CountryService {
    String saveCountry(Country country);
    String saveAllCountry(List<Country> countries);
    List<Country> getAllCountry();
    Optional<Country> findCountryById (Long countryId);
    String deleteCountryById(Long id);
    String deleteAllCountry();
    String updateCountry(Long countryId,Country country);
    Optional<Country> findLongDescription();
    Integer getCountTheCountry(String country);
}
