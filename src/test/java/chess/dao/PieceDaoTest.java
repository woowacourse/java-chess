package chess.dao;

import chess.domain.game.Board;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pawn;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PieceDaoTest {

    private final PieceDao dao = new PieceDao(new ChessConnectionManager());
    private final PositionDao positionDao = new PositionDao(new ChessConnectionManager());
    private final BoardDao boardDao = new BoardDao(new ChessConnectionManager());
    private int boardId;
    private int positionId;

    @BeforeEach
    void setup() {
        final Board board = boardDao.save(new Board("corinne"));
        this.boardId = board.getId();
        final Position position = positionDao.save(new Position(Column.A, Row.TWO, board.getId()));
        this.positionId = position.getId();
        final Piece piece = dao.save(new Piece(Color.WHITE, new Pawn(), positionId));
    }

    @AfterEach
    void setDown() {
        boardDao.deleteAll();
    }

    @Test
    void saveTest() {
        final Piece piece = dao.save(new Piece(Color.WHITE, new Pawn(), positionId));
        assertAll(
                () -> assertThat(piece.getType()).isInstanceOf(Pawn.class),
                () -> assertThat(piece.getColor()).isEqualTo(Color.WHITE),
                () -> assertThat(piece.getPositionId()).isEqualTo(positionId)
        );
    }

    @Test
    void findByPositionId() {
        Piece piece = dao.findByPositionId(positionId).get();
        assertAll(
                () -> assertThat(piece.getType()).isInstanceOf(Pawn.class),
                () -> assertThat(piece.getColor()).isEqualTo(Color.WHITE)
        );
    }

    @Test
    void updatePiecePositionId() {
        final int sourcePositionId = positionId;
        final int targetPositionId = positionDao.save(new Position(Column.A, Row.TWO, boardId)).getId();
        int affectedRow = dao.updatePiecePositionId(sourcePositionId, targetPositionId);
        assertThat(affectedRow).isEqualTo(1);
    }

    @Test
    void deletePieceByPositionId() {
        int affectedRows = dao.deletePieceByPositionId(positionId);
        assertThat(affectedRows).isEqualTo(1);
    }
}
