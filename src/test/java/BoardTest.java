import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.SquareState;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    @DisplayName("보드의 초기 설정")
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);

        chessGame.initialize();
    }

    @Test
    @DisplayName("빈 보드 생성")
    void createBoard() {
        Board board = new Board();
        assertThat(board.getSquareState(Point.of("a1")))
            .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getSquareState(Point.of("a2")))
            .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getSquareState(Point.of("a7")))
            .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getSquareState(Point.of("a8")))
            .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
    }

    @Test
    @DisplayName("팀 화이트 초기설정 테스트")
    void initializeBoard() {
        assertThat(board.getSquareState(Point.of("a1")))
            .isEqualTo(SquareState.of(Piece.ROOK, Team.WHITE));
        assertThat(board.getSquareState(Point.of("g1")))
            .isEqualTo(SquareState.of(Piece.KNIGHT, Team.WHITE));
        assertThat(board.getSquareState(Point.of("c1")))
            .isEqualTo(SquareState.of(Piece.BISHOP, Team.WHITE));
        assertThat(board.getSquareState(Point.of("d1")))
            .isEqualTo(SquareState.of(Piece.QUEEN, Team.WHITE));
        assertThat(board.getSquareState(Point.of("e1")))
            .isEqualTo(SquareState.of(Piece.KING, Team.WHITE));
        assertThat(board.getSquareState(Point.of("f2")))
            .isEqualTo(SquareState.of(Piece.PAWN, Team.WHITE));
    }

    @Test
    @DisplayName("팀 블랙 초기설정 테스트")
    void initializeBoardAndBlackTeam() {
        assertThat(board.getSquareState(Point.of("a8")))
            .isEqualTo(SquareState.of(Piece.ROOK, Team.BLACK));
        assertThat(board.getSquareState(Point.of("g8")))
            .isEqualTo(SquareState.of(Piece.KNIGHT, Team.BLACK));
        assertThat(board.getSquareState(Point.of("c8")))
            .isEqualTo(SquareState.of(Piece.BISHOP, Team.BLACK));
        assertThat(board.getSquareState(Point.of("d8")))
            .isEqualTo(SquareState.of(Piece.QUEEN, Team.BLACK));
        assertThat(board.getSquareState(Point.of("e8")))
            .isEqualTo(SquareState.of(Piece.KING, Team.BLACK));
        assertThat(board.getSquareState(Point.of("f7")))
            .isEqualTo(SquareState.of(Piece.PAWN, Team.BLACK));
    }

    @Test
    @DisplayName("킹이 잡히지 않았을 경우 게임 진행")
    void gameIsPlayingWhenKingNotDead() {
        assertThat(board.isContinued()).isTrue();
    }

    @Test
    @DisplayName("킹이 잡히면 게임종료 테스트")
    void gameIsOverWhenKingIsDead() {
        board.move(Point.of("e8"), Point.of("e3"));
        chessGame.move(Point.of("d2"), Point.of("e3"), Team.WHITE);

        assertThat(board.isContinued()).isFalse();
    }
}
