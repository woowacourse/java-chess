package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;
    private CatchPieces catchPieces;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createChessBoard();
        catchPieces = new CatchPieces();
    }

    @Test
    @DisplayName("source 위치의 Piece 를 target 위치로 이동시킨다.")
    void movePiece() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("a4"), catchPieces);

        assertThat(board.getPiece(Position.valueOf("a2"))).isInstanceOf(Blank.class);
        assertThat(board.getPiece(Position.valueOf("a4"))).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("Position 으로 기물을 찾고 해당 기물을 Team 을 반환한다.")
    void getTeamOfPiece() {
        assertThat(board.getTeamOfPiece(Position.valueOf("a2"))).isEqualTo(Team.WHITE);
    }

    @Test
    @DisplayName("Position 을 입력하면 piece 를 반환한다.")
    void getPiece() {
        Board board = BoardFactory.createChessBoard();
        assertThat(board.getPiece(Position.valueOf("a8"))).isEqualTo(new Rook(Team.BLACK));
    }
}
