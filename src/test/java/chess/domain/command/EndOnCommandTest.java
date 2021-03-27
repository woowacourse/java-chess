package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.piece.Bishop;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import chess.domain.position.Position;
import chess.domain.state.WhiteTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EndOnCommandTest {
    private EndOnCommand endOnCommand;
    @BeforeEach
    void setUp() {
        endOnCommand = new EndOnCommand();
    }

    @DisplayName("end 명령어를 실행한다.")
    @Test
    void end를_실행() {
        ChessGame chessGame = new ChessGame();
        String[] splitCommand = new String[]{"end"};

        endOnCommand.execute(chessGame, splitCommand);

        assertThat(chessGame.runnable()).isFalse();
    }

    @DisplayName("체스 보드를 출력해야한다.")
    @Test
    void 체스보드_출력_함() {
        boolean isMustShowBoard = endOnCommand.isMustShowBoard();

        assertThat(isMustShowBoard).isTrue();
    }

    @DisplayName("체스 보드 상태를 출력하지 않아도 된다.")
    @Test
    void 체스보드_상태_출력_안함() {
        boolean isMustShowStatus = endOnCommand.isMustShowStatus();

        assertThat(isMustShowStatus).isFalse();
    }
}
