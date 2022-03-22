package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @DisplayName("백팀 룩 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Rook_r() {
        Rook rook = Rook.createWhite(new Position("a1"));

        assertThat(rook.getSignature()).isEqualTo('r');
    }

    @DisplayName("흑팀 룩 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Rook_R() {
        Rook rook = Rook.createBlack(new Position("a1"));

        assertThat(rook.getSignature()).isEqualTo('R');
    }
}
