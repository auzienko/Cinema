package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class SessionRepositoryImpl implements SessionRepository {
    private static String SQL_FIND_ALL = "SELECT a FROM Session a";
    private static String SQL_FIND_BY_TITLE = "SELECT a FROM Session a WHERE lower(a.movie.title) like lower(concat('%',:title, '%'))";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Session> findById(Long id) {
        Session session = entityManager.find(Session.class, id);
        return session == null ? Optional.empty() : Optional.of(session);
    }

    @Override
    public List<Session> findAll() {
        return entityManager.createQuery(SQL_FIND_ALL, Session.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Session entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Session entity) {
        if (entity.getId() != null) {
            Session session = entityManager.find(Session.class, entity.getId());
            if (session != null) {
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public void delete(Long id) {
        Session session = entityManager.find(Session.class, id);
        if (session != null) {
            entityManager.remove(session);
        }
    }

    @Override
    public List<Session> getByFilmTitle(String title) {
        return null;
    }

    @Override
    public List<Session> findByTitle(String title) {
        return entityManager.createQuery(SQL_FIND_BY_TITLE, Session.class)
                .setParameter("title", title)
                .getResultList();
    }
}
