package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

import domain.PieceNameConverter;
import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Camp;
import domain.piece.slider.Rook;

class ChessBoardDaoTest {

    private final ChessBoardDao chessBoardDao = new ChessBoardDao();

    @Test
    public void connection() {
        try (final var connection = chessBoardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void selectTest() {
        PieceNameConverter.init();
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();
        chessBoardDao.save(chessBoard);
        assertThat(chessBoardDao.select(new Square(File.A, Rank.ONE))).isEqualTo(new Rook(Camp.WHITE));
    }
}
