package chess.domain.piece;

import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BishopTest {
    private static State white;
    private static State black;

    @BeforeEach
    void init() {
        white = StateFactory.initialization(new Pieces(
                Arrays.asList(
                        Bishop.from("b", D6),
                        Pawn.from("p", C5))));
        black = StateFactory.initialization(new Pieces(
                Collections.singletonList(
                        Queen.from("Q", F4))));
    }

    @DisplayName("이동할 수 있는 방향의 target 위치로 정상 이동한다.")
    @Test
    void moveTargetPosition() {
        Source bishop = Source.valueOf(D6, white);
        Target target = Target.valueOf(bishop, C7, white);
        bishop.move(target, white.pieces(), black.pieces());
        assertThat(bishop.isSamePosition(C7)).isEqualTo(true);

        bishop.move(Target.valueOf(bishop, D6, white), white.pieces(), black.pieces());
        bishop.move(Target.valueOf(bishop, E7, white), white.pieces(), black.pieces());
        assertThat(bishop.isSamePosition(E7)).isEqualTo(true);

        bishop.move(Target.valueOf(bishop, D6, white), white.pieces(), black.pieces());
        bishop.move(Target.valueOf(bishop, E5, white), white.pieces(), black.pieces());
        assertThat(bishop.isSamePosition(E5)).isEqualTo(true);
    }

    @DisplayName("이동할 수 없는 위치인 경우 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Source bishop = Source.valueOf(D6, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            bishop.move(Target.valueOf(bishop, B7, white), white.pieces(), black.pieces());
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", B7);
        assertThat(bishop.isSamePosition(D6)).isEqualTo(true);
    }

    @DisplayName("같은 색깔의 기물 위치인 경우 예외가 발생한다.")
    @Test
    void sameColorException() {
        Source bishop = Source.valueOf(D6, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            bishop.move(Target.valueOf(bishop, C5, white), white.pieces(), black.pieces());
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", C5);
        assertThat(bishop.isSamePosition(D6)).isEqualTo(true);
    }

    @DisplayName("장애물이 없는 경우 맵의 오른쪽 위 끝으로 갈 수 있다.")
    @Test
    void moveTargetDownPositionException() {
        Source bishop = Source.valueOf(D6, white);
        bishop.move(Target.valueOf(bishop, F8, white), white.pieces(), black.pieces());
        assertThat(bishop.isSamePosition(F8)).isEqualTo(true);
    }
}
