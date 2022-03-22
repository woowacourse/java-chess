package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @DisplayName("백팀 퀸 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Queen_q() {
        Queen queen = Queen.createWhite(new Position("a1"));

        assertThat(queen.getSignature()).isEqualTo('q');
    }

    @DisplayName("흑팀 퀸 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Queen_Q() {
        Queen queen = Queen.createBlack(new Position("a1"));

        assertThat(queen.getSignature()).isEqualTo('Q');
    }
}
