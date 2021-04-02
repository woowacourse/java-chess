package chess.webdto;

public class DBConnectionDTO {
    private String connection;

    private DBConnectionDTO(String connection) {
        this.connection = connection;
    }

    public static DBConnectionDTO success() {
        return new DBConnectionDTO("success");
    }

    public static DBConnectionDTO fail() {
        return new DBConnectionDTO("fail");
    }
}
