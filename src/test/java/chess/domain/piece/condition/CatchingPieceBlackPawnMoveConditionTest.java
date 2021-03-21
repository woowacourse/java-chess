package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackPawn;
import chess.domain.piece.white.WhitePawn;
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
                BlackPawn.createWithCoordinate(0, 2),
                WhitePawn.createWithCoordinate(1, 3)
        ));
        boolean rightActual = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 2),
                new Position(1, 3));
        boolean falseActual = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 2),
                new Position(1, 1));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("보드를 벗어나면 안된다.")
    @Test
    void isSatisfyBy_outOfBoardBoundTest() {
        CatchingPieceBlackPawnMoveCondition condition = new CatchingPieceBlackPawnMoveCondition();
        Board board = new Board(
                Arrays.asList(
                        BlackPawn.createWithCoordinate(0, 0)
                )
        );

        boolean actualLeft = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(-1, 0));

        boolean actualDown = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(8, 0));

        boolean actualUp = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(0, -1));

        boolean actualRight = condition.isSatisfyBy(board, BlackPawn.createWithCoordinate(0, 0),
                new Position(0, 8));


        assertThat(actualLeft).isFalse();
        assertThat(actualDown).isFalse();
        assertThat(actualUp).isFalse();
        assertThat(actualRight).isFalse();
    }
}