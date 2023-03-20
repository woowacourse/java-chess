package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = Board.create(new HashMap<>());
    }

    @Test
    @DisplayName("체스판은 64개의 칸으로 이루어져 있다.")
    void board64Size() {
        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("출발지와 도착지가 같으면 예외가 발생한다")
    void throwExcpetionWhenSamePosition() {
        final Position source = Position.of(File.A, Rank.THREE);
        final Position target = Position.of(File.A, Rank.THREE);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발지와 도착지는 같을 수 없습니다");
    }

    @Test
    @DisplayName("출발점에 체스말이 존재하지 않으면 예외가 발생한다")
    void throwExceptionWhenSourceNotEmpty() {
        final Position source = Position.of(File.B, Rank.THREE);
        final Position target = Position.of(File.B, Rank.FOUR);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발점에 체스말이 존재하지 않습니다");
    }

    @Test
    @DisplayName("체스말이 같은 팀을 공격할 경우 예외가 발생한다")
    void throwExceptionWhenAttackSameTeam() {
        final Position source = Position.of(File.A, Rank.ONE);
        final Position target = Position.of(File.A, Rank.TWO);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 팀은 공격할 수 없습니다");
    }

    @Test
    @DisplayName("체스말이 이동할 수 있는 방향이 아니면 예외가 발생한다")
    void throwExceptionWhenNotMovable() {
        final Position source = Position.of(File.B, Rank.TWO);
        final Position target = Position.of(File.C, Rank.THREE);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스말이 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("이동 경로에 체스말이 존재하면 예외가 발생한다")
    void pieceExistInPath() {
        final Position source = Position.of(File.A, Rank.ONE);
        final Position target = Position.of(File.A, Rank.THREE);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 경로에 체스말이 존재합니다.");
    }

    @Test
    @DisplayName("한 칸만 움직일 수 있는 체스말이 여러 칸을 움직이려고 할 경우, 예외가 발생한다")
    void throwExceptionWhenNotMovableByCount() {
        board.move(Position.of(File.E, Rank.TWO), Position.of(File.E, Rank.FOUR));

        final Position source = Position.of(File.E, Rank.ONE);
        final Position target = Position.of(File.E, Rank.THREE);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 칸만 움직일 수 있는 체스말입니다.");
    }

    @Test
    @DisplayName("체스말이 성공적으로 이동하는지 확인하는 테스트")
    void movePiece() {
        final Position source = Position.of(File.B, Rank.ONE);
        final Position target = Position.of(File.C, Rank.THREE);

        board.move(source, target);

        assertThat(board.getBoard().get(source).getClass()).isEqualTo(Empty.class);
        assertThat(board.getBoard().get(target).getClass()).isEqualTo(Knight.class);
    }
}
