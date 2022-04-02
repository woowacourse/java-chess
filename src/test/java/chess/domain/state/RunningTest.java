package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Location;
import chess.domain.board.Rank;

import chess.domain.piece.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RunningTest {

    @Test
    @DisplayName("white상태에서 start를 하면 예외가 발생한다.")
    void whiteStartTest() {
        State state = new White(new Board());
        assertThatThrownBy(state::start).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("white상태에서 end를 하면 예외가 발생한다.")
    void WhiteEndTest() {
        State state = new White(new Board());
        assertThat(state.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("White상태에서 getBoard를 실행하면 보드를 가져온다.")
    void WhiteGetBoardTest() {
        State state = new White(new Board());
        assertThat(state.getBoard()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("White 상태에서 isRunning을 실행하면 true가 반환된다.")
    void WhiteIsRunningTest() {
        State state = new White(new Board());
        assertThat(state.isRunning()).isTrue();
    }

    @Test
    @DisplayName("White 상태에서 White가 아닌 기물을 움직이면 예외발생.")
    void WhiteMoveTest() {
        State state = new White(new Board());
        assertThatThrownBy(
                () -> state.move(Team.WHITE, Location.of(File.F, Rank.SEVEN), Location.of(File.F, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("색이 다른 말을 움직이지 못하는가?")
    @MethodSource
    void checkSourcePiece(Location source, Location target) {
        White white = new White(new Board());
        assertThatThrownBy(() -> white.move(Team.WHITE, source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> checkSourcePiece() {
        return Stream.of(
                Arguments.arguments(Location.of("a3"), Location.of("a4")),
                Arguments.arguments(Location.of("a7"), Location.of("a5")));
    }

    @ParameterizedTest
    @DisplayName("체스말이 다른 방향으로 움직이지 못하는가?")
    @MethodSource
    void checkDirection(Location source, Location target) {
        White white = new White(new Board());
        assertThatThrownBy(() -> white.move(Team.WHITE, source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> checkDirection() {
        return Stream.of(
                Arguments.arguments(Location.of("a2"), Location.of("b4")),
                Arguments.arguments(Location.of("a1"), Location.of("b2")),
                Arguments.arguments(Location.of("b1"), Location.of("b2")),
                Arguments.arguments(Location.of("c1"), Location.of("c2"))
        );
    }

    @ParameterizedTest
    @DisplayName("체스말이 정해진 거리이상으로 움직이지 못하는가?")
    @MethodSource
    void checkDistance(Location source, Location target) {
        White white = new White(new Board());
        assertThatThrownBy(() -> white.move(Team.WHITE, source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> checkDistance() {
        return Stream.of(
                Arguments.arguments(Location.of("a2"), Location.of("a5")),
                Arguments.arguments(Location.of("a1"), Location.of("b2")),
                Arguments.arguments(Location.of("b1"), Location.of("b5")),
                Arguments.arguments(Location.of("c1"), Location.of("c7"))
        );
    }
}
