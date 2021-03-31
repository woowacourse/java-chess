package chess.domain.room;

import java.util.Objects;

public class User {
	private String userId;
	private String password;

	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final User user = (User) o;
		return Objects.equals(userId, user.userId) && Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, password);
	}
}

