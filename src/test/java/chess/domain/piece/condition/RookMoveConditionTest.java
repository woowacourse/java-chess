package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.white.WhitePawn;
import chess.domain.piece.white.WhiteRook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class RookMoveConditionTest {

    @DisplayName("룩이 조건대로 움직이는지 확인한다.")
    @Test
    void isSatisfyBy() {
        RookMoveCondition condition = new RookMoveCondition();
        Board board = new Board(Collections.singletonList(
                WhiteRook.createWithCoordinate(0, 1)
        ));
        boolean rightActual = condition.isSatisfyBy(board, WhiteRook.createWithCoordinate(0, 1),
                new Position(7, 1));

        boolean falseActual = condition.isSatisfyBy(board, WhiteRook.createWithCoordinate(0, 1),
                new Position(7, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("룩의 이동경로에 장애물 있으면 안된다.")
    @Test
    void isSatisfyBy_false() {
        RookMoveCondition condition = new RookMoveCondition();
        Board board = new Board(Arrays.asList(
                WhiteRook.createWithCoordinate(0, 1),
                WhitePawn.createWithCoordinate(3, 1),
                WhitePawn.createWithCoordinate(0, 2)
        ));

        boolean actualCol = condition.isSatisfyBy(board, WhiteRook.createWithCoordinate(0, 1),
                new Position(0, 4));
        boolean actualRow = condition.isSatisfyBy(board, WhiteRook.createWithCoordinate(0, 1),
                new Position(7, 1));

        assertThat(actualCol).isFalse();
        assertThat(actualRow).isFalse();
    }
}