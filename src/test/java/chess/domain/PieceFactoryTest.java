package chess.domain;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @Test
    void createPieceTest() {
        Piece piece = PieceFactory.of("Knight", "b", 1, Team.WHITE.name());
        Assertions.assertThat(piece).isInstanceOf(Knight.class);
    }
}