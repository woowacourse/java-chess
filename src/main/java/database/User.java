package database;

import java.util.Objects;

public class User {
    
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
        return this.userId;
    }
    
    public String name() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.userId, this.name);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final var user = (User) o;
        return Objects.equals(this.userId, user.userId) && Objects.equals(this.name, user.name);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "userId='" + this.userId + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }
}
