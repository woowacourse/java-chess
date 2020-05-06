package chess.domain.pieceTest;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceFactoryTest {

    @Test
    @DisplayName("알파벳으로 Piece를 불러주는 기능 테스트")
    void callPiece() {
        assertThat(PieceFactory.of("p")).isEqualTo(Pawn.of(Color.WHITE));
    }
}
