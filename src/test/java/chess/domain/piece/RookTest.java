package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @DisplayName("백팀 룩 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Rook_r() {
        Rook rook = Rook.createWhite(new Position("a1"));

        assertThat(rook.getSignature()).isEqualTo("r");
    }

    @DisplayName("흑팀 룩 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Rook_R() {
        Rook rook = Rook.createBlack(new Position("a1"));

        assertThat(rook.getSignature()).isEqualTo("R");
    }

    @DisplayName("타겟 위치가 빈칸일 경우 이동에 성공한다.")
    @Test
    void move_Blank() {
        Rook rook = Rook.createBlack(new Position("a8"));

        assertThat(rook.isMovable(new Blank(new Position("a1"))))
                .isTrue();
        assertThat(rook.isMovable(new Blank(new Position("h8"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 적 기물일 경우 공격에 성공한다.")
    @Test
    void move_Enemy() {
        Rook rook = Rook.createBlack(new Position("a8"));

        assertThat(rook.isMovable(Rook.createWhite(new Position("a1"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 같은 팀 기물일 경우 이동에 실패한다.")
    @Test
    void move_Ally() {
        Rook rook = Rook.createBlack(new Position("a8"));

        assertThat(rook.isMovable(Rook.createBlack(new Position("a1"))))
                .isFalse();
    }
}
