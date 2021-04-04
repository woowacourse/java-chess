package chess.dto;

public class RoomCreateRequestDTO {
    private String name;
    private String pw;

    public RoomCreateRequestDTO(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    @Override
    public String toString() {
        return "RoomCreateRequestDTO{" +
                "name='" + name + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
