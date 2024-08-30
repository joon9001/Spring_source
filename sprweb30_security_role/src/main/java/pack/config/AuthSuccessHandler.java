package pack.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	    private RequestCache requestCache = new HttpSessionRequestCache();

	    public AuthSuccessHandler() {
	          super.setRequestCache(requestCache);
	    }

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

	         HttpSession session=request.getSession();
	         session.setMaxInactiveInterval(60*20);   // 초 단위로 설정

	         SavedRequest cashed=requestCache.getRequest(request, response);

	          if(cashed==null) {
	                  RequestDispatcher rd=request.getRequestDispatcher("/user/login_success");
	                  rd.forward(request, response);
	          } else {
                  super.onAuthenticationSuccess(request, response, authentication);
	        }
	    }
	}
