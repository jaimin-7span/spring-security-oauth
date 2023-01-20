package com.baeldung.resource.spring.claim;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class CustomClaimVerifier implements OAuth2TokenValidator<Jwt>
{
	@Override
	public OAuth2TokenValidatorResult validate(Jwt jwt)
	{
		OAuth2Error error = new OAuth2Error("invalid_token", "Invalid preferred_username", null);

		String preferredUsername = jwt.getClaimAsString("preferred_username");;
		String[] extractedAry = preferredUsername.split("@");
		String domain = extractedAry[1];

		if (domain.equalsIgnoreCase("test.com")) {
			return OAuth2TokenValidatorResult.success();
		}
		return OAuth2TokenValidatorResult.failure(error);
	}
}
