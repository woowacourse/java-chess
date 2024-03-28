package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.TerminalPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {
    @DisplayName("블랙 폰 이동 테스트")
    @Nested
    class BlackPawnPassTest {
        @DisplayName("초기 위치 Rank 7에 있는 블랙 폰은 밑으로 두 칸 움직일 수 있다.")
        @Test
        void blackCanMoveTwoStraightTest() {
            // given
            Piece piece = Pawn.from(Color.BLACK);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.SEVENTH), new Position(File.A, Rank.FIFTH));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of(new Position(File.A, Rank.SIXTH)));
        }

        @DisplayName("초기 위치 Rank 7에 있지 않는 블랙 폰은 밑으로 두 칸 움직일 수 없다.")
        @Test
        void blackCanNotMoveTwoStraightTest() {
            // given
            Piece piece = Pawn.from(Color.BLACK);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.SIXTH), new Position(File.A, Rank.FOURTH));

            // when & then
            assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }

        @DisplayName("블랙 폰은 항상 밑으로 한 칸 움직일 수 있다.")
        @Test
        void blackCanStraightMoveTest() {
            // given
            Piece piece = Pawn.from(Color.BLACK);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.SEVENTH), new Position(File.A, Rank.SIXTH));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("블랙 폰은 밑으로 한 칸을 제외하고 움직일 수 없다.")
        @ParameterizedTest
        @MethodSource("provideUnValidEndPositionForBlack")
        void blackCanNotStraightMoveTest(Position endPosition) {
            // given
            Piece piece = Pawn.from(Color.BLACK);
            TerminalPosition terminalPosition = new TerminalPosition(new Position(File.B, Rank.SEVENTH), endPosition);

            // when & then
            assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }

        static Stream<Position> provideUnValidEndPositionForBlack() {
            return Stream.of(
                    new Position(File.B, Rank.EIGHTH),
                    new Position(File.A, Rank.SEVENTH),
                    new Position(File.C, Rank.SEVENTH),
                    new Position(File.A, Rank.SIXTH)
            );
        }
    }

    @DisplayName("화이트 폰 이동 테스트")
    @Nested
    class WhitePawnPassTest {
        @DisplayName("초기 위치 Rank 2에 있는 화이트 폰은 밑으로 두 칸 움직일 수 있다.")
        @Test
        void whiteCanMoveTwoStraightTest() {
            // given
            Piece piece = Pawn.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.SECOND), new Position(File.A, Rank.FOURTH));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of(new Position(File.A, Rank.THIRD)));
        }

        @DisplayName("초기 위치 Rank 2에 있지 않는 화이트 폰은 밑으로 두 칸 움직일 수 없다.")
        @Test
        void whiteCanNotMoveTwoStraightTest() {
            // given
            Piece piece = Pawn.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.THIRD), new Position(File.A, Rank.FIFTH));

            // when & then
            assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }

        @DisplayName("화이트 폰은 항상 위로 한 칸 움직일 수 있다.")
        @Test
        void whiteCanStraightMoveTest() {
            // given
            Piece piece = Pawn.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.THIRD), new Position(File.A, Rank.FOURTH));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("화이트 폰은 위로 한 칸을 제외하고 움직일 수 없다.")
        @ParameterizedTest
        @MethodSource("provideUnValidEndPositionForWhite")
        void canStraightNotMoveTest(Position endPosition) {
            // given
            Piece piece = Pawn.from(Color.WHITE);
            TerminalPosition terminalPosition = new TerminalPosition(new Position(File.B, Rank.SECOND), endPosition);

            // when & then
            assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }

        static Stream<Position> provideUnValidEndPositionForWhite() {
            return Stream.of(
                    new Position(File.B, Rank.FIRST),
                    new Position(File.A, Rank.SECOND),
                    new Position(File.C, Rank.SECOND),
                    new Position(File.A, Rank.FIRST)
            );
        }
    }

    @DisplayName("블랙 폰 공격 테스트")
    @Nested
    class BlackPawnAttackTest {
        @DisplayName("블랙 폰은 왼쪽 대각선 한 칸 밑을 공격할 수 있다.")
        @Test
        void blackCanLeftDownAttackTest() {
            // given
            Piece attackerPiece = Pawn.from(Color.BLACK);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.B, Rank.SEVENTH), new Position(File.A, Rank.SIXTH));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("블랙 폰은 오른쪽 대각선 한 칸 밑을 공격할 수 있다.")
        @Test
        void blackCanRightDownAttackTest() {
            // given
            Piece attackerPiece = Pawn.from(Color.BLACK);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.B, Rank.SEVENTH), new Position(File.C, Rank.SIXTH));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("블랙 폰은 왼쪽 또는 오른쪽 대각선 한 칸 밑을 제외하고 공격할 수 없다.")
        @ParameterizedTest
        @MethodSource("provideUnValidAttackedPositionForBlack")
        void blackCanNotAttackTest(Position attackedPosition) {
            // given
            Piece attackerPiece = Pawn.from(Color.BLACK);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.B, Rank.SEVENTH), attackedPosition);

            // when & then
            assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }

        static Stream<Position> provideUnValidAttackedPositionForBlack() {
            return Stream.of(
                    new Position(File.A, Rank.EIGHTH),
                    new Position(File.C, Rank.EIGHTH),
                    new Position(File.B, Rank.SIXTH),
                    new Position(File.B, Rank.EIGHTH),
                    new Position(File.A, Rank.SEVENTH),
                    new Position(File.C, Rank.SEVENTH)
            );
        }
    }

    @DisplayName("화이트 폰 공격 테스트")
    @Nested
    class WhitePawnAttackTest {
        @DisplayName("화이트 폰은 왼쪽 대각선 한 칸 위를 공격할 수 있다.")
        @Test
        void whiteCanLeftDownAttackTest() {
            // given
            Piece attackerPiece = Pawn.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.B, Rank.SEVENTH), new Position(File.A, Rank.EIGHTH));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("화이트 폰은 오른쪽 대각선 한 칸 위를 공격할 수 있다.")
        @Test
        void whiteCanRightDownAttackTest() {
            // given
            Piece attackerPiece = Pawn.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.B, Rank.SEVENTH), new Position(File.C, Rank.EIGHTH));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("화이트 폰은 왼쪽 또는 오른쪽 대각선 한 칸 밑을 제외하고 공격할 수 없다.")
        @ParameterizedTest
        @MethodSource("provideUnValidAttackedPositionForWhite")
        void whiteCanNotAttackTest(Position attackedPosition) {
            // given
            Piece attackerPiece = Pawn.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.B, Rank.SEVENTH), attackedPosition);

            // when & then
            assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }

        static Stream<Position> provideUnValidAttackedPositionForWhite() {
            return Stream.of(
                    new Position(File.A, Rank.SIXTH),
                    new Position(File.C, Rank.SIXTH),
                    new Position(File.B, Rank.SIXTH),
                    new Position(File.B, Rank.EIGHTH),
                    new Position(File.A, Rank.SEVENTH),
                    new Position(File.C, Rank.SEVENTH)
            );
        }
    }
}
