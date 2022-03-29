package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveTest {

    Move setUpBlack() {
        Board board = new Board(BoardInitializer.create());
        return new Move(board, Color.BLACK);
    }

    Move setUpWhite() {
        Board board = new Board(BoardInitializer.create());
        return new Move(board, Color.WHITE);
    }

    @Test
    @DisplayName("source 위치와 target 위치가 같은 경우 예외를 발생시킨다.")
    void exceptionSamePosition() {
        Move move = setUpWhite();
        Position from = Position.create("c2");
        Position to = Position.create("c2");

        assertThatThrownBy(() -> move.isMovable(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] source 위치와 target 위치가 같을 수 없습니다.");
    }

    @Test
    @DisplayName("자신의 기물이 아닐 경우 예외를 발생시킨다.")
    void exceptionTurn() {
        Move move = setUpBlack();
        Position from = Position.create("b2");
        Position to = Position.create("b3");

        assertThatThrownBy(() -> move.isMovable(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 자신의 기물만 이동시킬 수 있습니다.");
    }

    @Test
    @DisplayName("source 위치에 기물이 없는 경우 예외를 발생시킨다.")
    void exceptionEmptySource() {
        Move move = setUpWhite();
        Position from = Position.create("a3");
        Position to = Position.create("a4");

        assertThatThrownBy(() -> move.isMovable(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] source 위치에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("기물이 행마법에 맞지 않을 경우 예외를 발생시킨다.")
    void exceptionIllegalMovement() {
        Move move = setUpWhite();
        Position from = Position.create("b1");
        Position to = Position.create("b3");

        assertThatThrownBy(() -> move.isMovable(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 행마법에 맞지 않는 이동입니다.");
    }

    @Test
    @DisplayName("target 위치의 기물이 자신의 기물일 경우 예외를 발생시킨다.")
    void exceptionTargetColor() {
        Move move = setUpWhite();
        Position from = Position.create("a1");
        Position to = Position.create("a2");

        assertThatThrownBy(() -> move.isMovable(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 자신의 기물이 있는 곳으로 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("이동 경로에 기물이 있으면 예외를 발생시킨다.")
    void exceptionBlockedMove() {
        Move move = setUpWhite();
        Position from = Position.create("a1");
        Position to = Position.create("a7");

        assertThatThrownBy(() -> move.isMovable(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이동 경로에 기물이 있어 이동할 수 없습니다.");
    }
}
