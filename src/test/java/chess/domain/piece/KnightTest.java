package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("백팀 나이트 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Knight_n() {
        Knight knight = Knight.createWhite(new Position("b8"));

        assertThat(knight.getSignature()).isEqualTo("n");
    }

    @DisplayName("흑팀 나이트 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Knight_N() {
        Knight knight = Knight.createBlack(new Position("b8"));

        assertThat(knight.getSignature()).isEqualTo("N");
    }

    @DisplayName("타겟 위치가 빈칸일 경우 이동에 성공한다.")
    @Test
    void move_Blank() {
        Knight knight = Knight.createBlack(new Position("b8"));

        assertThat(knight.isMovable(new Blank(new Position("a6"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 적 기물일 경우 공격에 성공한다.")
    @Test
    void move_Enemy() {
        Knight knight = Knight.createBlack(new Position("b8"));

        assertThat(knight.isMovable(Knight.createWhite(new Position("a6"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 같은 팀 기물일 경우 이동에 실패한다.")
    @Test
    void move_Ally() {
        Knight knight = Knight.createBlack(new Position("b8"));

        assertThat(knight.isMovable(Knight.createBlack(new Position("a6"))))
                .isFalse();
    }
}
