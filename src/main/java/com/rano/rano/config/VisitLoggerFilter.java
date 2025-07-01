package com.rano.rano.config;

import com.rano.rano.entity.VisitLog;
import com.rano.rano.repository.VisitLogRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class VisitLoggerFilter implements Filter {

    private final VisitLogRepository visitLogRepository;

    public VisitLoggerFilter(VisitLogRepository visitLogRepository) {
        this.visitLogRepository = visitLogRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String ipAddress = request.getRemoteAddr();
        System.out.println("IP: " + ipAddress);

        // Если прокси передаёт IP через заголовок
        if (request instanceof HttpServletRequest) {
            String forwarded = ((HttpServletRequest) request).getHeader("X-Forwarded-For");
            if (forwarded != null && !forwarded.isEmpty()) {
                ipAddress = forwarded.split(",")[0]; // первый IP — настоящий клиент
            }
        }

        LocalDate date = LocalDate.now();
        boolean exists = visitLogRepository.existsByIpAddressAndVisitDate(ipAddress, date);
        if (!exists) {
            VisitLog visit = new VisitLog();
            visit.setIpAddress(ipAddress);
            visit.setVisitDate(date);
            visitLogRepository.save(visit);
        }

        chain.doFilter(request, response);
    }
}
