package edu.school21.cinema.services;

import edu.school21.cinema.models.Poster;


import java.util.List;

public interface PosterService extends BaseService<Poster> {
    List<Poster> getAllAvatars(Long id);
}

