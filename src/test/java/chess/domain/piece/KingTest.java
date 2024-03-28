package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.TerminalPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {
    @DisplayName("이동 테스트")
    @Nested
    class PassTest {
        @DisplayName("킹은 한 칸 짜리 직선 경로이면 움직일 수 있다.")
        @Test
        void canStraightMoveTest() {
            // given
            Piece piece = King.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.SECOND));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("킹은 한 칸 짜리 대각선 경로이면 움직일 수 있다.")
        @Test
        void canDiagonalMoveTest() {
            // given
            Piece piece = King.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.B, Rank.SECOND));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("킹은 한 칸 짜리 경로가 아니면 움직일 수 없다.")
        @Test
        void canNotMoveTest() {
            // given
            Piece piece = King.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.THIRD));

            // when & then
            assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }
    }

    @DisplayName("공격 테스트")
    @Nested
    class AttackTest {
        @DisplayName("킹은 한 칸 짜리 직선 경로이면 공격할 수 있다.")
        @Test
        void canStraightAttackTest() {
            // given
            Piece attackerPiece = King.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.SECOND));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("킹은 한 칸 짜리 대각선 경로이면 공격할 수 있다.")
        @Test
        void canDiagonalAttackTest() {
            // given
            Piece attackerPiece = King.from(Color.WHITE);

            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.B, Rank.SECOND));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of());
        }

        @DisplayName("킹은 한 칸 짜리 경로가 아니면 공격할 수 없다.")
        @Test
        void canNotAttackTest() {
            // given
            Piece attackerPiece = King.from(Color.WHITE);

            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.THIRD));

            // when & then
            assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }
    }
}
