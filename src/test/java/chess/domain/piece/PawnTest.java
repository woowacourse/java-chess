package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import chess.domain.strategy.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @DisplayName("폰의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForPawnMoveByDirection")
    void pawnMove(MoveStrategy moveStrategy, Piece piece, Position position, List<Position> expectedToPositions) {
        Board board = BoardFactory.createBoard();
        assertThat(moveStrategy.possiblePositions(board, piece, position)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForPawnMoveByDirection() {
        return Stream.of(
                Arguments.of(new PawnMoveStrategy(), Piece.of(PieceType.FIRST_WHITE_PAWN), Position.of("b2"),
                        Arrays.asList(Position.of("b3"), Position.of("b4"))),

                Arguments.of(new PawnMoveStrategy(), Piece.of(PieceType.FIRST_BLACK_PAWN), Position.of("b7"),
                        Arrays.asList(Position.of("b6"), Position.of("b5")))
                );
    }
}
