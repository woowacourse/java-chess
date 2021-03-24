package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CatchingPieceBlackPawnMoveConditionTest {

    @DisplayName("검정 폰이 상대말을 잡는 움직임을 확인한다.")
    @Test
    void isSatisfyBy() {
        CatchingPieceBlackPawnMoveCondition condition = new CatchingPieceBlackPawnMoveCondition();
        Board board = new Board(Arrays.asList(
                Piece.createPawn(Color.BLACK, 0, 2),
                Piece.createRook(Color.WHITE, 1, 3)
        ));
        boolean rightActual = condition.isSatisfyBy(board, Piece.createPawn(Color.BLACK, 0, 2),
                new Position(1, 3));
        boolean falseActual = condition.isSatisfyBy(board, Piece.createPawn(Color.BLACK, 0, 2),
                new Position(1, 1));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }
}
