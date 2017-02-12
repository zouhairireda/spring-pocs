package hello;

public interface CurrentUserService {
	
	boolean canAccessUser(CurrentUser currentUser, Long userId);
}
