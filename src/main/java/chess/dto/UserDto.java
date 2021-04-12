package chess.dto;

import java.util.Objects;

public class UserDto {
    private String name;
    private String pwd;

    public UserDto(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
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
        UserDto userDto = (UserDto)o;
        return name.equals(userDto.name) && pwd.equals(userDto.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pwd);
    }
}
