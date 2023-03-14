package database;

import java.util.Objects;

public final class User {

    private final String userId;
    private final String name;

    public User(
            final String userId,
            final String name
    ) {
        this.userId = userId;
        this.name = name;
    }

    public String userId() {
        return userId;
    }

    public String name() {
        return name;
    }

    public User changeName(String newName) {
        return new User(userId, newName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User)o;

        if (!Objects.equals(userId, user.userId))
            return false;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}