package chess.db;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.WhitePawn;
import chess.domain.piece.abstractPiece.Piece;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardDaoTest {
    private BoardDao boardDao;

    @BeforeEach
    void setUp() {
        boardDao = new BoardDao(new TestConnectionGenerator());
        boardDao.deleteAll();
    }

    @Test
    void loadAll() {
        assertThat(boardDao.loadAll().getPieces()).isEmpty();
    }

    @Test
    void save() {
        Piece piece = new WhitePawn();
        Board board = new Board(Map.of(Position.of(1, 1), piece));

        boardDao.addAll(board);
        boardDao.save(new Movement(Position.of(1, 1), Position.of(3, 1)), piece);

        assertThat(boardDao.loadAll().getPieces()).containsExactly(Map.entry(Position.of(3, 1), piece));
    }

    @Test
    void addAll() {
        Board board = new Board(Map.of(Position.of(1, 1), new WhitePawn()));
        boardDao.addAll(board);
        assertThat(boardDao.loadAll().getPieces()).isEqualTo(board.getPieces());
    }

    @Test
    void deleteAll() {
        Board board = new Board(Map.of(Position.of(1, 1), new WhitePawn()));
        boardDao.addAll(board);
        boardDao.deleteAll();
        assertThat(boardDao.loadAll().getPieces()).isEmpty();
    }
}
