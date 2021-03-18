package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveConditionTest {

    @DisplayName("보드를 벗어나면 안된다.")
    @Test
    void isSatisfyBy_outOfBoardBoundTest() {
        BishopMoveCondition bishopMoveCondition = new BishopMoveCondition();
        Board board = new Board(
                Arrays.asList(
                        Piece.createQueen(Color.BLACK, 0, 0)
                )
        );

        boolean actualLeft = bishopMoveCondition.isSatisfyBy(board, Piece.createBishop(Color.BLACK, 0, 0),
                new Position(-1, 0));

        boolean actualDown = bishopMoveCondition.isSatisfyBy(board, Piece.createBishop(Color.BLACK, 0, 0),
                new Position(8, 0));

        boolean actualUp = bishopMoveCondition.isSatisfyBy(board, Piece.createBishop(Color.BLACK, 0, 0),
                new Position(0, -1));

        boolean actualRight = bishopMoveCondition.isSatisfyBy(board, Piece.createBishop(Color.BLACK, 0, 0),
                new Position(0, 8));


        assertThat(actualLeft).isFalse();
        assertThat(actualDown).isFalse();
        assertThat(actualUp).isFalse();
        assertThat(actualRight).isFalse();
    }

}
