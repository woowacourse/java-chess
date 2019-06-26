package chess.domain.dao;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.Team;
import chess.domain.chess.dao.ChessBoardDAO;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.geometric.Position;
import chess.util.DBConnection;
import org.junit.jupiter.api.Test;
import view.OutputView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChessBoardDAOTest {
    private Connection connection = DBConnection.getConnection();

    @Test
    void 삽입() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardInitializer());
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO(connection);

        chessBoardDAO.add(chessBoard, Team.WHITE);
    }

    @Test
    void 업데이트() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardInitializer());
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO(connection);

        chessBoard.move(Position.create(1, 0), Position.create(2, 2));
        chessBoardDAO.update(chessBoard, Team.BLACK, 16);
    }

    @Test
    void 검색() throws SQLException {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO(connection);

        ChessBoard chessBoard = chessBoardDAO.select(16);
        OutputView.printCheckBoard(chessBoard);
    }

    @Test
    void 존재하는id들출력() throws SQLException {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO(connection);

        List<Integer> ids = chessBoardDAO.getIdList();
        System.out.println(ids);

    }
}
