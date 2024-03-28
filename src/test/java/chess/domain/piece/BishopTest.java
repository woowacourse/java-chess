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

class BishopTest {
    @DisplayName("이동 테스트")
    @Nested
    class PassTest {
        @DisplayName("비숍은 대각선 경로로 이동할 수 있다.")
        @Test
        void canMoveTest() {
            // given
            Piece piece = Bishop.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.C, Rank.THIRD));

            // when & then
            assertThat(piece.findPassPathTaken(terminalPosition))
                    .isEqualTo(List.of(new Position(File.B, Rank.SECOND)));
        }

        @DisplayName("비숍은 대각선 경로가 아니면 움직일 수 없다.")
        @Test
        void canNotMoveInvalidPathTest() {
            // given
            Piece piece = Bishop.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.C, Rank.FIRST));

            // when & then
            assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }
    }

    @DisplayName("공격 테스트")
    @Nested
    class AttackTest {
        @DisplayName("비숍은 대각선 경로로 공격할 수 있다.")
        @Test
        void canAttackTest() {
            // given
            Piece attackerPiece = Bishop.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.C, Rank.THIRD));

            // when & then
            assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                    .isEqualTo(List.of(new Position(File.B, Rank.SECOND)));
        }

        @DisplayName("비숍은 대각선 경로가 아니면 공격할 수 없다.")
        @Test
        void canNotAttackInvalidPathTest() {
            // given
            Piece attackerPiece = Bishop.from(Color.WHITE);
            TerminalPosition terminalPosition =
                    new TerminalPosition(new Position(File.A, Rank.FIRST), new Position(File.C, Rank.FIRST));

            // when & then
            assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
        }
    }
}
