package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board(BoardFactory.initialize()));
    }

    @Test
    @DisplayName("기물 이동후 턴이 바뀐다.")
    void move() {
        chessGame.move("a2", "a4");
        assertThatThrownBy(() -> chessGame.move("a4", "a5"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 당신의 차례가 아닙니다.");
    }

    @Test
    @DisplayName("본인 턴이 아닌경우 에러를 발생한다.")
    void moveSourceOutOfPositionBound() {
        assertThatThrownBy(() -> chessGame.move("a7", "a6"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 당신의 차례가 아닙니다.");
    }

    @Test
    @DisplayName("기물이 이동할 수 없으면 에러를 발생한다.")
    void pieceIsNotMovable() {
        assertThatThrownBy(() -> chessGame.move("a1", "a2"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료")
    void turnOff() {
        chessGame.turnOff();
        assertThat(chessGame.isOn()).isFalse();
    }

    @Nested
    @DisplayName("custom 체스보드로 테스트한다.")
    class CustomBoardTest {

        @Test
        @DisplayName("왕이 죽으면 게임이 종료된다.")
        void turnOffWhenKingDie() {
            Map<Position, Piece> customBoard = new HashMap<>();
            customBoard.put(Position.valueOf("a8"), new King(Team.BLACK));
            customBoard.put(Position.valueOf("b7"), new Pawn(Team.WHITE));
            ChessGame chessGame = new ChessGame(new Board(customBoard));

            chessGame.move("b7", "a8");
            assertThat(chessGame.isOn()).isFalse();
        }
    }

}
