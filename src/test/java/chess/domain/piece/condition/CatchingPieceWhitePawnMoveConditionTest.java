package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CatchingPieceWhitePawnMoveConditionTest {

    @DisplayName("하얀 폰이 상대말을 잡는 움직임을 확인한다.")
    @Test
    void isSatisfyBy() {
        CatchingPieceWhitePawnMoveCondition condition = new CatchingPieceWhitePawnMoveCondition();
        Board board = new Board(Arrays.asList(
                Piece.createPawn(Color.WHITE, 7, 2),
                Piece.createRook(Color.BLACK, 6, 3)
        ));
        boolean rightActual = condition.isSatisfyBy(board, Piece.createPawn(Color.WHITE, 7, 2),
                new Position(6, 3));
        boolean falseActual = condition.isSatisfyBy(board, Piece.createRook(Color.BLACK, 6, 3),
                new Position(6, 1));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

}
