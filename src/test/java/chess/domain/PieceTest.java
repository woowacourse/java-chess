package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = King.of(Color.BLACK);
        assertThat(piece).isEqualTo(King.of(Color.BLACK));
    }

    @Test
    @DisplayName("체스 말이 블랙인지 검증하는 테스트")
    void isBlack() {
        assertThat(Pawn.of(Color.BLACK).isBlack()).isTrue();
        assertThat(King.of(Color.WHITE).isBlack()).isFalse();
    }
}
