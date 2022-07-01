package edu.school21.cinema.services;

import edu.school21.cinema.models.UserAuthHistory;
import edu.school21.cinema.repositories.UserAuthHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserAuthHistoryServiceImpl implements UserAuthHistoryService {
    private final UserAuthHistoryRepository userAuthHistoryRepository;


    @Override
    public void save(UserAuthHistory entity) {
        userAuthHistoryRepository.save(entity);
    }

    @Override
    public List<UserAuthHistory> getUserAuthHistory(Long id) {
        return userAuthHistoryRepository.findAllByUserId(id);
    }
}
