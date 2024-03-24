package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorTest {

    @Test
    @DisplayName("같은 색이 아닌 경우 참을 반환한다.")
    void isEnemyColor() {
        assertAll(
                () -> assertThat(WHITE.isNotSameTeam(new Piece(PieceType.PAWN, BLACK))).isTrue(),
                () -> assertThat(WHITE.isNotSameTeam(Piece.getEmptyPiece())).isTrue()
        );
    }

    @Test
    @DisplayName("같은 색인 경우 거짓을 반환한다.")
    void isNotEnemyColor() {
        assertThat(BLACK.isNotSameTeam(new Piece(PieceType.PAWN, BLACK))).isFalse();
    }
}
