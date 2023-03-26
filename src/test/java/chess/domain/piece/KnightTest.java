package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {
    private static Stream<Arguments> possibleKnightTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.D, Rank.FIVE)
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.E, Rank.FOUR)
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.E, Rank.TWO)
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.D, Rank.ONE)
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.B, Rank.ONE)
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.A, Rank.TWO)
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.A, Rank.FOUR)
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.B, Rank.FIVE)
                )
        );
    }

    @DisplayName("사방 중 한 방향으로 한 칸 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("possibleKnightTestProvider")
    void Should_Success_When_KnightMove(Square source, Square target) {
        Knight knight = new Knight(Team.WHITE);

        assertDoesNotThrow(() -> knight.validateMovableRange(source, target));
    }
}
