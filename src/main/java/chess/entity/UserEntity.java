package chess.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
