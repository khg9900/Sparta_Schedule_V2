package com.example.schedulev2.filter;

import com.example.schedulev2.common.Const;
import com.example.schedulev2.exception.LoginRequiredException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    // 회원가입, 로그인 요청은 인증 처리에서 제외
    private static final String[] WHITE_LIST = {"/users/signup", "/users/login"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않은 사용자인 경우
            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                throw new LoginRequiredException();
            }

        }

        chain.doFilter(request, response);
    }

    // 회원가입, 로그인 요청 URL 체크
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
