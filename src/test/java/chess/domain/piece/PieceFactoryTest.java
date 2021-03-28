package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {
    @DisplayName("흰색 기물들을 초기화한다.")
    @Test
    void initializeWhitePiece() {
        assertThat(PieceFactory.whitePieces().getPieces()).hasSize(16);
    }

    @DisplayName("검정색 기물들을 초기화한다.")
    @Test
    void initializeBlackPiece() {
        assertThat(PieceFactory.blackPieces().getPieces()).hasSize(16);
    }
}
