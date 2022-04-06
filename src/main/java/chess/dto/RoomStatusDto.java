package chess.dto;

import chess.domain.GameStatus;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomStatusDto {

    private final String name;
    private final GameStatus gameStatus;

    private RoomStatusDto(final String name, final GameStatus gameStatus) {
        this.name = name;
        this.gameStatus = gameStatus;
    }

    public static RoomStatusDto from(final ResultSet resultSet) throws SQLException {
        return new RoomStatusDto(
                resultSet.getString("name"),
                GameStatus.from(resultSet.getString("game_status")
                )
        );
    }

    public String getName() {
        return name;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
