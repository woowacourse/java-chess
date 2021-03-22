import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Square;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    @DisplayName("보드의 초기 설정")
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);

        chessGame.start();
    }

    @Test
    @DisplayName("빈 보드 생성")
    void createBoard() {
        Board board = new Board();
        assertThat(board.getSquareState(Point.of("a1")))
                .isEqualTo(Square.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getSquareState(Point.of("a2")))
                .isEqualTo(Square.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getSquareState(Point.of("a7")))
                .isEqualTo(Square.of(Piece.EMPTY, Team.NONE));
        assertThat(board.getSquareState(Point.of("a8")))
                .isEqualTo(Square.of(Piece.EMPTY, Team.NONE));
    }

    @Test
    @DisplayName("팀 화이트 초기설정 테스트")
    void initializeBoard() {
        assertThat(board.getSquareState(Point.of("a1")))
                .isEqualTo(Square.of(Piece.ROOK, Team.WHITE));
        assertThat(board.getSquareState(Point.of("g1")))
                .isEqualTo(Square.of(Piece.KNIGHT, Team.WHITE));
        assertThat(board.getSquareState(Point.of("c1")))
                .isEqualTo(Square.of(Piece.BISHOP, Team.WHITE));
        assertThat(board.getSquareState(Point.of("d1")))
                .isEqualTo(Square.of(Piece.QUEEN, Team.WHITE));
        assertThat(board.getSquareState(Point.of("e1")))
                .isEqualTo(Square.of(Piece.KING, Team.WHITE));
        assertThat(board.getSquareState(Point.of("f2")))
                .isEqualTo(Square.of(Piece.PAWN, Team.WHITE));
    }

    @Test
    @DisplayName("팀 블랙 초기설정 테스트")
    void initializeBoardAndBlackTeam() {
        assertThat(board.getSquareState(Point.of("a8")))
                .isEqualTo(Square.of(Piece.ROOK, Team.BLACK));
        assertThat(board.getSquareState(Point.of("g8")))
                .isEqualTo(Square.of(Piece.KNIGHT, Team.BLACK));
        assertThat(board.getSquareState(Point.of("c8")))
                .isEqualTo(Square.of(Piece.BISHOP, Team.BLACK));
        assertThat(board.getSquareState(Point.of("d8")))
                .isEqualTo(Square.of(Piece.QUEEN, Team.BLACK));
        assertThat(board.getSquareState(Point.of("e8")))
                .isEqualTo(Square.of(Piece.KING, Team.BLACK));
        assertThat(board.getSquareState(Point.of("f7")))
                .isEqualTo(Square.of(Piece.PAWN, Team.BLACK));
    }

    @Test
    @DisplayName("킹이 잡히지 않았을 경우 게임 진행")
    void gameIsPlayingWhenKingNotDead() {
        assertThat(board.isContinued()).isTrue();
    }

    @Test
    @DisplayName("킹이 잡히면 게임종료 테스트")
    void gameIsOverWhenKingIsDead() {
        board.move(Point.of("e7"), Point.of("e5"));
        board.move(Point.of("e8"), Point.of("e7"));
        board.move(Point.of("e7"), Point.of("e6"));
        board.move(Point.of("e6"), Point.of("f5"));

        board.move(Point.of("d2"), Point.of("d4"));
        board.move(Point.of("d1"), Point.of("d3"));
        chessGame.move(Arrays.asList("move", "d3", "f5"));

        assertThat(chessGame.isRunning()).isFalse();
    }
}
