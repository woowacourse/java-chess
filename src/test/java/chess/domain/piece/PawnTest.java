package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.TerminalPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {
    // TODO: @Nested 로 분리하기
    @DisplayName("한 번도 이동하지 않은 블랙 폰은 밑으로 두 칸 움직일 수 있다.")
    @Test
    void blackCanMoveTwoStraightTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.BLACK);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.EIGHTH, File.A), new Position(Rank.SIXTH, File.A));

        // when & then
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of(new Position(Rank.SEVENTH, File.A)));
    }

    @DisplayName("이동한적이 있는 블랙 폰은 밑으로 두 칸 움직일 수 없다.")
    @Test
    void blackCanNotMoveTwoStraightTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.BLACK);
        piece.move();
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.EIGHTH, File.A), new Position(Rank.SIXTH, File.A));

        // when & then
        assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }

    @DisplayName("블랙 폰은 항상 밑으로 한 칸 움직일 수 있다.")
    @Test
    void blackCanStraightMoveTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.BLACK);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.EIGHTH, File.A), new Position(Rank.SEVENTH, File.A));

        // when & then
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("블랙 폰은 밑으로 한 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidEndPositionForBlack")
    void blackCanNotStraightMoveTest(Position endPosition) {
        // given
        Piece piece = Pawn.createOnStart(Color.BLACK);
        TerminalPosition terminalPosition = new TerminalPosition(new Position(Rank.SEVENTH, File.B), endPosition);

        // when & then
        assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }

    static Stream<Position> provideUnValidEndPositionForBlack() {
        return Stream.of(
                new Position(Rank.EIGHTH, File.B),
                new Position(Rank.SEVENTH, File.A),
                new Position(Rank.SEVENTH, File.C),
                new Position(Rank.SIXTH, File.A)
        );
    }

    @DisplayName("한 번도 이동하지 않은 화이트 폰은 위로 두 칸 움직일 수 있다.")
    @Test
    void whiteCanMoveTwoStraightTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.SECOND, File.A), new Position(Rank.FOURTH, File.A));

        // when & then
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of(new Position(Rank.THIRD, File.A)));
    }

    @DisplayName("이동한적이 있는 화이트 폰은 밑으로 두 칸 움직일 수 없다.")
    @Test
    void whiteCanNotMoveTwoStraightTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.WHITE);
        piece.move();
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.SECOND, File.A), new Position(Rank.FOURTH, File.A));

        // when & then
        assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }

    @DisplayName("화이트 폰은 항상 위로 한 칸 움직일 수 있다.")
    @Test
    void whiteCanStraightMoveTest() {
        // given
        Piece piece = Pawn.createOnStart(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("화이트 폰은 위로 한 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidEndPositionForWhite")
    void canStraightNotMoveTest(Position endPosition) {
        // given
        Piece piece = Pawn.createOnStart(Color.WHITE);
        TerminalPosition terminalPosition = new TerminalPosition(new Position(Rank.SECOND, File.B), endPosition);

        // when & then
        assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }

    static Stream<Position> provideUnValidEndPositionForWhite() {
        return Stream.of(
                new Position(Rank.FIRST, File.B),
                new Position(Rank.SECOND, File.A),
                new Position(Rank.SECOND, File.C),
                new Position(Rank.FIRST, File.A)
        );
    }

    @DisplayName("블랙 폰은 왼쪽 대각선 한 칸 밑을 공격할 수 있다.")
    @Test
    void blackCanLeftDownAttackTest() {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.BLACK);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.SEVENTH, File.B), new Position(Rank.SIXTH, File.A));

        // when & then
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("블랙 폰은 오른쪽 대각선 한 칸 밑을 공격할 수 있다.")
    @Test
    void blackCanRightDownAttackTest() {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.BLACK);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.SEVENTH, File.B), new Position(Rank.SIXTH, File.C));

        // when & then
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("블랙 폰은 왼쪽 또는 오른쪽 대각선 한 칸 밑을 제외하고 공격할 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidAttackedPositionForBlack")
    void blackCanNotAttackTest(Position attackedPosition) {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.BLACK);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.SEVENTH, File.B), attackedPosition);

        // when & then
        assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
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
        Piece attackerPiece = Pawn.createOnStart(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.SEVENTH, File.B), new Position(Rank.EIGHTH, File.A));

        // when & then
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("화이트 폰은 오른쪽 대각선 한 칸 위를 공격할 수 있다.")
    @Test
    void whiteCanRightDownAttackTest() {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.SEVENTH, File.B), new Position(Rank.EIGHTH, File.C));

        // when & then
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("화이트 폰은 왼쪽 또는 오른쪽 대각선 한 칸 밑을 제외하고 공격할 수 없다.")
    @ParameterizedTest
    @MethodSource("provideUnValidAttackedPositionForWhite")
    void whiteCanNotAttackTest(Position attackedPosition) {
        // given
        Piece attackerPiece = Pawn.createOnStart(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.SEVENTH, File.B), attackedPosition);

        // when & then
        assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
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
