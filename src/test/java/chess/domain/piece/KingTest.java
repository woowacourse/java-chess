package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.exception.PieceCanNotMoveException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {
    private static Stream<Arguments> kingTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.THREE)
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.THREE)
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.TWO)
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.ONE)
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.ONE)
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.ONE)
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.TWO)
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.THREE)
                )
        );
    }

    @DisplayName("팔방으로 한 칸씩 움직일 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("kingTestProvider")
    void Should_Success_When_KingMove(Square source, Square target) {
        King king = new King(Team.WHITE);

        assertDoesNotThrow(() -> king.validateMovableRange(source, target));
    }

    @DisplayName("사방과 대각선으로 한 칸 초과해서 움직일 수 없다.")
    @Test()
    void Should_Fail_When_KingMove() {
        King king = new King(Team.WHITE);
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.THREE);

        assertThatThrownBy(() -> king.validateMovableRange(source, target))
                .isInstanceOf(PieceCanNotMoveException.class)
                .hasMessage("이동할 수 없는 말입니다.");
    }

    @DisplayName("이동할 수 없는 방향일 경우 움직일 수 없다.")
    @Test
    void Should_ReturnFalse_When_KnightDirection() {
        King king = new King(Team.WHITE);
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.THREE);

        assertThatThrownBy(() -> king.validateMovableRange(source, target))
                .isInstanceOf(PieceCanNotMoveException.class)
                .hasMessage("이동할 수 없는 말입니다.");
    }
}
