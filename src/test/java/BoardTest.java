import static org.assertj.core.api.Assertions.assertThat;

import chess.PieceOperator;
import chess.board.Board;
import chess.board.Point;
import chess.board.State;
import chess.board.Team;
import chess.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

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
        assertThat(board.getState(Point.of("a1"))).isEqualTo(State.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getState(Point.of("a2"))).isEqualTo(State.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getState(Point.of("a7"))).isEqualTo(State.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getState(Point.of("a8"))).isEqualTo(State.of(Piece.EMPTY, Team.NONE));
    }

    @Test
    @DisplayName("팀 화이트 초기설정 테스트")
    void initializeBoard() {
        assertThat(board.getState(Point.of("a1"))).isEqualTo(State.of(Piece.ROOK, Team.WHITE));
        assertThat(board.getState(Point.of("g1"))).isEqualTo(State.of(Piece.KNIGHT, Team.WHITE));
        assertThat(board.getState(Point.of("c1"))).isEqualTo(State.of(Piece.BISHOP, Team.WHITE));
        assertThat(board.getState(Point.of("d1"))).isEqualTo(State.of(Piece.QUEEN, Team.WHITE));
        assertThat(board.getState(Point.of("e1"))).isEqualTo(State.of(Piece.KING, Team.WHITE));
        assertThat(board.getState(Point.of("f2"))).isEqualTo(State.of(Piece.PAWN, Team.WHITE));
    }

    @Test
    @DisplayName("팀 블랙 초기설정 테스트")
    void initializeBoardAndBlackTeam() {
        assertThat(board.getState(Point.of("a8"))).isEqualTo(State.of(Piece.ROOK, Team.BLACK));
        assertThat(board.getState(Point.of("g8"))).isEqualTo(State.of(Piece.KNIGHT, Team.BLACK));
        assertThat(board.getState(Point.of("c8"))).isEqualTo(State.of(Piece.BISHOP, Team.BLACK));
        assertThat(board.getState(Point.of("d8"))).isEqualTo(State.of(Piece.QUEEN, Team.BLACK));
        assertThat(board.getState(Point.of("e8"))).isEqualTo(State.of(Piece.KING, Team.BLACK));
        assertThat(board.getState(Point.of("f7"))).isEqualTo(State.of(Piece.PAWN, Team.BLACK));
    }
}
