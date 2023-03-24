package chess.domain;

import chess.domain.exception.InvalidTurnException;
import chess.domain.exception.StartCommandException;
import chess.dto.SquareMoveDto;
import chess.view.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setup() {
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("게임 시작 시 start를 입력하면 게임을 실행한다.")
    void start_command_success_when_input_start() {
        assertThatCode(() -> chessGame.start(Command.START))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임 시작 시 end를 입력하면 예외를 발생한다.")
    void start_command_fail_when_input_end() {
        assertThatThrownBy(() -> chessGame.start(Command.END))
                .isInstanceOf(StartCommandException.class);
    }

    @Test
    @DisplayName("게임 시작 시 move를 입력하면 예외를 발생한다.")
    void start_command_fail_when_input_move() {
        assertThatThrownBy(() -> chessGame.start(Command.MOVE))
                .isInstanceOf(StartCommandException.class);
    }

    @Test
    @DisplayName("처음에는 백팀의 말을 움직일 수 있다.")
    void first_move_might_be_white() {
        SquareMoveDto squareMoveDto = SquareMoveDto.from("a2", "a4");
        assertThatCode(() -> chessGame.move(squareMoveDto))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("처음에 흑팀의 말을 움직이려 하면 예외가 발생한다.")
    void when_move_black_piece_in_first_turn_it_might_be_fail() {
        SquareMoveDto squareMoveDto = SquareMoveDto.from("a7", "a5");
        assertThatThrownBy(() -> chessGame.move(squareMoveDto))
                .isInstanceOf(InvalidTurnException.class);
    }

    @Test
    @DisplayName("두번째에는 흑팀의 말을 움직일 수 있다.")
    void second_move_might_be_black() {
        SquareMoveDto firstSquareMoveDto = SquareMoveDto.from("a2", "a4");
        SquareMoveDto blackSquareMoveDto = SquareMoveDto.from("a7", "a5");

        assertThatCode(() -> {
            chessGame.move(firstSquareMoveDto);
            chessGame.move(blackSquareMoveDto);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("두번째에 백팀의 말을 움직이려 하면 예외가 발생한다.")
    void when_move_white_piece_in_second_turn_it_might_be_fail() {
        SquareMoveDto firstSquareMoveDto = SquareMoveDto.from("a2", "a4");
        SquareMoveDto whiteSquareMoveDto = SquareMoveDto.from("b2", "b4");

        assertThatThrownBy(() -> {
            chessGame.move(firstSquareMoveDto);
            chessGame.move(whiteSquareMoveDto);
        }).isInstanceOf(InvalidTurnException.class);
    }

    @Test
    @DisplayName("게임 진행 중에 start를 입력하면 게임을 재실행한다.")
    void start_command_success_when_restart() {
        assertThatCode(() -> chessGame.restart())
                .doesNotThrowAnyException();
    }
}