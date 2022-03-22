package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("백팀 비숍 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Bishop_k() {
        Bishop bishop = Bishop.createWhite(new Position("a1"));

        assertThat(bishop.getSignature()).isEqualTo("b");
    }

    @DisplayName("흑팀 비숍 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Bishop_K() {
        Bishop bishop = Bishop.createBlack(new Position("a1"));

        assertThat(bishop.getSignature()).isEqualTo("B");
    }
}
