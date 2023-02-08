package java8.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java8.config.HibernateConfig;
import java8.entity.Country;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Optional;

/**
 * Shabdanov Ilim
 **/
public class CountryRepositoryImpl implements CountryRepository, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getManagerFactory();

    @Override
    public String saveCountry(Country country) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(country);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully saved...!";

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String saveAllCountry(List<Country> countries) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            for (Country country : countries) {
                entityManager.persist(country);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully saved...!";

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Country> getAllCountry() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Country> resultList = entityManager.createQuery("select c from Country c", Country.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return resultList;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Country> findCountryById(Long countryId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Country country = entityManager.createQuery("select c from Country c where c.id = :countryId", Country.class).
                    setParameter("countryId", countryId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(country);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String deleteCountryById(Long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Country country = entityManager.createQuery("select c from Country c where c.id = :id", Country.class).
                    setParameter("id", id).getSingleResult();
            country.getAddresses().forEach(x->x.setCountry_id(null));
            country.getAddresses().forEach(x->x.getProgrammer().setLocation(null));
            entityManager.remove(country);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully deleted....!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteAllCountry() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Country> resultList = entityManager.createQuery("select c from Country c", Country.class).getResultList();
            for (Country country : resultList) {
                country.getAddresses().stream().forEach(x->x.setProgrammer(null));
            }
            entityManager.createNativeQuery("truncate table countries cascade").executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("Successfully deleted all countries...!");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String updateCountry(Long countryId, Country country) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Country oldCountry = entityManager.createQuery("select c from Country c where c.id = :countryId", Country.class).
                    setParameter("id", countryId).getSingleResult();
            oldCountry.setCountry(country.getCountry());
            oldCountry.setDescription(country.getDescription());
            oldCountry.setAddresses(country.getAddresses());
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Successfully updated...!";
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Country> findLongDescription() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Country country =(Country) entityManager.createNativeQuery("select * from countries  order by length(description) desc  limit 1", Country.class).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(country);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Integer getCountTheCountry(String country) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Country> list = entityManager.createQuery("select c from Country  c where c.country = :country", Country.class).setParameter("country", country).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return list.size();
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
