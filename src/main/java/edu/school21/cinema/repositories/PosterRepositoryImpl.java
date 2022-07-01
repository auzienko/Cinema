package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Poster;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PosterRepositoryImpl implements PosterRepository {
    private static String SQL_FIND_ALL = "SELECT a FROM Poster a";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Poster> findById(Long id) {
        Poster poster = entityManager.find(Poster.class, id);
        return poster == null ? Optional.empty() : Optional.of(poster);
    }

    @Override
    public List<Poster> findAll() {
        return entityManager.createQuery(SQL_FIND_ALL, Poster.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Poster entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Poster entity) {
        if (entity.getId() != null) {
            Poster poster = entityManager.find(Poster.class, entity.getId());
            if (poster != null) {
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public void delete(Long id) {
        Poster poster = entityManager.find(Poster.class, id);
        if (poster != null) {
            entityManager.remove(poster);
        }
    }

    @Override
    public List<Poster> getAllUserAvatars(Long id) {
        return entityManager.createQuery(SQL_FIND_ALL + " where a.administrator.id = :id " +
                "and a.type = 2", Poster.class).setParameter("id", id).getResultList();
    }
}
