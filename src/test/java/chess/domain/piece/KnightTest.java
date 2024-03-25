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

public class KnightTest {
    @DisplayName("나이트는 파일 두칸, 랭크 한칸만큼 떨어진 칸으로 갈 수 있다.")
    @Test
    void canTwoFileOneRankMoveTest() {
        // given
        Piece piece = Knight.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when & then
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("나이트는 파일 한칸, 랭크 두칸만큼 떨어진 칸으로 갈 수 있다.")
    @Test
    void canOneFileTwoRankMoveTest() {
        // given
        Piece piece = Knight.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.B));

        // when & then
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("나이트는 정해진 규칙이 아닌 칸으로 움직일 수 없다.")
    @Test
    void canNotMoveTest() {
        // given
        Piece piece = Knight.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }

    @DisplayName("나이트는 파일 두칸, 랭크 한칸만큼 떨어진 칸을 공격할 수 있다.")
    @Test
    void canTwoFileOneRankAttackTest() {
        // given
        Piece attackerPiece = Knight.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when & then
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("나이트는 파일 한칸, 랭크 두칸만큼 떨어진 칸을 공격할 수 있다.")
    @Test
    void canOneFileTwoRankAttackTest() {
        // given
        Piece attackerPiece = Knight.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.B));

        // when & then
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of());
    }

    @DisplayName("나이트는 정해진 규칙이 아닌 칸으로 공격할 수 없다.")
    @Test
    void canNotAttackTest() {
        // given
        Piece attackerPiece = Knight.from(Color.WHITE);
        TerminalPosition terminalPosition =
                new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThatThrownBy(() -> attackerPiece.findPassPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }
}
