package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserControllerAdvice {

	@Autowired
	private UserService userService;

	@ModelAttribute("currentUser")
	public CurrentUser getCurrentUser(Authentication authentication) {

		return (authentication == null) ? null
				: new CurrentUser(
						userService.getUserByEmail(((UserDetails) authentication.getPrincipal()).getUsername()).get());
	}

}