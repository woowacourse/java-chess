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

    @DisplayName("타겟 위치가 빈칸일 경우 이동에 성공한다.")
    @Test
    void move_Blank() {
        Bishop bishop = Bishop.createBlack(new Position("c8"));

        assertThat(bishop.isMovable(new Blank(new Position("d7"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 적 기물일 경우 공격에 성공한다.")
    @Test
    void move_Enemy() {
        Bishop bishop = Bishop.createBlack(new Position("c8"));

        assertThat(bishop.isMovable(Bishop.createWhite(new Position("d7"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 같은 팀 기물일 경우 이동에 실패한다.")
    @Test
    void move_Ally() {
        Bishop bishop = Bishop.createBlack(new Position("c8"));

        assertThat(bishop.isMovable(Bishop.createBlack(new Position("d7"))))
                .isFalse();
    }
}
