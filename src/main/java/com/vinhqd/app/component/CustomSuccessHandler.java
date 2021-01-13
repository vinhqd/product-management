package com.vinhqd.app.component;

import com.vinhqd.app.constants.SystemConstants;
import com.vinhqd.app.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private SecurityUtils securityUtils;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String urlTarget = determineTargetUrl(authentication);
        if (response.isCommitted()) return;
        redirectStrategy.sendRedirect(request, response, urlTarget);
    }

    private String determineTargetUrl(Authentication auth) {
        String url = "";
        List<String> roles = securityUtils.getAuthorities(auth);
        if (isAdmin(roles)) {
            url = "/admin";
        } else if (isUser(roles)) {
            url = "/";
        }
        return url;
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains(SystemConstants.ADMIN_ROLE);
    }

    private boolean isUser(List<String> roles) {
        return roles.contains(SystemConstants.USER_ROLE);
    }


}
