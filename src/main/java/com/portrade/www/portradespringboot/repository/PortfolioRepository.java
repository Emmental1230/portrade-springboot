package com.portrade.www.portradespringboot.repository;

import com.portrade.www.portradespringboot.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findAllByOrderByIdDesc();
}
