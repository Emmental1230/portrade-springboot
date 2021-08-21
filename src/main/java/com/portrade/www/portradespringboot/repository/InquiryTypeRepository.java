package com.portrade.www.portradespringboot.repository;

import com.portrade.www.portradespringboot.domain.inquiry.InquiryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryTypeRepository extends JpaRepository<InquiryType, Long> {

    @Query(value = "SELECT iqt FROM InquiryType iqt where iqt.desc = ?1")
    InquiryType findByDesc(String desc);
}
