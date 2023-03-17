package chess.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    private static final Piece knight = PieceType.KNIGHT.createPiece(Camp.WHITE);
    private static final Square source = Square.getInstanceOf(File.E, Rank.FOUR);

    @DisplayName("나이트 이동조건이 아닌 경우, 이동할 수 없다")
    @Test
    void cantMoveOverMovableDistance() {
        Square target = Square.getInstanceOf(File.E, Rank.ONE);
        assertThat(knight.canMove(source, target))
                .isFalse();
    }

    @ParameterizedTest(name = "나이트 이동조건인 경우, 이동할 수 있다")
    @MethodSource("squareProvider")
    void canMoveTestWithMovableRankDistance(Square target) {
        assertThat(knight.canMove(source, target))
                .isTrue();
    }

    static Stream<Arguments> squareProvider() {
        return Stream.of(
                Arguments.arguments(Square.getInstanceOf(File.G, Rank.THREE)),
                Arguments.arguments(Square.getInstanceOf(File.G, Rank.FIVE)),
                Arguments.arguments(Square.getInstanceOf(File.F, Rank.TWO)),
                Arguments.arguments(Square.getInstanceOf(File.F, Rank.SIX)),
                Arguments.arguments(Square.getInstanceOf(File.D, Rank.TWO)),
                Arguments.arguments(Square.getInstanceOf(File.D, Rank.SIX)),
                Arguments.arguments(Square.getInstanceOf(File.C, Rank.THREE)),
                Arguments.arguments(Square.getInstanceOf(File.C, Rank.FIVE))
        );
    }
}
