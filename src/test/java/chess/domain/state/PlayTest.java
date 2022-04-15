package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
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
        assertThatThrownBy(play::start)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("Play 상태에서 end 명령어를 입력하면 end 상태로 변한다.")
    void executeEndCommand(Play play, String testName) {
        assertThat(play.end()).isInstanceOf(End.class);
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("parameters")
    @DisplayName("Play 상태에서 status 명령어를 입력하면 상태가 변하지 않는다.")
    void executeStatusCommand(Play play, String testName) {
        assertThat(play.status()).isInstanceOf(play.getClass());
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
    void execute(Play play, ChessBoardPosition source, ChessBoardPosition target, Class<Play> nextTurn,
                 String testName) {
        assertThat(play.move(source, target)).isInstanceOf(nextTurn);
    }

    private static Stream<Arguments> moveParameters() {
        ChessBoard chessBoard = ChessBoard.create();
        return Stream.of(
                Arguments.of(new WhiteTurn(chessBoard), ChessBoardPosition.from("b2"), ChessBoardPosition.from("b4"),
                        BlackTurn.class, "백팀 차례의 Play 상태"),
                Arguments.of(new BlackTurn(chessBoard), ChessBoardPosition.from("b7"), ChessBoardPosition.from("b5"),
                        WhiteTurn.class, "흑팀 차례의 Play 상태")
        );
    }
}
