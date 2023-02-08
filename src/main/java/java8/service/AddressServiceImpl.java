package java8.service;

import java8.entity.Address;
import java8.repository.AddressRepository;
import java8.repository.AddressRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class AddressServiceImpl implements AddressService{
    AddressRepository addressRepository = new AddressRepositoryImpl();
    @Override
    public String saveAddress(Address address) {
        return addressRepository.saveAddress(address);
    }

    @Override
    public String saveAllAddress(List<Address> addresses) {
        return addressRepository.saveAllAddress(addresses);
    }

    @Override
    public String assignAddressToCountry(Long countryId, Long addressId) {
        return addressRepository.assignAddressToCountry(countryId,addressId);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.getAllAddress();
    }

    @Override
    public Optional<Address> getAddressById(Long addressId) {
        return addressRepository.getAddressById(addressId);
    }

    @Override
    public String deleteAddressById(Long addressId) {
        return addressRepository.deleteAddressById(addressId);
    }

    @Override
    public String deleteAllAddresses() {
        return addressRepository.deleteAllAddresses();
    }

    @Override
    public String updateAddress(Long addressId, Address address) {
        return addressRepository.updateAddress(addressId,address);
    }
}
