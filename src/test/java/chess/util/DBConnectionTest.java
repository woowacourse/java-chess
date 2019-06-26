package chess.util;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.dao.ChessBoardDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DBConnectionTest {

    @Test
    void DB연결() {
        DBConnection.getConnection();
    }

}
