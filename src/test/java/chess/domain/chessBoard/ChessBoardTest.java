package chess.domain.chessBoard;

import chess.domain.chessBoard.generator.ChessSpaceGenerator;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {

    private ChessBoard chessBoard;
    private Position from;
    private Position to;

    @BeforeEach
    void beforeEach() {
        chessBoard = new ChessBoard(new ChessSpaceGenerator());
        from = Position.fromCoordinate(Coordinate.of("b2"));
        to = Position.fromCoordinate(Coordinate.of("b3"));
    }

    @Test
    @DisplayName("게임 중인 상태가 아니면 피스를 움직일 수 없다")
    void should_not_move_when_not_playing_game() {
        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 활성화되지 않았습니다");

    }

    @Test
    @DisplayName("해당 색의 턴이 아니면 피스를 움직일 수 없다")
    void should_not_move_when_not_own_color_turn() {
        Position blackFrom = Position.fromCoordinate(Coordinate.of("b7"));
        Position blackTo = Position.fromCoordinate(Coordinate.of("b6"));
        chessBoard.startGame();

        assertThatThrownBy(() -> chessBoard.move(blackFrom, blackTo))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("상대 플레이어의 차례입니다");

    }

    @Test
    @DisplayName("게임을 시작하고 해당 색의 턴이면 피스를 움직일 수 있다")
    void should_move_when_start_and_own_color_turn() {
        chessBoard.startGame();

        assertThatCode(() -> chessBoard.move(from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임을 끝내면 더이상 피스를 움직일 수 있다")
    void should_not_move_when_end() {
        chessBoard.startGame();
        chessBoard.endGame();

        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 활성화되지 않았습니다");
    }
}
