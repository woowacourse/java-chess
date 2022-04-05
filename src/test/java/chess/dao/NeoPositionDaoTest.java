package chess.dao;

import chess.domain.pieces.NeoPiece;
import chess.domain.position.Column;
import chess.domain.position.NeoPosition;
import chess.domain.position.Row;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class NeoPositionDaoTest {

    final NeoPositionDao dao = new NeoPositionDao(new RollbackConnectionManager());

    @Test
    void save() {
        dao.save(new NeoPosition(Column.A, Row.TWO, 1));
    }

    @Test
    void findByColumnAndRowAndBoardId() {
        NeoPosition neoPosition = dao.findByColumnAndRowAndBoardId(Column.A, Row.TWO, 15);
        assertAll(
                () -> assertThat(neoPosition.getColumn().value()).isEqualTo(1),
                () -> assertThat(neoPosition.getRow().value()).isEqualTo(2),
                () -> assertThat(neoPosition.getId()).isEqualTo(71)
        );
    }

    @Test
    void findAllPositionsAndPieces() {
        final int boardId = 15;
        Map<NeoPosition, NeoPiece> all = dao.findAllPositionsAndPieces(boardId);
        assertThat(all.size()).isEqualTo(32);
    }
}
