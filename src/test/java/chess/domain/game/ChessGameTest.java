package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @Nested
    @DisplayName("예외상황을 모두 테스트한다.")
    class MoveExceptions {
        ChessGame chessGame;

        @BeforeEach
        void setUp() {
            chessGame = new ChessGame(Board.create());
        }

        @Test
        @DisplayName("본인 턴이 아닌경우 에러를 발생한다.")
        void moveSourceOutOfPositionBound() {
            assertThatThrownBy(() -> chessGame.move(Position.valueOf("a7"), Position.valueOf("a6"), new Turn()))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("[ERROR] 당신의 차례가 아닙니다.");
        }

        @Test
        @DisplayName("기물이 이동할 수 없으면 에러를 발생한다.")
        void pieceIsNotMovable() {
            assertThatThrownBy(() -> chessGame.move(Position.valueOf("a1"), Position.valueOf("a2"), new Turn()))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("[ERROR] 이동할 수 없습니다.");
        }
    }

    @Test
    @DisplayName("폰이 수직으로 존재할 경우 총점을 계산한다.")
    void calculateScoreWhenPawnVertical() {
        Board board = Board.create();
        board.movePiece(Position.valueOf("a2"), Position.valueOf("b3"));
        board.movePiece(Position.valueOf("c2"), Position.valueOf("b4"));

        ChessGame chessGame = new ChessGame(board);
        assertThat(chessGame.calculateScore(Color.WHITE)).isEqualTo(36.5);
    }

    @Test
    @DisplayName("queen이 없고 폰이 수직으로 존재하는 경우 총점을 계산한다.")
    void calculateScoreWhenNoQueenPawnVertical() {
        Board board = Board.create();
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"));

        ChessGame chessGame = new ChessGame(board);
        assertThat(chessGame.calculateScore(Color.WHITE)).isEqualTo(28);
    }

}
