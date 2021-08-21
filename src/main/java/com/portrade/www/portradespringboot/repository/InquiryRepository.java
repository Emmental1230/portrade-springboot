package com.portrade.www.portradespringboot.repository;

import com.portrade.www.portradespringboot.domain.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry,Long> {

    @Query(value = "SELECT iq FROM Inquiry iq join fetch iq.category ")
    List<Inquiry> findAll();
}
