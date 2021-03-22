package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackKnight;
import chess.domain.piece.black.BlackRook;
import chess.domain.piece.white.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CatchingPieceWhitePawnMoveConditionTest {

    @DisplayName("하얀 폰이 상대말을 잡는 움직임을 확인한다.")
    @Test
    void isSatisfiedBy() {
        CatchingPieceWhitePawnMoveCondition condition = new CatchingPieceWhitePawnMoveCondition();
        Board board = new Board(Arrays.asList(
                WhitePawn.createWithCoordinate(7, 2),
                BlackKnight.createWithCoordinate(6, 3)
        ));
        boolean rightActual = condition.isSatisfiedBy(board, WhitePawn.createWithCoordinate(7, 2),
                new Position(6, 3));
        boolean falseActual = condition.isSatisfiedBy(board, BlackRook.createWithCoordinate(6, 3),
                new Position(6, 1));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

}