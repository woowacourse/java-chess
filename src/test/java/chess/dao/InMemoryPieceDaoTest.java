package chess.dao;

import static chess.fixture.PositionFixture.B1;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryPieceDaoTest {

    private InMemoryPieceDao inMemoryPieceDao;

    @BeforeEach
    void setUp() {
        inMemoryPieceDao = new InMemoryPieceDao();
    }

    @Test
    void test_createPiece() {
        inMemoryPieceDao.createPiece(B1, Pawn.from(Color.WHITE), "1");
        assertThat(inMemoryPieceDao.read("1").size()).isEqualTo(1);
    }
}
