package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("나이트는 상하좌우로 두 칸 전진한 상태에서 좌우로 한 칸 움직인다.")
    void initialMoveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Knight knight = new Knight(team, initialPosition);
        knight.move(nextPosition);
        assertThat(knight.isSamePosition(nextPosition)).isTrue();
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('f', 5), "흑팀의 나이트는 두 칸 전진하고 좌로 한칸 움직인다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('d', 5), "흑팀의 나이트는 두 칸 전진하고 우로 한칸 움직인다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('d', 1), "흑팀의 나이트는 두 칸 후진하고 좌로 한칸 움직인다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('f', 1), "흑팀의 나이트는 두 칸 후진하고 우로 한칸 움직인다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('g', 2), "흑팀의 나이트는 두 칸 왼쪽으로 이동하고 좌로 한칸 움직인다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('g', 4), "흑팀의 나이트는 두 칸 왼쪽으로 이동하고 우로 한칸 움직인다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('c', 4), "흑팀의 나이트는 두 칸 오른쪽으로 이동하고 좌로 한칸 움직인다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('c', 2), "흑팀의 나이트는 두 칸 오른쪽으로 이동하고 우로 한칸 움직인다.")
        );
    }
}
