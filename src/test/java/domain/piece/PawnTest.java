package domain.piece;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @DisplayName("백색 폰은 위로 1칸 움직일 수 있다, 단 초기위치에서 2칸 이동을 허용한다.")
    @ParameterizedTest
    @MethodSource(value = "whitePawnMovementArguments")
    void canWhitePawnMove(final Square target, final boolean result) {
        final Pawn pawn = new Pawn(Team.WHITE);
        final Square source = new Square(File.D, Rank.TWO);

        final boolean canMove = pawn.canMove(source, target, new ChessBoard().getPieces());

        assertThat(canMove).isEqualTo(result);
    }

    static Stream<Arguments> whitePawnMovementArguments() {
        return Stream.of(
                Arguments.of(new Square(File.D, Rank.THREE), true),
                Arguments.of(new Square(File.D, Rank.FOUR), true)
        );
    }


    @DisplayName("흑색 폰은 아래로 1칸 움직일 수 있다, 단 초기위치에서 2칸 이동을 허용한다.")
    @ParameterizedTest
    @MethodSource(value = "blackPawnMovementArguments")
    void canBlackPawnMove(final Square target, final boolean result) {
        final Pawn pawn = new Pawn(Team.BLACK);
        final Square source = new Square(File.D, Rank.SEVEN);

        final boolean canMove = pawn.canMove(source, target, new ChessBoard().getPieces());

        assertThat(canMove).isEqualTo(result);
    }

    static Stream<Arguments> blackPawnMovementArguments() {
        return Stream.of(
                Arguments.of(new Square(File.D, Rank.SIX), true),
                Arguments.of(new Square(File.D, Rank.FIVE), true)
        );
    }

    @DisplayName("흰색 폰은 위 대각선 방향으로 공격할 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "whitePawnAttackArguments")
    void canWhitePawnAttack(final Square target, final boolean result) {
        final Pawn pawn = new Pawn(Team.WHITE);
        final Square source = new Square(File.D, Rank.TWO);

        final boolean canMove = pawn.canAttack(source, target, new ChessBoard().getPieces());

        assertThat(canMove).isEqualTo(result);
    }

    static Stream<Arguments> whitePawnAttackArguments() {
        return Stream.of(
                Arguments.of(new Square(File.C, Rank.THREE), true),
                Arguments.of(new Square(File.E, Rank.THREE), true)
        );
    }

    @DisplayName("블랙폰은 대각선 아래 방향으로 공격할 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "blackPawnAttackArguments")
    void canAttack(final Square target, final boolean result) {
        final Pawn pawn = new Pawn(Team.BLACK);
        final Square source = new Square(File.D, Rank.SEVEN);
        final boolean canMove = pawn.canAttack(source, target, new ChessBoard().getPieces());

        assertThat(canMove).isEqualTo(result);
    }

    static Stream<Arguments> blackPawnAttackArguments() {
        return Stream.of(
                Arguments.of(new Square(File.C, Rank.SIX), true),
                Arguments.of(new Square(File.E, Rank.SIX), true)
        );
    }


    @DisplayName("대각선에 적이 없으면 공격할 수 없다.")
    @ParameterizedTest
    @MethodSource(value = "canNotAttackArguments")
    void canNotAttack(final Team team, final Square source, final Square target, final boolean result) {
        final Pawn pawn = new Pawn(team);

        final boolean canMove = pawn.canAttack(source, target, new ChessBoard().getPieces());

        assertThat(canMove).isEqualTo(result);
    }

    static Stream<Arguments> canNotAttackArguments() {
        return Stream.of(
                Arguments.of(Team.WHITE, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.SIX), false),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.FIVE), false),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.FOUR), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.TWO), new Square(File.D, Rank.THREE), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.TWO), new Square(File.D, Rank.ONE), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.TWO), new Square(File.D, Rank.FOUR), false)
        );
    }
}
