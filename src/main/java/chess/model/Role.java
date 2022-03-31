package chess.model;

public class Role {
	private final String role;

	public Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return role;
	}
}
