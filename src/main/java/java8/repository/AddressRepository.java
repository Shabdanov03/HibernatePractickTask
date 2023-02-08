package java8.repository;

import java8.entity.Address;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public interface AddressRepository {

    String saveAddress(Address address);

    String saveAllAddress(List<Address> addresses);

    String assignAddressToCountry(Long countryId, Long addressId);
    List<Address> getAllAddress();
    Optional<Address> getAddressById(Long addressId);
    String deleteAddressById(Long addressId);
    String deleteAllAddresses();
    String updateAddress(Long addressId,Address address);

}
