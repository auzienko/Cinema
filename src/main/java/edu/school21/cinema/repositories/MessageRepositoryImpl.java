package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryImpl implements MessageRepository{
    private static final String SQL_FIND_ALL = "SELECT a FROM Message a";
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Message.class, id));
    }

    @Override
    public List<Message> findAll() {
        List<Message> ret = entityManager.createQuery(SQL_FIND_ALL, Message.class).getResultList();
        return ret;
    }

    @Override
    @Transactional
    public void save(Message entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Message entity) {
        if (entity.getId() != null) {
            Message message = entityManager.find(Message.class, entity.getId());
            if (message != null) {
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public void delete(Long id) {
        Message message = entityManager.find(Message.class, id);
        if (message != null) {
            entityManager.remove(message);
        }
    }


    @Override
    public List<Message> getChatHistory(Long filmId) {
        List<Message> msgHistory = entityManager
                .createQuery("select a from Message where a.movie.id = :fId  order by a.id desc", Message.class)
                .setParameter("fId", filmId)
                .setMaxResults(20)
                .getResultList();
        Collections.reverse(msgHistory);
        return msgHistory;
    }
}
