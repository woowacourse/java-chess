package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import domain.PieceNameConverter;
import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.slider.Rook;

class ChessDaoTest {

    private final ChessDao chessDao = new ChessDao();

    @Test
    public void connection() {
        try (final var connection = chessDao.getConnection()) {
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
        chessDao.save(convert(chessBoard));
        assertThat(chessDao.select("AONE")).isEqualTo("r");
    }

    private Map<String, String> convert(ChessBoard chessBoard) {
        Map<Square, Piece> board = chessBoard.getBoard();
        return board.keySet()
            .stream()
            .collect(Collectors.toMap(Square::toString, square -> PieceNameConverter.convert(board.get(square))));
    }
}
