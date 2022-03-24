package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("퀸은 전후좌우, 대각선 방향으로 한 칸씩 움직인다.")
    void initialMoveTest(Team team, ChessBoardPosition initialPosition, ChessBoardPosition nextPosition) {
        King king = new King(team, initialPosition);
        king.move(nextPosition);
        assertThat(king.isSamePosition(nextPosition)).isTrue();
    }

    private static Stream<Arguments> moveParameters() {
        return Stream.of(
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('e', 4), "킹이 앞으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('e', 2), "킹이 뒤로 한 칸 이동한다"),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('f', 3), "킹이 왼쪽으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('d', 3), "킹이 오른쪽으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('f', 2), "킹이 북동방향으로 한 칸 이동한다"),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('d', 2), "킹이 북서방향으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('f', 4), "킹이 남동방향으로 한 칸 이동한다."),
                Arguments.of(Team.BLACK, new ChessBoardPosition('e', 3),
                        new ChessBoardPosition('d', 2), "킹이 남서방향으로 한 칸 이동한다.")
        );
    }
}
