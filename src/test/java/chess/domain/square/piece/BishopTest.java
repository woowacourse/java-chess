package chess.domain.square.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.TerminalPosition;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {
    private static final Map<Position, Square> board = new HashMap<>();

    @BeforeEach
    void setUp() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                board.put(new Position(rank, file), Empty.getInstance());
            }
        }
    }

    @DisplayName("비숍은 대각선 경로로 이동할 수 있다.")
    @Test
    void canMoveTest() {
        // given
        Piece piece = Bishop.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        TerminalPosition terminalPosition = new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.findPassPathTaken(terminalPosition))
                .isEqualTo(List.of(new Position(Rank.SECOND, File.B)));
    }

    @DisplayName("비숍은 대각선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPathTest() {
        // given
        Piece piece = Bishop.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        TerminalPosition terminalPosition = new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThatThrownBy(() -> piece.findPassPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }

    @DisplayName("비숍은 대각선 경로로 공격할 수 있다.")
    @Test
    void canAttackTest() {
        // given
        Piece attackerPiece = Bishop.from(Color.WHITE);
        Piece attackedPiece = Bishop.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        TerminalPosition terminalPosition = new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(attackerPiece.findAttackPathTaken(terminalPosition))
                .isEqualTo(List.of(new Position(Rank.SECOND, File.B)));
    }

    @DisplayName("비숍은 대각선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackInvalidPathTest() {
        // given
        Piece attackerPiece = Bishop.from(Color.WHITE);
        Piece attackedPiece = Bishop.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.FIRST, File.C), attackedPiece);
        TerminalPosition terminalPosition = new TerminalPosition(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThatThrownBy(() -> attackerPiece.findAttackPathTaken(terminalPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 위치는 체스 말이 도달할 수 없는 위치입니다.");
    }
}
