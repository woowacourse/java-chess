package chess.Controller.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDto {

    private final int id;
    private final int boardId;

    private UserDto(int id, int boardId) {
        this.id = id;
        this.boardId = boardId;
    }

    public static UserDto fromEntity(final ResultSet resultSet) throws SQLException {
        return new UserDto(resultSet.getInt("id"), resultSet.getInt("board_id"));
    }

    public int getId() {
        return id;
    }

    public int getBoardId() {
        return boardId;
    }
}
