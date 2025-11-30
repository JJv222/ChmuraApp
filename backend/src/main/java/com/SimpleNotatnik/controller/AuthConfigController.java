package com.SimpleNotatnik.controller;

import com.SimpleNotatnik.dto.AuthConfigDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth-config")
public class AuthConfigController {

   @Value("${cognito.issuer-uri}")
   private String issuerUri;

   @Value("${cognito.client-id}")
   private String clientId;

   @Value("${cognito.redirect.url}")
   private String redirectUrl;

   @Value("${cognito.logout.url}")
   private String logoutUrl;

   @Value("${cognito.scope}")
   private String scope;

   @GetMapping
   public AuthConfigDto getAuthConfig() {
      AuthConfigDto dto = new AuthConfigDto();
      dto.setAuthority(issuerUri);
      dto.setClientId(clientId);
      dto.setRedirectUrl(redirectUrl);
      dto.setLogoutUrl(logoutUrl);
      dto.setScope(scope);
      dto.setResponseType("code");
      return dto;
   }
}
