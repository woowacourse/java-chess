import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import chess.Piece;
import chess.PieceOperator;
import chess.State;
import chess.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    Board board;

    @BeforeEach
    @DisplayName("보드의 초기 설정")
    void setUp() {
        board = new Board();
        PieceOperator pieceOperator = new PieceOperator(board);

        pieceOperator.initialize();
    }

    @Test
    void createBoard() {
        Board board = new Board();
        assertThat(board.getState("a1")).isEqualTo(new State(Piece.EMPTY, Team.NONE));
        assertThat(board.getState("a2")).isEqualTo(new State(Piece.EMPTY, Team.NONE));
        assertThat(board.getState("a7")).isEqualTo(new State(Piece.EMPTY, Team.NONE));
        assertThat(board.getState("a8")).isEqualTo(new State(Piece.EMPTY, Team.NONE));
    }

    @Test
    @DisplayName("팀 화이트 초기설정 테스트")
    void initializeBoard() {
        assertThat(board.getState("a1")).isEqualTo(new State(Piece.ROOK, Team.WHITE));
        assertThat(board.getState("g1")).isEqualTo(new State(Piece.KNIGHT, Team.WHITE));
        assertThat(board.getState("c1")).isEqualTo(new State(Piece.BISHOP, Team.WHITE));
        assertThat(board.getState("d1")).isEqualTo(new State(Piece.QUEEN, Team.WHITE));
        assertThat(board.getState("e1")).isEqualTo(new State(Piece.KING, Team.WHITE));
        assertThat(board.getState("f2")).isEqualTo(new State(Piece.PAWN, Team.WHITE));
    }

    @Test
    @DisplayName("팀 블랙 초기설정 테스트")
    void initializeBoardAndBlackTeam() {
        assertThat(board.getState("a8")).isEqualTo(new State(Piece.ROOK, Team.BLACK));
        assertThat(board.getState("g8")).isEqualTo(new State(Piece.KNIGHT, Team.BLACK));
        assertThat(board.getState("c8")).isEqualTo(new State(Piece.BISHOP, Team.BLACK));
        assertThat(board.getState("d8")).isEqualTo(new State(Piece.QUEEN, Team.BLACK));
        assertThat(board.getState("e8")).isEqualTo(new State(Piece.KING, Team.BLACK));
        assertThat(board.getState("f7")).isEqualTo(new State(Piece.PAWN, Team.BLACK));
    }
}
