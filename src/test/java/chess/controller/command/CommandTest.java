package chess.controller.command;

import chess.controller.ChessManager;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @DisplayName("올바른 start 명령어 입력")
    @Test
    void of_success1() {
        assertThat(Command.of("start")).isEqualTo(Command.START);
    }

    @DisplayName("올바른 end 명령어 입력")
    @Test
    void of_success2() {
        assertThat(Command.of("end")).isEqualTo(Command.END);
    }

    @DisplayName("올바른 move 명령어 입력")
    @Test
    void of_success3() {
        assertThat(Command.of("move a2 a4")).isEqualTo(Command.MOVE);
    }

    @DisplayName("올바른 status 명령어 입력")
    @Test
    void of_success4() {
        assertThat(Command.of("status")).isEqualTo(Command.STATUS);
    }

    @DisplayName("잘못된 명령어 입력")
    @Test
    void of_fail() {
        assertThatThrownBy(() -> Command.of("무브 a2 a4"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("시작 명령어 검증")
    @Test
    void validateStartCommand() {
        assertThatThrownBy(() -> Command.validateStartCommand("시작"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임 중 명령어 검증")

    @Test
    void validateContinueCommand() {
        assertThatThrownBy(() -> Command.validateContinueCommand("무브"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임 시작")
    @Test
    void applyStart() {
        ChessManager chessManager = new ChessManager();
        Command.START.apply(chessManager, "");

        assertThat(chessManager.isPlaying()).isTrue();
    }

    @DisplayName("게임 종료")
    @Test
    void applyEnd() {
        ChessManager chessManager = new ChessManager();
        Command.END.apply(chessManager, "");

        assertThat(chessManager.isPlaying()).isFalse();
    }

    @DisplayName("말 이동")
    @Test
    void applyMove() {
        ChessManager chessManager = new ChessManager();
        Command.MOVE.apply(chessManager, "move a2 a4");

        assertThat(chessManager.getCurrentTeam()).isEqualTo(Team.BLACK);
    }

    @DisplayName("팀 점수 출력")
    @Test
    void applyStatus() {
        ChessManager chessManager = new ChessManager();
        Command.MOVE.apply(chessManager, "move a2 a4");
        Command.MOVE.apply(chessManager, "move b7 b5");
        Command.MOVE.apply(chessManager, "move a4 b5");

        Command.STATUS.apply(chessManager, "");

        assertThat(chessManager.calculateScore()).isEqualTo(37.0);
    }
}