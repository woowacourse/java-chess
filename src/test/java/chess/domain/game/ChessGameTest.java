package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createInitChessBoard();
        chessGame = new ChessGame(
                board,
                new GameSwitch(true),
                new Turn(Team.WHITE)
        );
    }

    @Test
    @DisplayName("본인 턴이 아닌경우 에러를 발생한다.")
    void validateTurn() {
        assertThatThrownBy(() -> chessGame.move("a7", "a6"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 당신의 차례가 아닙니다.");
    }

    @Test
    @DisplayName("기물이 이동할 수 없으면 에러를 발생한다.")
    void validateMove() {
        assertThatThrownBy(() -> chessGame.move("a1", "a2"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 기물을 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("King 이 죽으면 GameSwitch 가 꺼진다.")
    void turnOffWhenKingDie() {
        board.movePiece(Position.valueOf("e8"), Position.valueOf("e2"));

        chessGame.move("e1", "e2");

        assertThat(chessGame.isOn()).isFalse();
    }
}
