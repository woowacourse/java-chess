package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.gamestate.Ready;
import chess.domain.piece.Blank;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static chess.domain.game.ChessGame.NO_MOVEMENT_ERROR;
import static chess.domain.game.ChessGame.TURN_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new ChessBoard(), Color.WHITE, new Ready());
        chessGame.initBoard();
    }

    @Test
    @DisplayName("킹이 죽으면 게임 끝")
    void killKing() {
        ChessBoard chessBoard = chessGame.getChessBoard();
        chessBoard.replace(Position.of("e1"), new Blank(Position.of("e1")));

        assertThat(chessGame.isOngoing()).isFalse();
    }

    @DisplayName("움직이지 않는 입력이 주어졌을 때 에러를 반환하는지")
    @Test
    void validate_noMovement_throwError() {
        AssertionsForClassTypes.assertThatThrownBy(() -> chessGame.movePiece(Arrays.asList("move", "b2", "b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NO_MOVEMENT_ERROR);
    }

    @Test
    @DisplayName("턴이 아닐 때 오류 발생")
    void validateTurn() {
        ChessGame chessGame = new ChessGame(new ChessBoard(), Color.WHITE, new Ready());
        chessGame.initBoard();
        assertThatThrownBy(() -> chessGame.movePiece(Arrays.asList("move", "b7", "b6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(TURN_MESSAGE, Color.WHITE));
    }
}
