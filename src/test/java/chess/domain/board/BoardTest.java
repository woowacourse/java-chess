package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Blank;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(BoardFactory.initialize());
    }

    @Test
    @DisplayName("Position 피스의 색을 가져온다.")
    void getPieceTeamByPosition() {
        assertThat(board.getPieceTeamByPosition(Position.valueOf("a8"))).isEqualTo(Team.BLACK);
    }

    @Test
    @DisplayName("Position을 입력하면 piece를 반환한다.")
    void getPiece() {
        assertThat(board.getPiece(Position.valueOf("a8"))).isEqualTo(new Rook(Team.BLACK));
    }

    @Test
    @DisplayName("Board가 move하면 타겟의 기물을 반환한다.")
    void move() {
        assertThat(board.movePiece(Position.valueOf("a7"), Position.valueOf("a6"))).isInstanceOf(Blank.class);
    }

    @Test
    @DisplayName("해당 포지션에 piece가 존재하는지 확인한다.")
    void isPieceExist() {
        assertThat(board.isPieceExist(Position.valueOf("a7"))).isTrue();
    }
}
