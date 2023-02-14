package mca.filesmanagement.bpm.controller;

import java.util.Objects;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Controlador padre para el resto de contraladores REST.
 *
 * @author agat
 */
public abstract class AbstractController {

	/**
	 * Devuelve el username asociado al contexto de sesión.
	 * @return Username.
	 */
	protected final String getUserName() {
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (Objects.nonNull(authentication)
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		return currentUserName;
	}
}
