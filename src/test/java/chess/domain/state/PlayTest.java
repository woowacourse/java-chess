package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Command;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("Play 상태에서 start 명령어를 입력하면 오류가 발생한다.")
    void executeStartCommand(Play play, String testName) {
        Command command = Command.START;
        List<String> input = List.of("start");

        assertThatThrownBy(() -> play.execute(command, input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("Play 상태에서 end 명령어를 입력하면 오류가 발생한다.")
    void executeEndCommand(Play play, String testName) {
        Command command = Command.END;
        List<String> input = List.of("end");

        assertThatThrownBy(() -> play.execute(command, input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("Play 상태에서 status 명령어를 입력하면 상태가 변하지 않는다.")
    void executeStatusCommand(Play play, String testName) {
        Command command = Command.STATUS;
        List<String> input = List.of("status");
        GameState gameState = play.execute(command, input);

        assertThat(gameState).isInstanceOf(play.getClass());
    }

    private static Stream<Arguments> parameters() {
        ChessBoard chessBoard = ChessBoard.create();
        return Stream.of(
                Arguments.of(new WhiteTurn(chessBoard), "백팀 차례의 Play 상태"),
                Arguments.of(new BlackTurn(chessBoard), "흑팀 차례의 Play 상태")
        );
    }

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("moveParameters")
    @DisplayName("Play 상태에서 move 명령어를 실행하면 차례가 반전된다.")
    void execute(Play play, List<String> input, Class<Play> nextTurn, String testName) {
        Command command = Command.MOVE;
        GameState gameState = play.execute(command, input);

        assertThat(gameState).isInstanceOf(nextTurn);
    }

    private static Stream<Arguments> moveParameters() {
        ChessBoard chessBoard = ChessBoard.create();
        return Stream.of(
                Arguments.of(new WhiteTurn(chessBoard), List.of("move", "b2", "b4"), BlackTurn.class, "백팀 차례의 Play 상태"),
                Arguments.of(new BlackTurn(chessBoard), List.of("move", "b7", "b5"), WhiteTurn.class, "흑팀 차례의 Play 상태")
        );
    }
}
