package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.white.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class FirstTurnWhitePawnMoveConditionTest {

    @DisplayName("하얀 폰의 첫 움직임을 확인한다.")
    @Test
    void isSatisfiedBy() {
        FirstTurnWhitePawnMoveCondition condition = new FirstTurnWhitePawnMoveCondition();
        Board board = new Board(Collections.singletonList(
                WhitePawn.createWithCoordinate(7, 0)
        ));
        boolean rightActual = condition.isSatisfiedBy(board, WhitePawn.createWithCoordinate(7, 0),
                new Position(5, 0));
        boolean falseActual = condition.isSatisfiedBy(board, WhitePawn.createWithCoordinate(7, 0),
                new Position(4, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("하얀 폰의 첫 이동경로에 장애물이 있으면 안된다.")
    @Test
    void isSatisfiedBy_false() {
        FirstTurnWhitePawnMoveCondition condition = new FirstTurnWhitePawnMoveCondition();
        Board board = new Board(Arrays.asList(
                WhitePawn.createWithCoordinate(6, 0),
                WhitePawn.createWithCoordinate(5, 0)
        ));
        boolean actual = condition.isSatisfiedBy(board, WhitePawn.createWithCoordinate(6, 0),
                new Position(4, 0));

        assertThat(actual).isFalse();
    }

}