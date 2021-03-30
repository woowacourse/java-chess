package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
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
                Piece.createPawn(Color.WHITE, 0, 1)
        ));
        boolean rightActual = condition.isSatisfyBy(board, Piece.createPawn(Color.WHITE, 0, 1),
                new Position(7, 1));

        boolean falseActual = condition.isSatisfyBy(board, Piece.createPawn(Color.WHITE, 0, 1),
                new Position(7, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("룩의 이동경로에 장애물 있으면 안된다.")
    @Test
    void isSatisfyBy_false() {
        RookMoveCondition condition = new RookMoveCondition();
        Piece rook = Piece.createRook(Color.WHITE, 0, 1);
        Board board = new Board(Arrays.asList(
                rook,
                Piece.createPawn(Color.WHITE, 3, 1),
                Piece.createPawn(Color.WHITE, 0, 2)
        ));

        boolean actualCol = condition.isSatisfyBy(board, rook, new Position(0, 4));
        boolean actualRow = condition.isSatisfyBy(board, rook,
                new Position(7, 1));

        assertThat(actualCol).isFalse();
        assertThat(actualRow).isFalse();
    }
}
