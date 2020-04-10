package chess.domain;

import chess.domain.piece.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceFactoryTest {

    @Test
    @DisplayName("PieceFactory가 제대로 32개의 말들을 생성하는지")
    void size() {
        Pieces pieces = new Pieces(PieceFactory.getInstance().getPieces());
        assertThat(pieces.getAlivePieces().size()).isEqualTo(32);
    }
}
