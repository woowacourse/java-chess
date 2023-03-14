package chess.model.board;

import static chess.model.piece.PieceType.BISHOP;
import static chess.model.piece.PieceType.KING;
import static chess.model.piece.PieceType.KNIGHT;
import static chess.model.piece.PieceType.PAWN;
import static chess.model.piece.PieceType.QUEEN;
import static chess.model.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.Type;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("64개의 칸을 가지는 체스판을 생성한다.")
    void constructor_whenCall_thenSuccess() {
        final Board board = assertDoesNotThrow(Board::create);

        assertAll(
            () -> assertThat(board).isExactlyInstanceOf(Board.class),
            () -> assertThat(board)
                .extracting("squares", InstanceOfAssertFactories.list(Square.class))
                .hasSize(64)
        );
    }

    /**
     * rank = 8 RNBQKBNR
     * rank = 7 PPPPPPPP
     * rank = 2 pppppppp
     * rank = 1 rnbqkbnr
     */
    @Test
    @DisplayName("체스판에 정상적으로 기물이 생성 되었는지 확인한다.")
    void constructor_whenCall_thenReturnPieces() {
        //given
        final Board board = Board.create();
        final List<Square> squares = board.getSquares();

        //when
        final List<Type> rankOne = getRank(squares, 0, 8);
        final List<Type> rankTwo = getRank(squares, 8, 16);
        final List<Type> rankSeven = getRank(squares, 48, 56);
        final List<Type> rankEight = getRank(squares, 56, 64);

        //then
        assertAll(
            () -> assertThat(rankOne).containsExactly(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP,
                KNIGHT, ROOK),
            () -> assertThat(rankTwo).containsExactly(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                PAWN),
            () -> assertThat(rankSeven).containsExactly(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                PAWN),
            () -> assertThat(rankEight).containsExactly(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP,
                KNIGHT, ROOK)
        );
    }

    private List<Type> getRank(final List<Square> squares, final int from, final int to) {
        return squares.subList(from, to).stream()
            .map(Square::getType)
            .collect(Collectors.toList());
    }
}
