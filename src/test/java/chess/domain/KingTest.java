package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @DisplayName("백팀 킹 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_King_k() {
        King king = King.createWhite(new Position("a1"));

        assertThat(king.getSignature()).isEqualTo('k');
    }

    @DisplayName("흑팀 킹 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_King_K() {
        King king = King.createBlack(new Position("a1"));

        assertThat(king.getSignature()).isEqualTo('K');
    }
}
