package chess.piece;

import chess.File;
import chess.Rank;
import chess.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PawnTest {
    @DisplayName("폰은 가로로 이동할 수 없다")
    @Test
    void cantMoveJustAlongFile() {
        Piece pawn = PieceType.PAWN.createPiece(Camp.WHITE);
        Square source = new Square(File.E, Rank.FOUR);
        Square target = new Square(File.F, Rank.FOUR);
        Assertions.assertThat(pawn.canMove(source, target))
                .isFalse();
    }

    @DisplayName("폰은 rank를 3칸 이상 이동할 수 없다")
    @Test
    void cantMoveOverMovableRankDistance() {
        Piece pawn = PieceType.PAWN.createPiece(Camp.WHITE);
        Square source = new Square(File.E, Rank.FOUR);
        Square target = new Square(File.E, Rank.SEVEN);
        Assertions.assertThat(pawn.canMove(source, target))
                .isFalse();
    }

    @ParameterizedTest(name = "흰색 진영의 폰은 이동규칙에 따라 이동할 수 있다")
    @MethodSource("whitePawnMovableSquareProvider")
    void canMoveWhitePawnTest(Square target) {
        Piece pawn = PieceType.PAWN.createPiece(Camp.WHITE);
        Assertions.assertThat(pawn.canMove(new Square(File.E, Rank.TWO), target))
                .isTrue();
    }

    @ParameterizedTest(name = "검은색 진영의 폰은 이동규칙에 따라 이동할 수 있다")
    @MethodSource("blackPawnMovableSquareProvider")
    void canMoveBlackPawnTest(Square target) {
        Piece pawn = PieceType.PAWN.createPiece(Camp.BLACK);
        Assertions.assertThat(pawn.canMove(new Square(File.E, Rank.SEVEN), target))
                .isTrue();
    }

    static Stream<Arguments> whitePawnMovableSquareProvider() {
        return Stream.of(
                Arguments.arguments(new Square(File.E, Rank.FOUR)),
                Arguments.arguments(new Square(File.E, Rank.THREE)),
                Arguments.arguments(new Square(File.F, Rank.THREE)),
                Arguments.arguments(new Square(File.D, Rank.THREE))

        );
    }

    static Stream<Arguments> blackPawnMovableSquareProvider() {
        return Stream.of(
                Arguments.arguments(new Square(File.E, Rank.FIVE)),
                Arguments.arguments(new Square(File.E, Rank.SIX)),
                Arguments.arguments(new Square(File.F, Rank.SIX)),
                Arguments.arguments(new Square(File.D, Rank.SIX))

        );
    }
}
