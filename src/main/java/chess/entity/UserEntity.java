package chess.entity;

public final class UserEntity {
    private Long id;
    private final String name;

    public UserEntity(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UserEntity(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
