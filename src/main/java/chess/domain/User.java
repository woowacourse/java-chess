package chess.domain;

import java.util.Objects;

public class User {
    private String id;
    private String pwd;

    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User)o;
        return id.equals(user.id) && pwd.equals(user.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pwd);
    }
}
