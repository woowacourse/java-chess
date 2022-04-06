package chess.dto;

import chess.domain.chesspiece.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentTurnDto {

    private final String name;
    private final Color currentTurn;

    private CurrentTurnDto(final String name, final Color currentTurn) {
        this.name = name;
        this.currentTurn = currentTurn;
    }

    public static CurrentTurnDto from(final ResultSet resultSet) throws SQLException {
        return new CurrentTurnDto(
                resultSet.getString("name"),
                Color.from(resultSet.getString("current_turn"))
        );
    }

    public String getName() {
        return name;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }
}
