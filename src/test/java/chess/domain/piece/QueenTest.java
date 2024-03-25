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

public class QueenTest {
    @DisplayName("이동 테스트")
    @Nested
    class PassTest {
        @DisplayName("퀸은 직선 경로로 이동할 수 있다.")
        @Test
        void canStraightMoveTest() {
            // given
            Piece piece = Queen.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.FOURTH));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of(new Position(File.A, Rank.SECOND), new Position(File.A, Rank.THIRD)));
        }

        @DisplayName("퀸은 대각선 경로로 이동할 수 있다.")
        @Test
        void canDiagonalMoveTest() {
            // given
            Piece piece = Queen.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.D, Rank.FOURTH));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of(new Position(File.B, Rank.SECOND), new Position(File.C, Rank.THIRD)));
        }

        @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 움직일 수 없다.")
        @Test
        void canNotMoveInvalidPathTest() {
            // given
            Piece piece = Queen.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.C, Rank.SECOND));

            // when & then
            assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }
    }

    @DisplayName("공격 테스트")
    @Nested
    class AttackTest {
        @DisplayName("퀸은 직선 경로로 공격할 수 있다.")
        @Test
        void canStraightAttackTest() {
            // given
            Piece attackerPiece = Queen.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.FOURTH));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of(new Position(File.A, Rank.SECOND), new Position(File.A, Rank.THIRD)));
        }

        @DisplayName("퀸은 대각선 경로로 공격할 수 있다.")
        @Test
        void canDiagonalAttackTest() {
            // given
            Piece attackerPiece = Queen.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.D, Rank.FOURTH));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of(new Position(File.B, Rank.SECOND), new Position(File.C, Rank.THIRD)));
        }

        @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 공격할 수 없다.")
        @Test
        void canNotAttackInvalidPathTest() {
            // given
            Piece attackerPiece = Queen.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.C, Rank.SECOND));

            // when & then
            assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }
    }
}
