package hello;

import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

	@Override
	public boolean canAccessUser(CurrentUser currentUser, Long userId) {
		return currentUser != null && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
	}

}
