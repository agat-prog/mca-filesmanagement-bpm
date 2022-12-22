package mca.filesmanagement.bpm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BpmSecurityConfig {
	
	@Value("${mca.filesmanagement.bpm.oauth2.clientid}")
	private String oauthClientId;
	
	@Value("${mca.filesmanagement.bpm.oauth2.clientsecret}")
	private String oauthSecret;
	
	@Value("${mca.filesmanagement.bpm.oauth2.checktokenendpointurl}")
	private String checkTokenEndPoint;
	
	@Bean
	public ResourceServerTokenServices tokenService() {
		RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setClientId(this.oauthClientId);
		tokenService.setClientSecret(this.oauthSecret);
		tokenService.setCheckTokenEndpointUrl(this.checkTokenEndPoint);
		return tokenService;
	}
}
