package com.example.NaTour21.Report.Repository;

import com.example.NaTour21.Report.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(value = "SELECT * FROM report WHERE sender = ?1 AND post_id = ?2", nativeQuery = true)
    Report findByUsernameAndPost(String username, Long postId);

    @Query(value = "SELECT r.id,r.sender,r.title AS report_title,r.description,p.username AS post_owner,p.title,r.response_message AS post_title FROM report AS r INNER JOIN post AS p ON r.post_id=p.id AND (p.username=?1 OR r.sender=?1)", nativeQuery = true)
    List<?> getReports(String username);

    List<Report> getReportsByPostId(Long postId);

}
