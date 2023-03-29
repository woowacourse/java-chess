package chess.domain.piece;

import chess.domain.piece.normal.Queen;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static chess.domain.piece.BishopTest.C5;
import static chess.domain.piece.BishopTest.E7;
import static chess.domain.piece.KingTest.C6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    public static final Position A3 = new Position(File.A, Rank.THREE);
    public static final Position B2 = new Position(File.B, Rank.TWO);
    public static final Position B3 = new Position(File.B, Rank.THREE);
    public static final Position C3 = new Position(File.C, Rank.THREE);
    public static final Position C1 = new Position(File.C, Rank.ONE);
    public static final Position C4 = new Position(File.C, Rank.FOUR);
    public static final Position D4 = new Position(File.D, Rank.FOUR);
    public static final Position E5 = new Position(File.E, Rank.FIVE);
    public static final Position F6 = new Position(File.F, Rank.SIX);
    public static final Position F3 = new Position(File.F, Rank.THREE);
    public static final Position D3 = new Position(File.D, Rank.THREE);
    public static final Position E3 = new Position(File.E, Rank.THREE);
    public static final Position H7 = new Position(File.H, Rank.SEVEN);

    @DisplayName("대각선에 있으면 이동경로에 포함된다")
    @Test
    void computePath_upRight() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = F6;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(F6, E5, D4);
    }

    @DisplayName("대각선에 있으면 이동경로에 포함된다")
    @Test
    void computePath_downLeft() {
        final var queen = new Queen(Color.BLACK);
        final var source = E5;
        final var target = B2;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(B2, C3, D4);
    }

    @DisplayName("같은 Rank에 있으면 이동경로에 포함된다")
    @Test
    void computePath_left() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = A3;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(A3, B3);
    }

    @DisplayName("같은 File에 있으면 이동경로에 포함된다")
    @Test
    void computePath_right() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = F3;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(F3, E3, D3);
    }

    @DisplayName("같은 File에 있으면 이동경로에 포함된다")
    @Test
    void computePath_up() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = C6;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(C6, C5, C4);
    }

    @DisplayName("잘못된 target이면 예외가 발생한다")
    @Test
    void computePath_illegalTarget_exception() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = H7;

        assertThatThrownBy(() -> queen.computePath(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("퀸이 이동 가능한 경로를 계산한다.")
    @MethodSource("queenMovable")
    void test(Position source, Position target, Position[] expected) {
        final var queen = new Queen(Color.WHITE);
        Set<Position> positions = queen.computePath(source, target);

        assertThat(positions).containsExactlyInAnyOrder(expected);
    }

    @ParameterizedTest
    @DisplayName("퀸이 이동이 불가능한 경우 예외가 발생한다.")
    @MethodSource("queenIllegalMove")
    void test(Position source, Position target) {
        final var queen = new Queen(Color.WHITE);
        assertThatThrownBy(() -> queen.computePath(source, target))
                .isInstanceOf(IllegalArgumentException.class);

    }

    static Stream<Arguments> queenMovable() {
        return Stream.of(
                Arguments.arguments(C3, F6, new Position[]{F6, E5, D4}),
                Arguments.arguments(C3, F3, new Position[]{F3, E3, D3}),
                Arguments.arguments(E7, F6, new Position[]{F6}),
                Arguments.arguments(A3, C1, new Position[]{C1, B2})
        );
    }

    static Stream<Arguments> queenIllegalMove() {
        return Stream.of(
                Arguments.arguments(C1, D4),
                Arguments.arguments(C1, E5),
                Arguments.arguments(D4, A3),
                Arguments.arguments(H7, D4),
                Arguments.arguments(C1, E7)
        );
    }
}
