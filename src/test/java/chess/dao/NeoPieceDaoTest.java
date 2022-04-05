package chess.dao;

import chess.domain.game.NeoBoard;
import chess.domain.pieces.Color;
import chess.domain.pieces.NeoPiece;
import chess.domain.pieces.Pawn;
import chess.domain.position.Column;
import chess.domain.position.NeoPosition;
import chess.domain.position.Row;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NeoPieceDaoTest {

    private final NeoPieceDao dao = new NeoPieceDao(new ChessConnectionManager());
    private final NeoPositionDao neoPositionDao = new NeoPositionDao(new ChessConnectionManager());
    private final NeoBoardDao neoBoardDao = new NeoBoardDao(new ChessConnectionManager());
    private int neoBoardId;
    private int neoPositionId;

    @BeforeEach
    void setup() {
        final NeoBoard neoBoard = neoBoardDao.save(new NeoBoard("corinne"));
        this.neoBoardId = neoBoard.getId();
        final NeoPosition neoPosition = neoPositionDao.save(new NeoPosition(Column.A, Row.TWO, neoBoard.getId()));
        this.neoPositionId = neoPosition.getId();
        final NeoPiece neoPiece = dao.save(new NeoPiece(new Pawn(), Color.WHITE, neoPositionId));
    }

    @AfterEach
    void setDown() {
        neoBoardDao.deleteAll();
    }

    @Test
    void saveTest() {
        final NeoPiece neoPiece = dao.save(new NeoPiece(new Pawn(), Color.WHITE, neoPositionId));
        assertAll(
                () -> assertThat(neoPiece.getType()).isInstanceOf(Pawn.class),
                () -> assertThat(neoPiece.getColor()).isEqualTo(Color.WHITE),
                () -> assertThat(neoPiece.getPositionId()).isEqualTo(neoPositionId)
        );
    }

    @Test
    void findByPositionId() {
        NeoPiece neoPiece = dao.findByPositionId(neoPositionId);
        assertAll(
                () -> assertThat(neoPiece.getType()).isInstanceOf(Pawn.class),
                () -> assertThat(neoPiece.getColor()).isEqualTo(Color.WHITE)
        );
    }

    @Test
    void updatePiecePositionId() {
        final int sourcePositionId = neoPositionId;
        final int targetPositionId = neoPositionDao.save(new NeoPosition(Column.A, Row.TWO, neoBoardId)).getId();
        int affectedRow = dao.updatePiecePositionId(sourcePositionId, targetPositionId);
        assertThat(affectedRow).isEqualTo(1);
    }

    @Test
    void deletePieceByPositionId() {
        int affectedRows = dao.deletePieceByPositionId(neoPositionId);
        assertThat(affectedRows).isEqualTo(1);
    }
}
