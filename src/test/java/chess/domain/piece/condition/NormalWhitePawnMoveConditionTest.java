package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.white.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class NormalWhitePawnMoveConditionTest {

    @DisplayName("하얀 폰의 기본 움직임을 확인한다.")
    @Test
    void isSatisfyBy() {
        NormalWhitePawnMoveCondition condition = new NormalWhitePawnMoveCondition();
        Board board = new Board(Collections.singletonList(
                WhitePawn.createWithCoordinate(7, 0)
        ));
        boolean rightActual = condition.isSatisfyBy(board, WhitePawn.createWithCoordinate(7, 0),
                new Position(6, 0));
        boolean falseActual = condition.isSatisfyBy(board, WhitePawn.createWithCoordinate(7, 0),
                new Position(5, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("하얀 폰의 이동 경로에 장애물이 있는지 확인")
    @Test
    void isSatisfyBy_false() {
        NormalWhitePawnMoveCondition condition = new NormalWhitePawnMoveCondition();
        Board board = new Board(Arrays.asList(
                WhitePawn.createWithCoordinate(7, 0),
                WhitePawn.createWithCoordinate(6, 0)

        ));
        boolean actual = condition.isSatisfyBy(board, WhitePawn.createWithCoordinate(7, 0),
                new Position(6, 0));

        assertThat(actual).isFalse();
    }

}