import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessGame;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.SquareState;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
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
        chessGame = new ChessGame(board, new Turn());
        chessGame.start();
    }

    @Test
    @DisplayName("빈 보드 생성")
    void createBoard() {
        Board board = new Board();
        assertThat(board.squareState(Point.of("a1")))
            .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
        assertThat(board.squareState(Point.of("a2")))
            .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
        assertThat(board.squareState(Point.of("a7")))
            .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
        assertThat(board.squareState(Point.of("a8")))
            .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
    }

    @Test
    @DisplayName("팀 화이트 초기설정 테스트")
    void initializeBoard() {
        assertThat(board.squareState(Point.of("a1")))
            .isEqualTo(SquareState.of(Piece.ROOK, Team.WHITE));
        assertThat(board.squareState(Point.of("g1")))
            .isEqualTo(SquareState.of(Piece.KNIGHT, Team.WHITE));
        assertThat(board.squareState(Point.of("c1")))
            .isEqualTo(SquareState.of(Piece.BISHOP, Team.WHITE));
        assertThat(board.squareState(Point.of("d1")))
            .isEqualTo(SquareState.of(Piece.QUEEN, Team.WHITE));
        assertThat(board.squareState(Point.of("e1")))
            .isEqualTo(SquareState.of(Piece.KING, Team.WHITE));
        assertThat(board.squareState(Point.of("f2")))
            .isEqualTo(SquareState.of(Piece.PAWN, Team.WHITE));
    }

    @Test
    @DisplayName("팀 블랙 초기설정 테스트")
    void initializeBoardAndBlackTeam() {
        assertThat(board.squareState(Point.of("a8")))
            .isEqualTo(SquareState.of(Piece.ROOK, Team.BLACK));
        assertThat(board.squareState(Point.of("g8")))
            .isEqualTo(SquareState.of(Piece.KNIGHT, Team.BLACK));
        assertThat(board.squareState(Point.of("c8")))
            .isEqualTo(SquareState.of(Piece.BISHOP, Team.BLACK));
        assertThat(board.squareState(Point.of("d8")))
            .isEqualTo(SquareState.of(Piece.QUEEN, Team.BLACK));
        assertThat(board.squareState(Point.of("e8")))
            .isEqualTo(SquareState.of(Piece.KING, Team.BLACK));
        assertThat(board.squareState(Point.of("f7")))
            .isEqualTo(SquareState.of(Piece.PAWN, Team.BLACK));
    }

    @Test
    @DisplayName("킹이 잡혔는지 확인")
    void gameIsOverWhenKingIsDead() {
        assertThat(board.isKingDead()).isFalse();
        board.move(Point.of("e8"), Point.of("e3"));
        chessGame.tryToMove(Point.of("d2"), Point.of("e3"));

        assertThat(board.isKingDead()).isTrue();
    }

    @Test
    @DisplayName("현재 체스판 위의 말들의 점수를 계산하는 테스트")
    void testScore() {
        assertThat(chessGame.score(Team.WHITE)).isEqualTo(38);
        assertThat(chessGame.score(Team.BLACK)).isEqualTo(38);
    }

    @Test
    @DisplayName("세로 줄에 같은 팀의 폰이 있는 경우 0.5점으로 계산")
    void testScoreWhenSameTeamPawnInSameColumn() {
        Board board = new Board();

        board.putSymmetrically(Piece.PAWN, Point.of("c4"));
        board.putSymmetrically(Piece.PAWN, Point.of("c3"));

        assertThat(board.score(Team.WHITE)).isCloseTo(1.0d, Assertions.offset(0.01d));
    }

    @Test
    @DisplayName("진행중인 게임의 체스판 점수를 계산한다.")
    void testScoreWhenSomePiecesNotExist() {
        Board board = new Board();

        // 체스 3단계 예시와 동일
        board.putSymmetrically(Piece.KNIGHT, Point.of("f4"));
        board.putSymmetrically(Piece.QUEEN, Point.of("g4"));
        board.putSymmetrically(Piece.ROOK, Point.of("e1"));
        board.putSymmetrically(Piece.KING, Point.of("f1"));
        board.putSymmetrically(Piece.PAWN, Point.of("f2"));
        board.putSymmetrically(Piece.PAWN, Point.of("g2"));
        board.putSymmetrically(Piece.PAWN, Point.of("f3"));
        board.putSymmetrically(Piece.PAWN, Point.of("h3"));

        assertThat(board.score(Team.WHITE)).isCloseTo(19.5d, Assertions.offset(0.01d));
    }
}
