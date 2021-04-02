package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.util.BoardInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = BoardInitializer.init();
    }

    @DisplayName("보드 초기화")
    @Test
    void init() {
        assertThatCode(BoardInitializer::init).doesNotThrowAnyException();
    }

    @DisplayName("보드에 말이 있는지 확인")
    @Test
    void pieceAt() {
        assertThat(board.pieceAt(Position.of("a1"))).isInstanceOf(Rook.class);
    }

    @DisplayName("피스 움직이기")
    @Test
    void movePiece() {
        board.movePiece(Position.of("a1"), Position.of("a3"));

        assertThat(board.pieceAt(Position.of("a3"))).isInstanceOf(Rook.class);
        assertThatThrownBy(() -> board.pieceAt(Position.of("a1")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("팀의 포인트 합산")
    @Test
    void point() {
        assertThat(board.calculateTotalPoint(Team.WHITE)).isEqualTo(38);
    }

    @DisplayName("같은 세로줄에 위치한 폰의 개수만큼 점수 차감")
    @Test
    void updatePawnPoint() {
        board.movePiece(Position.of("d2"), Position.of("e3"));
        board.movePiece(Position.of("f2"), Position.of("e4"));
        assertThat(board.updatePawnPoint(Team.WHITE)).isEqualTo(1.5);
    }
}
