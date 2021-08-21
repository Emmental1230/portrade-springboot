package com.portrade.www.portradespringboot.repository;

import com.portrade.www.portradespringboot.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
