package com.address.book.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("/contacts".equals(request.getRequestURI())) {
            String phraseParam = request.getParameter("phrase");
            if (phraseParam != null && (phraseParam.trim().isEmpty())) {
                sendEmptyArrayResponse(response);
                return false;
            }
        }
        return true;
    }

    private void sendEmptyArrayResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print("[]");
        writer.flush();
    }
}
