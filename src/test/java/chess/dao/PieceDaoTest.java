package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardBuilder;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PieceDaoTest {

    private PieceDao pieceDao = new PieceDao();

    @BeforeEach
    public void before() {
        pieceDao.removeAll();
    }

    @Test
    public void saveTest() {
        Board board = new Board(new BoardBuilder());
        assertDoesNotThrow(() -> pieceDao.save(board.getBoard()));
    }

    @Test
    public void saveAndLoadTest() {
        Board board = new Board(new BoardBuilder());
        pieceDao.save(board.getBoard());
        Map<Position, Piece> pieces = pieceDao.load();
        assertThat(pieces.values().size()).isEqualTo(64);
    }
}
