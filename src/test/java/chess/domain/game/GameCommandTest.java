package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.exception.IllegalCommandException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameCommandTest {
    @DisplayName("start 커맨드가 입력되었을 때 True를 반환한다.")
    @Test
    void Should_True_When_GameCommandIsStart() {
        GameCommand gameCommand = new GameCommand(List.of("start"));

        assertDoesNotThrow(gameCommand::isStart);
    }

    @DisplayName("move 커맨드가 입력되었을 때 True를 반환한다.")
    @Test
    void Should_True_When_GameCommandIsMove() {
        GameCommand gameCommand = new GameCommand(List.of("move", "a1", "a2"));

        assertThat(gameCommand.isMove()).isTrue();
    }

    @DisplayName("status 커맨드가 입력되었을 때 True를 반환한다.")
    @Test
    void Should_GameCommandTest_When_GameCommandTest() {
        GameCommand gameCommand = new GameCommand(List.of("status"));

        assertThat(gameCommand.isStatus()).isTrue();
    }

    @DisplayName("move 커맨드를 좌표로 반환할 수 있다.")
    @Test
    void Should_ReturnSquareList_When_ConvertToSquare() {
        GameCommand gameCommand = new GameCommand(List.of("move", "a1", "a2"));

        assertThat(gameCommand.convertToSquare())
                .containsExactly(new Square(File.A, Rank.ONE), new Square(File.A, Rank.TWO));
    }

    @DisplayName("잘못된 게임 커맨드가 입력될 시 오류가 발생한다.")
    @Test
    void Should_ThrowException_When_OtherGameCommand() {
        assertThatThrownBy(() -> new GameCommand(List.of("kouz")))
                .isInstanceOf(IllegalCommandException.class)
                .hasMessage("올바른 커맨드를 입력해주세요.");
    }

    @DisplayName("잘못된 좌표가 입력될 시 오류가 발생한다.")
    @Test
    void Should_ThrowException_When_OtherMoveSquare() {
        assertThatThrownBy(() -> new GameCommand(List.of("move", "aa", "a3")))
                .isInstanceOf(IllegalCommandException.class)
                .hasMessage("올바른 커맨드를 입력해주세요.");
    }
}
