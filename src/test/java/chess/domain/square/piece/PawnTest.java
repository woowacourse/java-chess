package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {
    // TODO: @Nested 로 분리하기
    @DisplayName("한 번도 이동하지 않은 블랙 폰은 밑으로 두 칸 움직일 수 있다.")
    @Test
    void blackCanMoveTwoStraightTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Pawn.from(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.SEVENTH, File.A), new Position(Rank.FIFTH, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("이동한적이 있는 블랙 폰은 밑으로 두 칸 움직일 수 없다.")
    @Test
    void blackCanNotMoveTwoStraightTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Pawn.from(Color.BLACK);
        board.put(new Position(Rank.FIFTH, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIFTH, File.A), new Position(Rank.THIRD, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("블랙 폰은 항상 밑으로 한 칸 움직일 수 있다.")
    @Test
    void blackCanStraightMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Pawn.from(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.SEVENTH, File.A), new Position(Rank.SIXTH, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("블랙 폰은 밑으로 한 칸 또는 두 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidPathForBlack")
    void blackCanNotStraightMoveTest(PathFinder pathFinder) {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Pawn.from(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.B), piece);

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    static Stream<PathFinder> provideUnValidPathForBlack() {
        return Stream.of(
                new PathFinder(new Position(Rank.SEVENTH, File.B), new Position(Rank.EIGHTH, File.B)),
                new PathFinder(new Position(Rank.SEVENTH, File.B), new Position(Rank.SEVENTH, File.A)),
                new PathFinder(new Position(Rank.SEVENTH, File.B), new Position(Rank.SEVENTH, File.C)),
                new PathFinder(new Position(Rank.SEVENTH, File.B), new Position(Rank.SIXTH, File.A)),
                new PathFinder(new Position(Rank.SEVENTH, File.B), new Position(Rank.SIXTH, File.C))
        );
    }

    @DisplayName("한 번도 이동하지 않은 화이트 폰은 위로 두 칸 움직일 수 있다.")
    @Test
    void whiteCanMoveTwoStraightTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Pawn.from(Color.WHITE);
        board.put(new Position(Rank.SECOND, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.SECOND, File.A), new Position(Rank.FOURTH, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("이동한적이 있는 화이트 폰은 위로 두 칸 움직일 수 없다.")
    @Test
    void whiteCanNotMoveTwoStraightTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Pawn.from(Color.WHITE);
        board.put(new Position(Rank.FOURTH, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FOURTH, File.A), new Position(Rank.SIXTH, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("화이트 폰은 항상 위로 한 칸 움직일 수 있다.")
    @Test
    void whiteCanStraightMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Pawn.from(Color.WHITE);
        board.put(new Position(Rank.SECOND, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.SECOND, File.A), new Position(Rank.THIRD, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("화이트 폰은 위로 한 칸 또는 두 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidPathForWhite")
    void canStraightNotMoveTest(PathFinder pathFinder) {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Pawn.from(Color.WHITE);
        board.put(new Position(Rank.SECOND, File.B), piece);

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    static Stream<PathFinder> provideUnValidPathForWhite() {
        return Stream.of(
                new PathFinder(new Position(Rank.SECOND, File.B), new Position(Rank.FIRST, File.B)),
                new PathFinder(new Position(Rank.SECOND, File.B), new Position(Rank.SECOND, File.A)),
                new PathFinder(new Position(Rank.SECOND, File.B), new Position(Rank.SECOND, File.C)),
                new PathFinder(new Position(Rank.SECOND, File.B), new Position(Rank.FIRST, File.A)),
                new PathFinder(new Position(Rank.SECOND, File.B), new Position(Rank.FIRST, File.C))
        );
    }

    @DisplayName("블랙 폰은 왼쪽 대각선 한 칸 밑을 공격할 수 있다.")
    @Test
    void blackCanLeftDownAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Pawn.from(Color.BLACK);
        Piece attackedPiece = Pawn.from(Color.WHITE);
        board.put(new Position(Rank.EIGHTH, File.B), attackerPiece);
        board.put(new Position(Rank.SEVENTH, File.A), attackedPiece);

        PathFinder pathFinder = new PathFinder(new Position(Rank.EIGHTH, File.B), new Position(Rank.SEVENTH, File.A));

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("블랙 폰은 오른쪽 대각선 한 칸 밑을 공격할 수 있다.")
    @Test
    void blackCanRightDownAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Pawn.from(Color.BLACK);
        Piece attackedPiece = Pawn.from(Color.WHITE);
        board.put(new Position(Rank.EIGHTH, File.B), attackerPiece);
        board.put(new Position(Rank.SEVENTH, File.C), attackedPiece);

        PathFinder pathFinder = new PathFinder(new Position(Rank.EIGHTH, File.B), new Position(Rank.SEVENTH, File.C));

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("블랙 폰은 왼쪽 또는 오른쪽 대각선 한 칸 밑을 제외하고 공격할 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidAttackedPositionForBlack")
    void blackCanNotAttackTest(Position attackedPosition) {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        PathFinder pathFinder = new PathFinder(new Position(Rank.SEVENTH, File.B), attackedPosition);
        Piece attackerPiece = Pawn.from(Color.BLACK);
        Piece attackedPiece = Pawn.from(Color.WHITE);
        board.put(new Position(Rank.SEVENTH, File.B), attackerPiece);
        board.put(attackedPosition, attackedPiece);

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isFalse();
    }

    static Stream<Position> provideUnValidAttackedPositionForBlack() {
        return Stream.of(
                new Position(Rank.EIGHTH, File.A),
                new Position(Rank.EIGHTH, File.C),
                new Position(Rank.SIXTH, File.B),
                new Position(Rank.EIGHTH, File.B),
                new Position(Rank.SEVENTH, File.A),
                new Position(Rank.SEVENTH, File.C)
        );
    }

    @DisplayName("화이트 폰은 왼쪽 대각선 한 칸 위를 공격할 수 있다.")
    @Test
    void whiteCanLeftDownAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Pawn.from(Color.WHITE);
        Piece attackedPiece = Pawn.from(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.B), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.A), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.SEVENTH, File.B), new Position(Rank.EIGHTH, File.A));

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("화이트 폰은 오른쪽 대각선 한 칸 위를 공격할 수 있다.")
    @Test
    void whiteCanRightDownAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Pawn.from(Color.WHITE);
        Piece attackedPiece = Pawn.from(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.B), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.C), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.SEVENTH, File.B), new Position(Rank.EIGHTH, File.C));

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("화이트 폰은 왼쪽 또는 오른쪽 대각선 한 칸 밑을 제외하고 공격할 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidAttackedPositionForWhite")
    void whiteCanNotAttackTest(Position attackedPosition) {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        PathFinder pathFinder = new PathFinder(new Position(Rank.SEVENTH, File.B), attackedPosition);
        Piece attackerPiece = Pawn.from(Color.WHITE);
        Piece attackedPiece = Pawn.from(Color.BLACK);
        board.put(new Position(Rank.SEVENTH, File.B), attackerPiece);
        board.put(attackedPosition, attackedPiece);

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isFalse();
    }

    static Stream<Position> provideUnValidAttackedPositionForWhite() {
        return Stream.of(
                new Position(Rank.SIXTH, File.A),
                new Position(Rank.SIXTH, File.C),
                new Position(Rank.SIXTH, File.B),
                new Position(Rank.EIGHTH, File.B),
                new Position(Rank.SEVENTH, File.A),
                new Position(Rank.SEVENTH, File.C)
        );
    }
}
