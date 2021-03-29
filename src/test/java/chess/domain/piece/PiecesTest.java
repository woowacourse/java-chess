package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PositionTexture.A2;
import static chess.domain.piece.PositionTexture.D8;
import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {
    private Pieces whitePieces;
    private Pieces blackPieces;

    @BeforeEach
    void setUp() {
        whitePieces = PieceFactory.whitePieces();
        blackPieces = PieceFactory.blackPieces();
    }

    @DisplayName("현재 남아 있는 말에 대한 점수를 계산한다.")
    @Test
    void calculateScoreWhitePieces() {
        whitePieces.remove(A2);
        blackPieces.remove(D8);

        assertThat(whitePieces.calculateScore()).isEqualTo(37.0);
        assertThat(blackPieces.calculateScore()).isEqualTo(29.0);
    }
}
