package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("백팀 폰 기물이 생성되었을 때 소문자 시그니처로 표시된다.")
    @Test
    void createWhite_Pawn_p() {
        Pawn pawn = Pawn.createWhite(new Position("a1"));

        assertThat(pawn.getSignature()).isEqualTo('p');
    }

    @DisplayName("흑팀 폰 기물이 생성되었을 때 대문자 시그니처로 표시된다.")
    @Test
    void createBlack_Pawn_P() {
        Pawn pawn = Pawn.createBlack(new Position("a1"));

        assertThat(pawn.getSignature()).isEqualTo('P');
    }
}
