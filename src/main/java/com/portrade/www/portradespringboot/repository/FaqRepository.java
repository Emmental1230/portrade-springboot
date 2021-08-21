package com.portrade.www.portradespringboot.repository;

import com.portrade.www.portradespringboot.domain.faq.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {

}
