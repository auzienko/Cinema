package edu.school21.cinema.services;

import edu.school21.cinema.models.Poster;
import javafx.geometry.Pos;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PosterService extends BaseService<Poster> {
    List<Poster> getAllAvatars(Long id);
    Optional<Poster> findByUUID(UUID UUID);
}

