package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @DisplayName("백팀 킹 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_King_k() {
        King king = King.createWhite(new Position("a1"));

        assertThat(king.getSignature()).isEqualTo("k");
    }

    @DisplayName("흑팀 킹 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_King_K() {
        King king = King.createBlack(new Position("a1"));

        assertThat(king.getSignature()).isEqualTo("K");
    }

    @DisplayName("타겟 위치가 빈칸일 경우 이동에 성공한다.")
    @Test
    void move_Blank() {
        King king = King.createBlack(new Position("c8"));

        assertThat(king.isMovable(new Blank(new Position("d7"))))
                .isTrue();
        assertThat(king.isMovable(new Blank(new Position("c7"))))
                .isTrue();
        assertThat(king.isMovable(new Blank(new Position("d8"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 적 기물일 경우 공격에 성공한다.")
    @Test
    void move_Enemy() {
        King king = King.createBlack(new Position("c8"));

        assertThat(king.isMovable(King.createWhite(new Position("d7"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 같은 팀 기물일 경우 이동에 실패한다.")
    @Test
    void move_Ally() {
        King king = King.createBlack(new Position("c8"));

        assertThat(king.isMovable(King.createBlack(new Position("d7"))))
                .isFalse();
    }
}
