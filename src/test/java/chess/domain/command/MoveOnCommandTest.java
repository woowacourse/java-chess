package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.piece.*;
import chess.domain.piece.info.Color;
import chess.domain.position.Position;
import chess.domain.state.Running;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoveOnCommandTest {
    private MoveOnCommand moveOnCommand;

    @BeforeEach
    void setUp() {
        moveOnCommand = new MoveOnCommand();
    }

    @DisplayName("게임 시작 전 상태일시 예외가 발생한다.")
    @Test
    void 게임_시작_전_실행시_예외() {
        ChessGame chessGame = new ChessGame();
        String[] splitCommand = new String[]{"move", "a2", "a3"};
        assertThatThrownBy(() -> moveOnCommand.execute(chessGame, splitCommand))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("move source위치 target위치 형식이 아닐 시 예외")
    @Test
    void 형식_예외() {
        ChessGame chessGame = new ChessGame();
        String[] splitCommand = new String[]{"move", "a2", "a3", "23"};
        assertThatThrownBy(() -> moveOnCommand.execute(chessGame, splitCommand))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("move 명령어를 실행한다.")
    @Test
    void move를_실행() {
        List<Piece> current = Arrays.asList(
                new King(Color.BLACK, Position.of("e8")),
                new Bishop(Color.WHITE, Position.of("c8")),
                new Pawn(Color.BLACK, Position.of("f5")));
        ChessGame chessGame = new ChessGame();
        String[] splitCommand = new String[]{"move", "a2", "a3"};
        chessGame.start();
        moveOnCommand.execute(chessGame, splitCommand);
        boolean result = chessGame.getPiecesByAllPosition()
                .stream()
                .anyMatch(piece -> piece.position()
                                .equals(Position.of("a2")) && piece.isEmpty());

        assertThat(result).isTrue();
    }

    @DisplayName("체스 보드를 출력해야한다.")
    @Test
    void 체스보드_출력_함() {
        boolean isMustShowBoard = moveOnCommand.isMustShowBoard();

        assertThat(isMustShowBoard).isTrue();
    }

    @DisplayName("체스 보드 상태를 출력하지 않아도 된다.")
    @Test
    void 체스보드_상태_출력_안함() {
        boolean isMustShowStatus = moveOnCommand.isMustShowStatus();

        assertThat(isMustShowStatus).isFalse();
    }
}
