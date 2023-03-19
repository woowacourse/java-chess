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

    @ParameterizedTest(name = "흰색 진영의 폰은 e2에서 {0}로 이동할 수 있다")
    @MethodSource("whitePawnMovableSquareProvider")
    void canMoveWhitePawnTest(String name, Square target) {
        Piece pawn = PieceType.PAWN.createPiece(Camp.WHITE);
        Assertions.assertThat(pawn.canMove(new Square(File.E, Rank.TWO), target))
                .isTrue();
    }

    @ParameterizedTest(name = "검은색 진영의 폰은 e7에서 {0}로 이동할 수 있다")
    @MethodSource("blackPawnMovableSquareProvider")
    void canMoveBlackPawnTest(String name, Square target) {
        Piece pawn = PieceType.PAWN.createPiece(Camp.BLACK);
        Assertions.assertThat(pawn.canMove(new Square(File.E, Rank.SEVEN), target))
                .isTrue();
    }

    static Stream<Arguments> whitePawnMovableSquareProvider() {
        return Stream.of(
                Arguments.arguments("e4", new Square(File.E, Rank.FOUR)),
                Arguments.arguments("e3", new Square(File.E, Rank.THREE)),
                Arguments.arguments("f3", new Square(File.F, Rank.THREE)),
                Arguments.arguments("d3", new Square(File.D, Rank.THREE))

        );
    }

    static Stream<Arguments> blackPawnMovableSquareProvider() {
        return Stream.of(
                Arguments.arguments("e5", new Square(File.E, Rank.FIVE)),
                Arguments.arguments("e6", new Square(File.E, Rank.SIX)),
                Arguments.arguments("f6", new Square(File.F, Rank.SIX)),
                Arguments.arguments("d6", new Square(File.D, Rank.SIX))

        );
    }
}
