package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {
    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("비숍은 대각선 방향으로 칸 수의 제한 없이 움직인다. ")
    void initialMoveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        Bishop bishop = new Bishop(team, initialPosition);
        bishop.move(nextPosition);
        assertThat(bishop.isSamePosition(nextPosition)).isTrue();
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('g', 1), "흑팀의 비숍은 1사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('c', 1), "흑팀의 비숍은 2사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('a', 7), "흑팀의 비숍은 3사분면으로 끝까지 간다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('h', 6), "흑팀의 비숍은 4사분면으로 끝까지 간다.")
        );
    }
}
