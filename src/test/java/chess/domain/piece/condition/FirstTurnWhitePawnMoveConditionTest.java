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

class FirstTurnWhitePawnMoveConditionTest {

    @DisplayName("하얀 폰의 첫 움직임을 확인한다.")
    @Test
    void isSatisfyBy() {
        FirstTurnWhitePawnMoveCondition condition = new FirstTurnWhitePawnMoveCondition();
        Board board = new Board(Collections.singletonList(
                Piece.createPawn(Color.WHITE, 7, 0)
        ));
        boolean rightActual = condition.isSatisfyBy(board, Piece.createPawn(Color.WHITE, 7, 0),
                new Position(5, 0));
        boolean falseActual = condition.isSatisfyBy(board, Piece.createPawn(Color.WHITE, 7, 0),
                new Position(4, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("하얀 폰의 첫 이동경로에 장애물이 있으면 안된다.")
    @Test
    void isSatisfyBy_false() {
        FirstTurnWhitePawnMoveCondition condition = new FirstTurnWhitePawnMoveCondition();
        Board board = new Board(Arrays.asList(
                Piece.createPawn(Color.WHITE, 6, 0),
                Piece.createPawn(Color.WHITE, 5, 0)
        ));
        boolean actual = condition.isSatisfyBy(board, Piece.createPawn(Color.WHITE, 6, 0),
                new Position(4, 0));

        assertThat(actual).isFalse();
    }

}