package liga.warehouse.warehousecontrol.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Set<String> role = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (role.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        } else if (role.contains("ROLE_USER")) {
            response.sendRedirect("/user");
        } else if (role.contains("ROLE_OWNER")) {
            response.sendRedirect("/owner");
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}