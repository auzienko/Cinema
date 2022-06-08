package edu.school21.cinema.repositories;

import edu.school21.cinema.models.MovieHall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieHallRepositoryImpl implements MovieHallRepository {
    private static String SQL_FIND_BY_ADMINID = "SELECT a FROM MovieHall a WHERE a.administrator.id= :administratorId";

    private static String SQL_FIND_BY_SERIAL_NUMBER = "SELECT a FROM MovieHall a WHERE a.serialNumber = :serialNumber";
    private static String SQL_FIND_ALL = "SELECT a FROM MovieHall a";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<MovieHall> findById(Long id) {
        MovieHall movieHall = entityManager.find(MovieHall.class, id);
        return movieHall == null ? Optional.empty() : Optional.of(movieHall);
    }

    @Override
    public List<MovieHall> findAll() {
        return entityManager.createQuery(SQL_FIND_ALL, MovieHall.class).getResultList();
    }

    @Override
    @Transactional
    public void save(MovieHall entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(MovieHall entity) {
        if (entity.getId() != null) {
            MovieHall movieHall = entityManager.find(MovieHall.class, entity.getId());
            if (movieHall != null) {
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public void delete(Long id) {
        MovieHall movieHall = entityManager.find(MovieHall.class, id);
        if (movieHall != null) {
            entityManager.remove(movieHall);
        }
    }

    @Override
    public List<MovieHall> findAllByAdministratorId(Long id) {
        return entityManager.createQuery(SQL_FIND_BY_ADMINID, MovieHall.class)
                .setParameter("administratorId", id)
                .getResultList();
    }

    @Override
    public Optional<MovieHall> findBySerialNumber(Integer serialNumber) {
        List<MovieHall> movieHallList = entityManager.createQuery(SQL_FIND_BY_SERIAL_NUMBER, MovieHall.class)
                .setParameter("serialNumber", serialNumber)
                .setMaxResults(1)
                .getResultList();
        return movieHallList.size() == 0 ? Optional.empty() : Optional.of(movieHallList.get(0));
    }
}
