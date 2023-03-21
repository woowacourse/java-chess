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

    @ParameterizedTest(name = "나이트는 {0}로 이동할 수 있다")
    @MethodSource("squareProvider")
    void canMoveTestWithMovableRankDistance(String name, Square target) {
        Assertions.assertThat(knight.canMove(source, target))
                .isTrue();
    }

    static Stream<Arguments> squareProvider() {
        return Stream.of(
                Arguments.arguments("e4에서 g3", new Square(File.G, Rank.THREE)),
                Arguments.arguments("e4에서 g5", new Square(File.G, Rank.FIVE)),
                Arguments.arguments("e4에서 f2", new Square(File.F, Rank.TWO)),
                Arguments.arguments("e4에서 f6", new Square(File.F, Rank.SIX)),
                Arguments.arguments("e4에서 d2", new Square(File.D, Rank.TWO)),
                Arguments.arguments("e4에서 d6", new Square(File.D, Rank.SIX)),
                Arguments.arguments("e4에서 c3", new Square(File.C, Rank.THREE)),
                Arguments.arguments("e4에서 c5", new Square(File.C, Rank.FIVE))
        );
    }
}
