package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusCommandTest {

    private StatusCommand statusCommand;

    @BeforeEach
    void setUp() {
        statusCommand = new StatusCommand(new ChessGame(new Board(PieceFactory.createPieces())));
    }

    @DisplayName("Status 명령어를 통해 점수를 가져온다.")
    @Test
    void execute() {
        assertThat(statusCommand.getWhiteScore()).isEqualTo(34);
        assertThat(statusCommand.getBlackScore()).isEqualTo(34);
    }

    @DisplayName("올바른 입력인지 확인한다.")
    @Test
    void isUsable() {
        assertThat(statusCommand.isUsable("status")).isTrue();
        assertThat(statusCommand.isUsable("status2")).isFalse();
    }

    @DisplayName("Status 인지 확인한다.")
    @Test
    void isStatus() {
        assertThat(statusCommand.isStatus()).isTrue();
    }
}