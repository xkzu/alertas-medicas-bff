package com.alertasmedicas.app.bff;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.security.oauth2.resourceserver.jwt.issuer-uri=https://duocazurenative.b2clogin.com/duocazurenative.onmicrosoft.com/B2C_1_login_native/v2.0/",
		"spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://duocazurenative.b2clogin.com/duocazurenative.onmicrosoft.com/B2C_1_login_native/discovery/v2.0/keys",
		"cors.allowed-origins=http://localhost:4200"
})
class BffApplicationTests {

	@Test
	void contextLoads() {
		// Este test verifica que el contexto de Spring se carga correctamente
	}
}