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

class PawnTest {

    @DisplayName("폰은 가로로 이동할 수 없다")
    @Test
    void cantMoveJustAlongFile() {
        Piece pawn = PieceType.PAWN.createPiece(Camp.WHITE);
        Square source = Square.getInstanceOf(File.E, Rank.FOUR);
        Square target = Square.getInstanceOf(File.F, Rank.FOUR);

        assertThat(pawn.canMove(source, target))
                .isFalse();
    }

    @DisplayName("폰은 rank를 3칸 이상 이동할 수 없다")
    @Test
    void cantMoveOverMovableRankDistance() {
        Piece pawn = PieceType.PAWN.createPiece(Camp.WHITE);
        Square source = Square.getInstanceOf(File.E, Rank.FOUR);
        Square target = Square.getInstanceOf(File.E, Rank.SEVEN);

        assertThat(pawn.canMove(source, target))
                .isFalse();
    }

    @ParameterizedTest(name = "흰색 진영의 폰은 이동규칙에 따라 이동할 수 있다")
    @MethodSource("whitePawnMovableSquareProvider")
    void canMoveWhitePawnTest(Square target) {
        Piece pawn = PieceType.PAWN.createPiece(Camp.WHITE);
        Square source = Square.getInstanceOf(File.E, Rank.TWO);

        assertThat(pawn.canMove(source, target))
                .isTrue();
    }

    static Stream<Arguments> whitePawnMovableSquareProvider() {
        return Stream.of(
                Arguments.arguments(Square.getInstanceOf(File.E, Rank.FOUR)),
                Arguments.arguments(Square.getInstanceOf(File.E, Rank.THREE)),
                Arguments.arguments(Square.getInstanceOf(File.F, Rank.THREE)),
                Arguments.arguments(Square.getInstanceOf(File.D, Rank.THREE))
        );
    }

    @ParameterizedTest(name = "검은색 진영의 폰은 이동규칙에 따라 이동할 수 있다")
    @MethodSource("blackPawnMovableSquareProvider")
    void canMoveBlackPawnTest(Square target) {
        Piece pawn = PieceType.PAWN.createPiece(Camp.BLACK);
        Square source = Square.getInstanceOf(File.E, Rank.SEVEN);

        assertThat(pawn.canMove(source, target))
                .isTrue();
    }

    static Stream<Arguments> blackPawnMovableSquareProvider() {
        return Stream.of(
                Arguments.arguments(Square.getInstanceOf(File.E, Rank.FIVE)),
                Arguments.arguments(Square.getInstanceOf(File.E, Rank.SIX)),
                Arguments.arguments(Square.getInstanceOf(File.F, Rank.SIX)),
                Arguments.arguments(Square.getInstanceOf(File.D, Rank.SIX))
        );
    }
}
