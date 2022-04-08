package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createInitChessBoard();
    }

    @Test
    @DisplayName("source 위치의 Piece 를 target 위치로 이동시킨다.")
    void movePiece() {
        board.movePiece(Position.of('a', 2), Position.of('a', 4));

        assertThat(board.getPiece(Position.of('a', 2))).isInstanceOf(Blank.class);
        assertThat(board.getPiece(Position.of('a', 4))).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("Position 으로 기물을 찾고 해당 기물을 Team 을 반환한다.")
    void getTeamOfPiece() {
        assertThat(board.getTeamOfPiece(Position.of('a', 2))).isEqualTo(Team.WHITE);
    }

    @Test
    @DisplayName("Position 을 입력하면 piece 를 반환한다.")
    void getPiece() {
        Board board = BoardFactory.createInitChessBoard();
        assertThat(board.getPiece(Position.of('a', 8))).isEqualTo(new Rook(Team.BLACK));
    }
}
