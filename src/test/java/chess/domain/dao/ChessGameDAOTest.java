package chess.domain.dao;

import chess.db.DBConnection;
import chess.domain.chess.ChessGame;
import chess.domain.chess.Team;
import chess.domain.chess.dao.ChessBoardDAO;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.geometric.Position;
import org.junit.jupiter.api.Test;
import view.OutputView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChessGameDAOTest {
    private Connection connection = DBConnection.getConnection();



    @Test
    void 업데이트() throws SQLException {
        ChessGame chessGame = new ChessGame(new ChessBoardInitializer());
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO(connection);

        chessGame.move(Position.create(1, 0), Position.create(2, 2));
        chessBoardDAO.update(chessGame, Team.BLACK, 16);
    }

    @Test
    void 검색() throws SQLException {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO(connection);

        ChessGame chessGame = chessBoardDAO.select(16);
        OutputView.printCheckBoard(chessGame);
    }

    @Test
    void 존재하는id들출력() throws SQLException {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO(connection);

        List<Integer> ids = chessBoardDAO.getIdList();

    }
}
