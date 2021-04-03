package chess.dao;

import chess.controller.dto.BoardDto;
import chess.domain.board.Board;

import java.sql.SQLException;

public class GameDao {
    public void addGameStatus(final String turn, final BoardDto board) throws SQLException {
        String query = "INSERT INTO user VALUES (?, ?)";

    }

}
