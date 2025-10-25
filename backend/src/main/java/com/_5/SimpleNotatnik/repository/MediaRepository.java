package com._5.SimpleNotatnik.repository;

import com._5.SimpleNotatnik.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}

