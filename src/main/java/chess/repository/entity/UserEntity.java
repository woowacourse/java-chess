package chess.repository.entity;

public class UserEntity implements Entity {

	private final long id;
	private final String name;

	public UserEntity(final long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "UserEntity{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
