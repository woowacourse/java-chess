package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("백팀 폰 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Pawn_p() {
        Pawn pawn = Pawn.createWhite(new Position("a1"), true);

        assertThat(pawn.getSignature()).isEqualTo("p");
    }

    @DisplayName("흑팀 폰 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Pawn_P() {
        Pawn pawn = Pawn.createBlack(new Position("a1"), true);

        assertThat(pawn.getSignature()).isEqualTo("P");
    }

    @DisplayName("타겟 위치가 빈칸일 경우 이동에 성공한다.")
    @Test
    void move_Blank() {
        Pawn pawn = Pawn.createBlack(new Position("a7"), true);

        assertThat(pawn.isMovable(new Blank(new Position("a6"))))
                .isTrue();
    }

    @DisplayName("타겟 위치가 적 기물일 경우 공격에 성공한다.")
    @Test
    void move_Enemy() {
        Pawn pawn = Pawn.createBlack(new Position("a7"), true);

        assertThat(pawn.isMovable(Pawn.createWhite(new Position("b6"), true)))
                .isTrue();
    }

    @DisplayName("타겟 위치가 같은 팀 기물일 경우 이동에 실패한다.")
    @Test
    void move_Ally() {
        Pawn pawn = Pawn.createBlack(new Position("a7"), true);

        assertThat(pawn.isMovable(Pawn.createBlack(new Position("a6"), true)))
                .isFalse();
    }

    @DisplayName("폰 기물은 첫번째 이동일 경우에 두칸을 전진할 수 있다.")
    @Test
    void firstMove_CanMoveTwice() {
        Pawn pawn = Pawn.createBlack(new Position("a7"), true);

        assertThat(pawn.isMovable(new Blank(new Position("a5"))))
                .isTrue();
    }
}
