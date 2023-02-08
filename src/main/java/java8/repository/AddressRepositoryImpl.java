package java8.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java8.config.HibernateConfig;
import java8.entity.Address;
import java8.entity.Country;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class AddressRepositoryImpl implements AddressRepository, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getManagerFactory();

    @Override
    public String saveAddress(Address address) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully saved....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String saveAllAddress(List<Address> addresses) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            for (Address address : addresses) {
                entityManager.persist(address);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("Successfully saved.....!");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignAddressToCountry(Long countryId, Long addressId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Country country = entityManager.createQuery("select c from Country c where c.id = :countryId", Country.class).
                    setParameter("countryId", countryId).getSingleResult();
            Address address = entityManager.createQuery("select a from Address  a where a.id = :addressId", Address.class).
                    setParameter("addressId", addressId).getSingleResult();
            List<Address> addressList = new ArrayList<>(Arrays.asList(address));
            address.setCountry_id(country);
            country.setAddresses(addressList);
            entityManager.merge(address);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully assigned to country....";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Address> getAllAddress() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Address> resultList = entityManager.createQuery("select a from Address a").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return resultList;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Address> getAddressById(Long addressId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Address address = entityManager.createQuery("select a from Address a where a.id = :addressId", Address.class).
                    setParameter("addressId", addressId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(address);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String deleteAddressById(Long addressId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Address address = entityManager.createQuery("select a from Address a where a.id = :addressId", Address.class).
                    setParameter("addressId", addressId).getSingleResult();
            address.getCountry_id().setAddresses(null);
            if (!(address.getProgrammer() == null)) {
                address.getProgrammer().setLocation(null);
            }
            entityManager.remove(address);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully deleted....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteAllAddresses() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Address> resultList = entityManager.createQuery("select a from Address a ", Address.class).getResultList();
            for (Address address : resultList) {
                address.setProgrammer(null);
                address.setCountry_id(null);
            }
             entityManager.createNativeQuery("truncate table addresses cascade").executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully deleted all addresses.....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String updateAddress(Long addressId, Address address) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Address oldAddress = entityManager.createQuery("select a from Address a where a.id = :addressId", Address.class).
                    setParameter("addressId", addressId).getSingleResult();
            oldAddress.setRegionName(address.getRegionName());
            oldAddress.setStreet(address.getStreet());
            oldAddress.setHomeNumber(address.getHomeNumber());
            oldAddress.setCountry_id(address.getCountry_id());
            oldAddress.setProgrammer(address.getProgrammer());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
