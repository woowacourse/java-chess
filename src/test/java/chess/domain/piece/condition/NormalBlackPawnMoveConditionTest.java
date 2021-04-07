package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackPawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class NormalBlackPawnMoveConditionTest {

    @DisplayName("검정 폰의 기본 움직임을 확인한다.")
    @Test
    void isSatisfiedBy() {
        NormalBlackPawnMoveCondition condition = new NormalBlackPawnMoveCondition();
        Board board = new Board(Collections.singletonList(
                BlackPawn.createWithCoordinate(0, 0)
        ));
        boolean rightActual = condition.isSatisfiedBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(1, 0));
        boolean falseActual = condition.isSatisfiedBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(2, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("검은 폰의 이동 경로에 장애물이 있는지 확인")
    @Test
    void isSatisfiedBy_false() {
        NormalBlackPawnMoveCondition condition = new NormalBlackPawnMoveCondition();
        Board board = new Board(Arrays.asList(
                BlackPawn.createWithCoordinate(0, 0),
                BlackPawn.createWithCoordinate(1, 0)

        ));
        boolean actual = condition.isSatisfiedBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(1, 0));

        assertThat(actual).isFalse();
    }

}