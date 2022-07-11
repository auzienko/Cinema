package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserAuthHistory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;;
import java.util.List;
import java.util.Optional;

@Repository
public class UserAuthHistoryRepositoryImpl implements UserAuthHistoryRepository {
    private static String SQL_FIND_ALL = "SELECT a FROM UserAuthHistory a";
    private static String SQL_FIND_BY_USER_ID= "SELECT a FROM UserAuthHistory a WHERE a.administrator.id = :userId";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserAuthHistory> findById(Long id) {
        UserAuthHistory userAuthHistory = entityManager.find(UserAuthHistory.class, id);
        return userAuthHistory == null ? Optional.empty() : Optional.of(userAuthHistory);
    }

    @Override
    public List<UserAuthHistory> findAll() {
        return entityManager.createQuery(SQL_FIND_ALL, UserAuthHistory.class).getResultList();
    }

    @Override
    @Transactional
    public void save(UserAuthHistory entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(UserAuthHistory entity) {
        if (entity.getId() != null) {
            UserAuthHistory userAuthHistory = entityManager.find(UserAuthHistory.class, entity.getId());
            if (userAuthHistory != null) {
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public void delete(Long id) {
        UserAuthHistory userAuthHistory = entityManager.find(UserAuthHistory.class, id);
        if (userAuthHistory != null) {
            entityManager.remove(userAuthHistory);
        }
    }

    @Override
    public List<UserAuthHistory> findAllByUserId(Long id) {
        return entityManager.createQuery(SQL_FIND_BY_USER_ID, UserAuthHistory.class).setParameter("userId", id).getResultList();
    }
}
