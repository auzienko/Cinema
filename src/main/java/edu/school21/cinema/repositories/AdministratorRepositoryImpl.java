package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Administrator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AdministratorRepositoryImpl implements AdministratorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static String SQL_FIND_BY_EMAIL = "SELECT a FROM Administrator a WHERE a.email= :email";

    @Override
    public Optional<Administrator> findByEmail(String email) {
        List<Administrator> query = entityManager.createQuery(SQL_FIND_BY_EMAIL, Administrator.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList();
        return query.size() == 0 ? Optional.empty() : Optional.of(query.get(0));
    }

    @Override
    public Optional<Administrator> findById(Long Id) {
        return Optional.empty();
    }

    @Override
    public List<Administrator> findAll() {
        return null;
    }

    @Override
    public void save(Administrator entity) {
        entityManager.merge(entity);
    }

    @Override
    public void update(Administrator entity) {
        if (entity.getId() != null) {
            Administrator administrator = entityManager.find(Administrator.class, entity.getId());
            if (administrator != null) {
                entityManager.merge(entity);
            }
        }
    }

    @Override
    public void delete(Long id) {

    }
}
