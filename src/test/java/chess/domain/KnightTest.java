package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("백팀 나이트 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Knight_k() {
        Knight knight = Knight.createWhite(new Position("a1"));

        assertThat(knight.getSignature()).isEqualTo('k');
    }

    @DisplayName("흑팀 나이트 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Knight_K() {
        Knight knight = Knight.createBlack(new Position("a1"));

        assertThat(knight.getSignature()).isEqualTo('K');
    }
}
