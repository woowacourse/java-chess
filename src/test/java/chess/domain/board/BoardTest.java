package chess.domain.board;

import chess.config.BoardConfig;
import chess.domain.piece.position.MovingFlow;
import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

//todo: refac
class BoardTest {

//    @Test
//    @DisplayName("#initialize() : should return initialized Board")
//    void initiaize() {
//        Board board = Board.initiaize();
//        for (int i = BoardConfig.LINE_START; i <= BoardConfig.LINE_END; i++) {
//            for (IntegerTest row : Arrays.asList(BoardConfig.LINE_START,BoardConfig.LINE_END)) {
//                Piece piece = board.getPiece(Position.of(i, row));
//                //todo: refac
////                assertThat().isEqualTo(null);
//            }
//        }
//
//        for (int i = BoardConfig.LINE_START; i <= BoardConfig.LINE_END; i++) {
//            for (int j = Board.BLANK_START_INDEX; j <= Board.BLANK_END_INDEX; j++) {
//                Piece piece = board.getPiece(Position.of(i, j));
//                assertTrue(piece.isBlank());
//            }
//        }
//    }
//
//    @ParameterizedTest
//    @DisplayName("#movePiece() : should return Board with moved piece")
//    @MethodSource({"getCasesForMovePiece"})
//    void movePiece(MovingFlow movingFlow, Piece expected) {
//        Board board = Board.initiaize();
//        Board movedBoard = board.movePiece(movingFlow);
//        Piece piece = movedBoard.getPiece(movingFlow.getTo());
//        assertThat(piece).isEqualTo(expected);
//
//    }
//
//    @Test
//    @DisplayName("#getPiece() : should return Piece as to Position")
//    void getPiece() {
//        Board board = Board.initiaize();
//        Piece piece = board.getPiece(Position.of(1, 1));
//        assertThat(piece).isInstanceOf(Rook.class);
//    }
//
//    @Test
//    @DisplayName("#concludeResult() : should throw IllegalStateException")
//    void concludeResult() {
//        Board board = Board.initiaize();
//        assertThatThrownBy(board::concludeResult)
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessage(Board.NOT_FINISHED_ERROR);
//    }
//
//    private static Stream<Arguments> getCasesForMovePiece() {
//        return Stream.of(
//                Arguments.of(MovingFlow.of("a2", "a3"), null),
//                Arguments.of(MovingFlow.of("b1", "c3"), null)
//        );
//    }
}