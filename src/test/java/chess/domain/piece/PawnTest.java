package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PawnTest {
    private static State white;
    private static State black;

    private static Stream<Arguments> pawnFirstMove() {
        return Stream.of(
                Arguments.of(B3, true),
                Arguments.of(B4, true)
        );
    }

    @BeforeEach
    void init() {
        white = StateFactory.initialization(new Pieces(
                Arrays.asList(
                        Pawn.from("p", B2),
                        Pawn.from("p", F2),
                        Pawn.from("p", D5),
                        Bishop.from("b", G3),
                        Bishop.from("b", C7)
                )));
        black = StateFactory.initialization(new Pieces(
                Arrays.asList(
                        Rook.from("R", C3),
                        Knight.from("N", F4),
                        Rook.from("R", E7)
                )));
    }

    @DisplayName("첫 시작시 1칸 혹은 2칸을 이동한다.")
    @ParameterizedTest
    @MethodSource("pawnFirstMove")
    void firstMove(Position position, boolean canMove) {
        Source pawn = Source.valueOf(B2, white);

        pawn.move(Target.valueOf(pawn, position, white), white.pieces(), black.pieces());
        assertThat(pawn.isSamePosition(position)).isEqualTo(canMove);
    }

    @DisplayName("첫 시작시 대각선에 있는 상대의 기물을 잡는다.")
    @Test
    void firstMoveKill() {
        Source pawn = Source.valueOf(B2, white);

        pawn.move(Target.valueOf(pawn, C3, white), white.pieces(), black.pieces());
        assertThat(pawn.isSamePosition(C3)).isEqualTo(true);
    }

    @DisplayName("첫 시작시 대각선에 있는 기물이 같은 색이면 예외가 발생한다.")
    @Test
    void firstMoveKillException() {
        Source pawn = Source.valueOf(F2, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            pawn.move(Target.valueOf(pawn, G3, white), white.pieces(), black.pieces());
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", G3);
        assertThat(pawn.isSamePosition(F2)).isEqualTo(true);
    }

    @DisplayName("첫 시작시 2칸 이상을 이동하려고 하면 에러가 발생한다.")
    @Test
    void firstMoveException() {
        Source pawn = Source.valueOf(B2, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            pawn.move(Target.valueOf(pawn, B5, white), white.pieces(), black.pieces());
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", B5);
        assertThat(pawn.isSamePosition(B2)).isEqualTo(true);
    }

    @DisplayName("첫 시작시 갈 수 있는 위치에 기물이 있으면 에러가 발생한다.")
    @Test
    void firstMoveObstacleException() {
        Source pawn = Source.valueOf(F2, white);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            pawn.move(Target.valueOf(pawn, F4, white), white.pieces(), black.pieces());
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", F4);
        assertThat(pawn.isSamePosition(F2)).isEqualTo(true);
    }


    @DisplayName("첫 시작 이후 1칸을 이동한다.")
    @Test
    void afterFirstMove() {
        Source pawn = Source.valueOf(D5, white);

        pawn.move(Target.valueOf(pawn, D6, white), white.pieces(), black.pieces());
        pawn.move(Target.valueOf(pawn, D7, white), white.pieces(), black.pieces());

        assertThat(pawn.isSamePosition(D7)).isEqualTo(true);
    }

    @DisplayName("첫 시작 이후 2칸을 이동하면 예외가 발생한다.")
    @Test
    void afterFirstMoveException() {
        Source pawn = Source.valueOf(D5, white);
        pawn.move(Target.valueOf(pawn, D6, white), white.pieces(), black.pieces());

        assertThatIllegalArgumentException().isThrownBy(() -> {
            pawn.move(Target.valueOf(pawn, D8, white), white.pieces(), black.pieces());
        }).withMessage("이동할 수 없는 위치입니다. 입력 값: %s", D8);
        assertThat(pawn.isSamePosition(D6)).isEqualTo(true);
    }

    @DisplayName("첫 시작 이후 대각선에 있는 상대의 기물을 잡는다.")
    @Test
    void afterFirstMoveKill() {
        Source pawn = Source.valueOf(D5, white);

        pawn.move(Target.valueOf(pawn, D6, white), white.pieces(), black.pieces());
        pawn.move(Target.valueOf(pawn, E7, white), white.pieces(), black.pieces());

        assertThat(pawn.isSamePosition(E7)).isEqualTo(true);
    }

    @DisplayName("첫 시작 이후 대각선에 있는 기물이 같은 색이면 예외가 발생한다.")
    @Test
    void afterFirstMoveKillException() {
        Source pawn = Source.valueOf(D5, white);
        pawn.move(Target.valueOf(pawn, D6, white), white.pieces(), black.pieces());

        assertThatIllegalArgumentException().isThrownBy(() -> {
            pawn.move(Target.valueOf(pawn, C7, white), white.pieces(), black.pieces());
        }).withMessage("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", C7);
        assertThat(pawn.isSamePosition(D6)).isEqualTo(true);
    }
}