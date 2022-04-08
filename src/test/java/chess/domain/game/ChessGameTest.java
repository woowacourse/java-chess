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
                "ChessGame",
                board,
                new GameSwitch(true),
                new Turn(Team.WHITE)
        );
    }

    @Test
    @DisplayName("게임이 종료된 상태면 기물을 이동시킬 수 없다.")
    void validateGamSwitch() {
        chessGame.turnOff();

        assertThatThrownBy(() -> chessGame.move('a', 3, 'a', 4))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 종료되어 기물을 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("체스 기물을 이동시킬 때 기물이 없는 빈칸을 선택하면 예외를 발생시킨다.")
    void validateBlank() {
        assertThatThrownBy(() -> chessGame.move('a', 3, 'a', 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 체스 기물이 아닌 빈칸을 선택하였습니다.");
    }

    @Test
    @DisplayName("본인 턴이 아닌경우 에러를 발생한다.")
    void validateTurn() {
        assertThatThrownBy(() -> chessGame.move('a', 7, 'a', 6))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 당신의 차례가 아닙니다.");
    }

    @Test
    @DisplayName("기물이 이동할 수 없으면 에러를 발생한다.")
    void validateMove() {
        assertThatThrownBy(() -> chessGame.move('a', 1, 'a', 2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 기물을 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("King 이 죽으면 GameSwitch 가 꺼진다.")
    void turnOffWhenKingDie() {
        board.movePiece(Position.of('e', 8), Position.of('e', 2));

        chessGame.move('e', 1, 'e', 2);

        assertThat(chessGame.isOn()).isFalse();
    }
}
