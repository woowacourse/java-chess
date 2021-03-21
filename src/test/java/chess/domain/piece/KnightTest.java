package chess.domain.piece;

import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class KnightTest {
    private static State white;
    private static State black;

    @BeforeEach
    void init() {
        white = StateFactory.initialization(new Pieces(
                        Arrays.asList(
                                Knight.from("n", D4),
                                Pawn.from("p", F3),
                                Bishop.from("b", F5))
                )
        );
        black = StateFactory.initialization(new Pieces(
                        Arrays.asList(
                                Bishop.from("B", C6)
                        )
                )
        );
    }

    @DisplayName("이동할 수 있는 방향의 target 위치로 정상 이동한다.")
    @Test
    void moveTargetPosition() {
        Source knight = Source.valueOf(D4, white);
        Target target = Target.valueOf(knight, E6, white);

        knight.move2(target, white.pieces(), black.pieces());
        assertThat(knight.isSamePosition(E6)).isEqualTo(true);

        knight.move2(Target.valueOf(knight, D4, white), white.pieces(), black.pieces());
        knight.move2(Target.valueOf(knight, C6, white), white.pieces(), black.pieces());
        assertThat(knight.isSamePosition(C6)).isEqualTo(true);

        knight.move2(Target.valueOf(knight, D4, white), white.pieces(), black.pieces());
        knight.move2(Target.valueOf(knight, B5, white), white.pieces(), black.pieces());
        assertThat(knight.isSamePosition(B5)).isEqualTo(true);

        knight.move2(Target.valueOf(knight, D4, white), white.pieces(), black.pieces());
        knight.move2(Target.valueOf(knight, B3, white), white.pieces(), black.pieces());
        assertThat(knight.isSamePosition(B3)).isEqualTo(true);

        knight.move2(Target.valueOf(knight, D4, white), white.pieces(), black.pieces());
        knight.move2(Target.valueOf(knight, C2, white), white.pieces(), black.pieces());
        assertThat(knight.isSamePosition(C2)).isEqualTo(true);

        knight.move2(Target.valueOf(knight, D4, white), white.pieces(), black.pieces());
        knight.move2(Target.valueOf(knight, E2, white), white.pieces(), black.pieces());
        assertThat(knight.isSamePosition(E2)).isEqualTo(true);
    }

    @DisplayName("이동할 수 없는 위치인 경우 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Source knight = Source.valueOf(D4, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            knight.move2(Target.valueOf(knight, D6, white), white.pieces(), black.pieces());
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", D6);
        assertThat(knight.isSamePosition(D4)).isEqualTo(true);
    }

    @DisplayName("같은 색깔의 기물 위치인 경우 예외가 발생한다.")
    @Test
    void sameColorException() {
        Source knight = Source.valueOf(D4, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            knight.move2(Target.valueOf(knight, F5, white), white.pieces(), black.pieces());
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", F5);
        assertThat(knight.isSamePosition(D4)).isEqualTo(true);
    }
}
