package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PawnTest {
    @Test
    @DisplayName("방향을 지정한 폰을 정상 생성한다.")
    void init_direction_fixed_pawn() {
        assertThatCode(() -> new Pawn(1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {"a7,a6", "a7,a5", "a7,b6","h7,h6","h7,h5","h7,g6"})
    @DisplayName("이동할 수 있는 좌표가 주어졌을 때, 폰이 움직일 수 있는 칸인지 체크한다.")
    void when_give_position_check_black_pawn_rule(String start, String dest) {
        Pawn blackPawn = new Pawn(-1);
        Position current = Position.of(start);
        Position destination = Position.of(dest);
        assertThat(blackPawn.checkPositionRule(current, destination)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a2,a3", "a2,a4", "a2,b3","h2,h3","h2,h4","h2,g3"})
    @DisplayName("이동할 수 있는 좌표가 주어졌을 때, 폰이 움직일 수 있는 칸인지 체크한다.")
    void when_give_position_check_white_pawn_rule(String start, String dest) {
        Pawn blackPawn = new Pawn(1);
        Position current = Position.of(start);
        Position destination = Position.of(dest);
        assertThat(blackPawn.checkPositionRule(current, destination)).isTrue();
    }
}
