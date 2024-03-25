package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.TerminalPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenTest {
    @DisplayName("퀸은 직선 경로로 이동할 수 있다.")
    @Test
    void canStraightMoveTest() {
        // given
        Piece piece = Queen.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.FOURTH, File.A));

        // when & then
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of(new Position(Rank.SECOND, File.A), new Position(Rank.THIRD, File.A)));
    }

    @DisplayName("퀸은 대각선 경로로 이동할 수 있다.")
    @Test
    void canDiagonalMoveTest() {
        // given
        Piece piece = Queen.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.FOURTH, File.D));

        // when & then
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of(new Position(Rank.SECOND, File.B), new Position(Rank.THIRD, File.C)));
    }

    @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPathTest() {
        // given
        Piece piece = Queen.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when & then
        assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }

    @DisplayName("퀸은 직선 경로로 공격할 수 있다.")
    @Test
    void canStraightAttackTest() {
        // given
        Piece attackerPiece = Queen.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.FOURTH, File.A));

        // when & then
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of(new Position(Rank.SECOND, File.A), new Position(Rank.THIRD, File.A)));
    }

    @DisplayName("퀸은 대각선 경로로 공격할 수 있다.")
    @Test
    void canDiagonalAttackTest() {
        // given
        Piece attackerPiece = Queen.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.FOURTH, File.D));

        // when & then
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of(new Position(Rank.SECOND, File.B), new Position(Rank.THIRD, File.C)));
    }

    @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackInvalidPathTest() {
        // given
        Piece attackerPiece = Queen.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when & then
        assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }
}
