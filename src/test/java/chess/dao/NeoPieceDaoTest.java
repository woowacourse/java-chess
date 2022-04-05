package chess.dao;

import chess.domain.pieces.Color;
import chess.domain.pieces.NeoPiece;
import chess.domain.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NeoPieceDaoTest {

    private final NeoPieceDao dao = new NeoPieceDao(new RollbackConnectionManager());

    @Test
    void saveTest() {
        dao.save(new NeoPiece(new Pawn(), Color.WHITE, 1));
    }

    @Test
    void findByPositionId() {
        NeoPiece neoPiece = dao.findByPositionId(26);
        assertAll(
                () -> assertThat(neoPiece.getId()).isEqualTo(3),
                () -> assertThat(neoPiece.getType()).isInstanceOf(Pawn.class),
                () -> assertThat(neoPiece.getColor()).isEqualTo(Color.WHITE)
        );
    }

    @Test
    void updatePiecePositionId() {
        final int sourcePositionId = 2;
        final int targetPosition = 4;
        NeoPiece neoPiece = dao.updatePiecePositionId(sourcePositionId, targetPosition);
        assertThat(neoPiece.getType()).isInstanceOf(Pawn.class);
    }

    @Test
    void deletePieceByPositionId() {
        final int positionId = 2;
        int affectedRows = dao.deletePieceByPositionId(positionId);
        assertThat(affectedRows).isEqualTo(1);
    }


}
