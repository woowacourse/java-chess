package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackQueen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveConditionTest {

    @DisplayName("보드를 벗어나면 안된다.")
    @Test
    void isSatisfiedBy_outOfBoardBoundTest() {
        BishopMoveCondition bishopMoveCondition = new BishopMoveCondition();
        Board board = new Board(
                Collections.singletonList(
                        BlackQueen.createWithCoordinate(0, 0)
                )
        );

        boolean actualLeft = bishopMoveCondition.isSatisfiedBy(board, BlackQueen.createWithCoordinate(0, 0),
                new Position(-1, 0));

        boolean actualDown = bishopMoveCondition.isSatisfiedBy(board, BlackQueen.createWithCoordinate(0, 0),
                new Position(8, 0));

        boolean actualUp = bishopMoveCondition.isSatisfiedBy(board, BlackQueen.createWithCoordinate(0, 0),
                new Position(0, -1));

        boolean actualRight = bishopMoveCondition.isSatisfiedBy(board, BlackQueen.createWithCoordinate(0, 0),
                new Position(0, 8));


        assertThat(actualLeft).isFalse();
        assertThat(actualDown).isFalse();
        assertThat(actualUp).isFalse();
        assertThat(actualRight).isFalse();
    }

}
