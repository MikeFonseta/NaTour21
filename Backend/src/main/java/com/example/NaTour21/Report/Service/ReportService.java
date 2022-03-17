package com.example.NaTour21.Report.Service;

import com.example.NaTour21.Post.Repository.PostRepository;
import com.example.NaTour21.Report.Entity.Report;
import com.example.NaTour21.Report.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;
    private final PostRepository postRepository;

    public Report saveReport(Report report) throws Exception {
        if(postRepository.findById(report.getPostId()).isPresent())
        {
            if (reportRepository.findByUsernameAndPost(report.getSender(), report.getPostId()) != null) {
                throw new Exception("Hai già segnalato questo post");
            } else {
                return reportRepository.save(report);
            }
        }
        throw new Exception("Post non trovato");
    }

    public List<?> getReports(String username) {
        return reportRepository.getReports(username);
    }

    public List<Report> getReportsByPostId(Long postId){
        return reportRepository.getReportsByPostId(postId);
    }

    public Report updateResponse(Report report, String responseMessage) {
        report.setResponseMessage(responseMessage);
        return reportRepository.save(report);
    }

    public Report getReport(Long report_id) {
        Optional<Report> report =  reportRepository.findById(report_id);
        if(report.isPresent())
        {
            return report.get();
        }
        return null;
    }
}
