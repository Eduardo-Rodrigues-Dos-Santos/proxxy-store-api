package com.proxxy.auth.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CustomToken implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {

        Authentication principal = context.getPrincipal();
        List<String> authorities = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        Set<String> scopes = context.getAuthorizedScopes();

        if (principal.getPrincipal() instanceof AuthUserDetails authUserDetails) {

            HashMap<String, Object> claims = new HashMap<>();

            claims.put("user_id", authUserDetails.getId());
            claims.put("full_name", authUserDetails.getFullName());
            claims.put("scope", scopes);
            claims.put("authorities", authorities);
            context.getClaims().claims(map -> map.putAll(claims));
        }
    }
}
