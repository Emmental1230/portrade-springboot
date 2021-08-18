package com.portrade.www.portradespringboot.repository;

import com.portrade.www.portradespringboot.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByPortfolioId(Long portfolioId);
}
