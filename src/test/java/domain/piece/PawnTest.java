package domain.piece;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    final Pawn whitePawn = new Pawn(Team.WHITE);
    final Pawn blackPawn = new Pawn(Team.BLACK);

    @DisplayName("백색 폰은 위로 1칸 움직일 수 있다, 단 초기위치에서 2칸 이동을 허용한다.")
    @ParameterizedTest
    @MethodSource(value = "whitePawnMovementArguments")
    void canWhitePawnMove(final Square target) {
        final Square source = new Square(File.D, Rank.TWO);

        final boolean canMove = whitePawn.canMove(source, target, new ChessBoard().getPieces());

        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> whitePawnMovementArguments() {
        return Stream.of(
                Arguments.of(new Square(File.D, Rank.THREE)),
                Arguments.of(new Square(File.D, Rank.FOUR))
        );
    }


    @DisplayName("흑색 폰은 아래로 1칸 움직일 수 있다, 단 초기위치에서 2칸 이동을 허용한다.")
    @ParameterizedTest
    @MethodSource(value = "blackPawnMovementArguments")
    void canBlackPawnMove(final Square target) {
        final Square source = new Square(File.D, Rank.SEVEN);

        final boolean canMove = blackPawn.canMove(source, target, new ChessBoard().getPieces());

        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> blackPawnMovementArguments() {
        return Stream.of(
                Arguments.of(new Square(File.D, Rank.SIX)),
                Arguments.of(new Square(File.D, Rank.FIVE))
        );
    }

    @DisplayName("흰색 폰은 위 대각선 방향으로 공격할 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "whitePawnAttackArguments")
    void canWhitePawnAttack(final Square target) {
        final Square source = new Square(File.D, Rank.TWO);

        final Map<Square, Piece> pieces = new HashMap<>();
        pieces.put(source, whitePawn);
        pieces.put(target, blackPawn);

        final boolean canMove = whitePawn.canMove(source, target, new ChessBoard(pieces).getPieces());

        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> whitePawnAttackArguments() {
        return Stream.of(
                Arguments.of(new Square(File.C, Rank.THREE)),
                Arguments.of(new Square(File.E, Rank.THREE))
        );
    }

    @DisplayName("블랙폰은 대각선 아래 방향으로 공격할 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "blackPawnAttackArguments")
    void canAttack(final Square target) {
        final Square source = new Square(File.D, Rank.SEVEN);

        final Map<Square, Piece> pieces = new HashMap<>();
        pieces.put(source, blackPawn);
        pieces.put(target, whitePawn);

        final ChessBoard chessBoard = new ChessBoard(pieces);

        final boolean canMove = blackPawn.canMove(source, target, chessBoard.getPieces());

        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> blackPawnAttackArguments() {
        return Stream.of(
                Arguments.of(new Square(File.C, Rank.SIX)),
                Arguments.of(new Square(File.E, Rank.SIX))
        );
    }


    @DisplayName("대각선에 적이 없으면 공격할 수 없다.")
    @ParameterizedTest
    @MethodSource(value = "canNotAttackArguments")
    void canNotAttack(final Team sourceTeam, final Team targetTeam, final Square source, final Square target) {
        final Pawn sourcePawn = new Pawn(sourceTeam);
        final Pawn targetPawn = new Pawn(targetTeam);

        final Map<Square, Piece> pieces = new HashMap<>();
        pieces.put(source, sourcePawn);
        pieces.put(target, targetPawn);

        final boolean canMove = sourcePawn.canMove(source, target, new ChessBoard(pieces).getPieces());

        assertThat(canMove).isFalse();
    }

    static Stream<Arguments> canNotAttackArguments() {
        return Stream.of(
                Arguments.of(Team.WHITE, Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.SIX)),
                Arguments.of(Team.WHITE, Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.FIVE)),
                Arguments.of(Team.WHITE, Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.FOUR)),
                Arguments.of(Team.BLACK, Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.D, Rank.THREE)),
                Arguments.of(Team.BLACK, Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.D, Rank.ONE)),
                Arguments.of(Team.BLACK, Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.D, Rank.FOUR))
        );
    }
}
