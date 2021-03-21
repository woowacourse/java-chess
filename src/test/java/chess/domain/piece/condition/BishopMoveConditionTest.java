package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackBishop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class BishopMoveConditionTest {

    @DisplayName("비숍이 조건대로 움직이는지 확인한다.")
    @Test
    void isSatisfyBy_checkMoveConditionIsRight() {
        BishopMoveCondition bishopMoveCondition = new BishopMoveCondition();
        Board board = new Board(
                Collections.singletonList(
                        BlackBishop.createWithCoordinate(0, 0)
                )
        );
        boolean rightActual = bishopMoveCondition.isSatisfyBy(board, BlackBishop.createWithCoordinate(0, 0),
                new Position(1, 1));
        boolean falseActual = bishopMoveCondition.isSatisfyBy(board, BlackBishop.createWithCoordinate(0, 0),
                new Position(1, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("비숍의 이동 경로에 장애물이 있으면 예외")
    @Test
    void isSatisfyBy_ifHasObstacleOnMovePathThenException() {
        BishopMoveCondition bishopMoveCondition = new BishopMoveCondition();
        Board board = new Board(
                Arrays.asList(
                        BlackBishop.createWithCoordinate(0, 0),
                        BlackBishop.createWithCoordinate(1, 1)
                )
        );

        boolean actual = bishopMoveCondition.isSatisfyBy(board, BlackBishop.createWithCoordinate(0, 0),
                new Position(2, 2));

        assertThat(actual).isFalse();
    }

}