package database;

import java.util.Objects;

public final class User {

    private final String userId;
    private final String name;

    public User(final String userId, final String name) {
        this.userId = userId;
        this.name = name;
    }

    public String userId() {
        return userId;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        return userId.equals(((User) o).userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}