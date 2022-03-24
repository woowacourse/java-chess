package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("폰은 한 칸 또는 두 칸 전진한다.")
    void initialMoveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Pawn pawn = new Pawn(team, initialPosition);
        pawn.move(nextPosition);
        assertThat(pawn.isSamePosition(nextPosition)).isTrue();
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('a', 2),
                        new ChessBoardPosition('a', 3), "흑팀의 폰은 초기상태에서 한칸 전진한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('a', 7),
                        new ChessBoardPosition('a', 6), "백팀의 폰은 초기상태에서 한칸 전진한다"),
                Arguments.of(Team.BLACK, new ChessBoardPosition('a', 2),
                        new ChessBoardPosition('a', 4), "흑팀의 폰은 초기상태에서 두칸 전진한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('a', 7),
                        new ChessBoardPosition('a', 5), "백팀의 폰은 초기상태에서 두칸 전진한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('a', 6),
                        new ChessBoardPosition('a', 5), "백팀의 폰은 초기상태 이후 한칸 전진한다")
        );
    }

    @Test
    @DisplayName("앞으로 3칸 전진할 수 없다")
    void moveTest3() {
        ChessBoardPosition initialPosition = new ChessBoardPosition('a', 2);
        Pawn pawn = new Pawn(Team.BLACK, initialPosition);
        ChessBoardPosition nextPosition = new ChessBoardPosition('a', 5);

        assertThatThrownBy(() -> pawn.move(nextPosition)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("초기상태 이후로는 두 칸 이상 전진할 수 없다.")
    void notMoveTest() {
        ChessBoardPosition initialPosition = new ChessBoardPosition('a', 3);
        Pawn pawn = new Pawn(Team.BLACK, initialPosition);
        ChessBoardPosition nextPosition = new ChessBoardPosition('a', 5);

        assertThatThrownBy(() -> pawn.move(nextPosition)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 직진만 가능하다")
    void notMove() {
        ChessBoardPosition initialPosition = new ChessBoardPosition('a', 2);
        Pawn pawn = new Pawn(Team.BLACK, initialPosition);
        ChessBoardPosition nextPosition = new ChessBoardPosition('b', 3);

        assertThatThrownBy(() -> pawn.move(nextPosition)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("diagonalParameters")
    @DisplayName("폰은 한 칸 또는 두 칸 전진한다.")
    void diagonalMoveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Pawn pawn = new Pawn(team, initialPosition);
        pawn.moveDiagonal(nextPosition);
        assertThat(pawn.isSamePosition(nextPosition)).isTrue();
    }

    private static Stream<Arguments> diagonalParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('b', 2),
                        new ChessBoardPosition('a', 3), "흑팀의 폰이 오른쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.BLACK, new ChessBoardPosition('b', 2),
                        new ChessBoardPosition('c', 3), "흑팀의 폰은 왼쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('b', 7),
                        new ChessBoardPosition('a', 6), "백팀의 폰은 왼쪽 대각선으로 한 칸 이동한다"),
                Arguments.of(Team.WHITE, new ChessBoardPosition('b', 7),
                        new ChessBoardPosition('c', 6), "백팀의 폰은 오른쪽 대각선으로 한 칸 이동한다")
        );
    }
}
