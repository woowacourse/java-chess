package chess.domain.dao;

import chess.domain.chess.dao.ChessBoardDAO;
import chess.domain.chess.dao.DBConnection;
import chess.domain.chess.game.ChessBoard;
import chess.domain.chess.game.Team;
import org.junit.jupiter.api.Test;
import view.OutputView;

import java.sql.Connection;
import java.sql.SQLException;

public class ChessBoardDaoTest {
    private Connection connection = DBConnection.getConnection();




    @Test
    void 검색2() throws SQLException {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO(connection);

        ChessBoard chessBoard = chessBoardDAO.select(10, Team.BLACK);
        OutputView.printCheckBoard(chessBoard);
    }


}
