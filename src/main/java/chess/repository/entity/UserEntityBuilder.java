package chess.repository.entity;

public class UserEntityBuilder {

	private long id;
	private String name;

	public UserEntityBuilder id(final long id) {
		this.id = id;
		return this;
	}

	public UserEntityBuilder name(final String name) {
		this.name = name;
		return this;
	}

	public UserEntity build() {
		return new UserEntity(id, name);
	}
}
