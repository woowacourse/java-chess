package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackBishop;
import chess.domain.piece.white.WhiteBishop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

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

    @DisplayName("비숍 붙어있는 있는 적으로 이동 테스트")
    @MethodSource("boardsForAttachCatchTest")
    @ParameterizedTest
    void isSatisfyBy_attachCatch(BlackBishop bishop, Board board, Position target) {
        BishopMoveCondition bishopMoveCondition = new BishopMoveCondition();

        boolean actual = bishopMoveCondition.isSatisfyBy(board, bishop, target);

        assertThat(actual).isTrue();
    }

    static Stream<Arguments> boardsForAttachCatchTest() {
        return Stream.of(
                Arguments.of(
                        BlackBishop.createWithCoordinate(3, 3),
                        new Board(
                                Arrays.asList(
                                        BlackBishop.createWithCoordinate(3, 3),
                                        WhiteBishop.createWithCoordinate(4, 4)
                                )),
                        new Position(4, 4)),
                Arguments.of(
                        BlackBishop.createWithCoordinate(3, 3),
                        new Board(
                                Arrays.asList(
                                        BlackBishop.createWithCoordinate(3, 3),
                                        WhiteBishop.createWithCoordinate(4, 2)
                                )),
                        new Position(4, 2)),
                Arguments.of(
                        BlackBishop.createWithCoordinate(3, 3),
                        new Board(
                                Arrays.asList(
                                        BlackBishop.createWithCoordinate(3, 3),
                                        WhiteBishop.createWithCoordinate(2, 4)
                                )),
                        new Position(2, 4)),
                Arguments.of(
                        BlackBishop.createWithCoordinate(3, 3),
                        new Board(
                                Arrays.asList(
                                        BlackBishop.createWithCoordinate(3, 3),
                                        WhiteBishop.createWithCoordinate(2, 2)
                                )),
                        new Position(2, 2))
        );
    }

    @DisplayName("비숍 떨어져 있는 적으로 이동 테스트")
    @MethodSource("boardsForRemoteCatchTest")
    @ParameterizedTest
    void isSatisfyBy_catch(BlackBishop bishop, Board board, Position target) {
        BishopMoveCondition bishopMoveCondition = new BishopMoveCondition();

        boolean actual = bishopMoveCondition.isSatisfyBy(board, bishop, target);

        assertThat(actual).isTrue();
    }

    static Stream<Arguments> boardsForRemoteCatchTest() {
        return Stream.of(
                Arguments.of(
                        BlackBishop.createWithCoordinate(3, 3),
                        new Board(
                                Arrays.asList(
                                        BlackBishop.createWithCoordinate(3, 3),
                                        WhiteBishop.createWithCoordinate(5, 5)
                                )),
                        new Position(5, 5)),
                Arguments.of(
                        BlackBishop.createWithCoordinate(3, 3),
                        new Board(
                                Arrays.asList(
                                        BlackBishop.createWithCoordinate(3, 3),
                                        WhiteBishop.createWithCoordinate(5, 1)
                                )),
                        new Position(5, 1)),
                Arguments.of(
                        BlackBishop.createWithCoordinate(3, 3),
                        new Board(
                                Arrays.asList(
                                        BlackBishop.createWithCoordinate(3, 3),
                                        WhiteBishop.createWithCoordinate(1, 5)
                                )),
                        new Position(1, 5)),
                Arguments.of(
                        BlackBishop.createWithCoordinate(3, 3),
                        new Board(
                                Arrays.asList(
                                        BlackBishop.createWithCoordinate(3, 3),
                                        WhiteBishop.createWithCoordinate(1, 1)
                                )),
                        new Position(1, 1))
        );
    }

}