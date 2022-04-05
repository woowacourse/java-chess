package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.constants.TestConstants;
import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.position.Position;
import chess.entity.Square;
import chess.utils.JdbcTemplate;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareDaoTest {

    private SquareDao squareDao;
    private Connection connection;

    @BeforeEach
    void beforeEach() {
        connection = JdbcTemplate.getConnection(TestConstants.TEST_DB_URL);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        squareDao = new SquareDao(connection);
    }

    @AfterEach
    void afterEach() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void saveAll() {
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        Map<Position, Piece> board = chessBoard.getPieces();
        List<Square> squares = board.keySet().stream()
                .map(position -> new Square(position.convertToString(), board.get(position).convertToString()))
                .collect(Collectors.toList());

        assertThat(squareDao.saveAll(squares, 1)).isTrue();
    }

    @Test
    void findByRoomIdAndPosition() {
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        Map<Position, Piece> board = chessBoard.getPieces();
        List<Square> squares = board.keySet().stream()
                .map(position -> new Square(position.convertToString(), board.get(position).convertToString()))
                .collect(Collectors.toList());

        squareDao.saveAll(squares, 1);

        Square square = squareDao.findByRoomIdAndPosition(1, "a1");
        assertThat(square.getPiece()).isEqualTo("white_rook");
    }

    @Test
    void update() {
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        Map<Position, Piece> board = chessBoard.getPieces();
        List<Square> squares = board.keySet().stream()
                .map(position -> new Square(position.convertToString(), board.get(position).convertToString()))
                .collect(Collectors.toList());

        squareDao.saveAll(squares, 1);

        squareDao.update(1, "a2", "empty");
        Square square = squareDao.findByRoomIdAndPosition(1, "a2");
        assertThat(square.getPiece()).isEqualTo("empty");
    }
}
