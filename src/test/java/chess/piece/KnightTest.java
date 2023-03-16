package chess.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class KnightTest {
    private static final Piece knight = PieceType.KNIGHT.createPiece(Camp.WHITE);
    private static final Square source = new Square(File.E, Rank.FOUR);

    @DisplayName("나이트 이동조건이 아닌 경우, 이동할 수 없다")
    @Test
    void cantMoveOverMovableDistance() {
        Square target = new Square(File.E, Rank.ONE);
        Assertions.assertThat(knight.canMove(source, target))
                .isFalse();
    }

    @ParameterizedTest(name = "나이트 이동조건인 경우, 이동할 수 있다")
    @MethodSource("squareProvider")
    void canMoveTestWithMovableRankDistance(Square target) {
        Assertions.assertThat(knight.canMove(source, target))
                .isTrue();
    }

    static Stream<Arguments> squareProvider() {
        return Stream.of(
                Arguments.arguments(new Square(File.G, Rank.THREE)),
                Arguments.arguments(new Square(File.G, Rank.FIVE)),
                Arguments.arguments(new Square(File.F, Rank.TWO)),
                Arguments.arguments(new Square(File.F, Rank.SIX)),
                Arguments.arguments(new Square(File.D, Rank.TWO)),
                Arguments.arguments(new Square(File.D, Rank.SIX)),
                Arguments.arguments(new Square(File.C, Rank.THREE)),
                Arguments.arguments(new Square(File.C, Rank.FIVE))
        );
    }
}
