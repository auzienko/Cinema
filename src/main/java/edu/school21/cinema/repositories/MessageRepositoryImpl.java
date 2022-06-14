package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import edu.school21.cinema.models.Message;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryImpl implements MessageRepository{
    private static final String SQL_FIND_ALL = "SELECT a FROM Message a";
    public static final String FIND_ALL_BY_ID = "SELECT a FROM Message a WHERE a.administrator.id= :administratorId";
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Message.class, id));
    }

    @Override
    public List<Message> findAll() {
        return null;
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
    public List<Message> getChatHistory(Long film_id) {
        List<Message> msgHistory = null;
//        List<Message> msgHistory = entityManager
//                .createQuery("select a from Message where a.movie.id = :f_id  order by a.id desc", Message.class)
//                .getParameterValue("f_id", film_id)
//                .setMaxResults(20)
//                .getResultList();
//        Collections.reverse(msgHistory);
        return msgHistory;
    }
}
