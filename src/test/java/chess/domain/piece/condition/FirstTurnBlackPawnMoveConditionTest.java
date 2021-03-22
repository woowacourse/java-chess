package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackPawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class FirstTurnBlackPawnMoveConditionTest {

    @DisplayName("검정 폰의 첫 움직임을 확인한다.")
    @Test
    void isSatisfyBy() {
        FirstTurnBlackPawnMoveCondition condition = new FirstTurnBlackPawnMoveCondition();
        Board board = new Board(Collections.singletonList(
                BlackPawn.createWithCoordinate(0, 0)
        ));
        boolean rightActual = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(2, 0));
        boolean falseActual = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(3, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("검정 폰의 첫 이동경로에 장애물이 있으면 안된다.")
    @Test
    void isSatisfyBy_false() {
        FirstTurnBlackPawnMoveCondition condition = new FirstTurnBlackPawnMoveCondition();
        Board board = new Board(Arrays.asList(
                BlackPawn.createWithCoordinate(0, 0),
                BlackPawn.createWithCoordinate(1, 0)
        ));
        boolean actual = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(2, 0));

        assertThat(actual).isFalse();
    }
}