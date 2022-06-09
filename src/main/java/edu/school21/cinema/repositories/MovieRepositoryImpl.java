package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    private static String SQL_FIND_ALL = "SELECT a FROM Movie a";
    private static String SQL_FIND_BY_ADMINID = "SELECT a FROM Movie a WHERE a.administrator.id= :administratorId";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Movie> findById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        return movie == null ? Optional.empty() : Optional.of(movie);
    }

    @Override
    public List<Movie> findAll() {
        return entityManager.createQuery(SQL_FIND_ALL, Movie.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Movie entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Movie entity) {
        if (entity.getId() != null) {
            Movie movie = entityManager.find(Movie.class, entity.getId());
            if (movie != null) {
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public void delete(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }

    @Override
    public List<Movie> findAllByAdministratorId(Long id) {
        return entityManager.createQuery(SQL_FIND_BY_ADMINID, Movie.class)
                .setParameter("administratorId", id)
                .getResultList();
    }
}
