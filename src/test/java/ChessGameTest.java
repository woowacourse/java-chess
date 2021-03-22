import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.SquareState;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ChessGameTest {

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
    @DisplayName("킹 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void kingWithInvalidMove() {
        List<String> moveCommander = Arrays.asList("move", "e1", "e2");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("킹 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void kingsMoveToInvalidPoint() {
        List<String> moveCommander = Arrays.asList("move", "e1", "e3");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("킹을 유효한 위치로 이동 테스트")
    void kingWithValidMove() {
        board.move(Point.of("e2"), Point.of("e3")); // 폰 이동
        board.move(Point.of("e1"), Point.of("e2"));

        assertThat(board.getSquareState(Point.of("e2")))
                .isEqualTo(SquareState.of(Piece.KING, Team.WHITE));
    }

    @Test
    @DisplayName("퀸을 유효한 위치로 이동 테스트")
    void queenWithValidMove() {
        board.move(Point.of("d2"), Point.of("d3"));
        List<String> moveCommander = Arrays.asList("move", "d1", "d2");
        chessGame.move(moveCommander);

        assertThat(board.getSquareState(Point.of("d2")))
                .isEqualTo(SquareState.of(Piece.QUEEN, Team.WHITE));
    }

    @Test
    @DisplayName("퀸 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void queensMoveToInvalidMove() {
        List<String> moveCommander = Arrays.asList("move", "d1", "d2");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("퀸 이동 테스트(해당 위치로 가는 길이 막힌 경우 예외처리)")
    void queensMoveToInvalidPoint() {
        List<String> moveCommander = Arrays.asList("move", "d1", "e2");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("비숍을 유효한 위치로 이동 테스트")
    void bishopWithValidMove() {
        board.move(Point.of("d2"), Point.of("d3"));
        List<String> moveCommander = Arrays.asList("move", "c1", "h6");
        chessGame.move(moveCommander);
        assertThat(board.getSquareState(Point.of("h6")))
                .isEqualTo(SquareState.of(Piece.BISHOP, Team.WHITE));
    }

    @Test
    @DisplayName("비숍 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void bishopMoveToInvalidPoint() {
        List<String> moveCommander = Arrays.asList("move", "c1", "c4");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("룩을 유효한 위치로 이동 테스트")
    void rookWithValidMove() {
        board.move(Point.of("a2"), Point.of("a6"));
        List<String> moveCommander = Arrays.asList("move", "a1", "a5");
        chessGame.move(moveCommander);
        assertThat(board.getSquareState(Point.of("a5")))
                .isEqualTo(SquareState.of(Piece.ROOK, Team.WHITE));
    }

    @Test
    @DisplayName("룩 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void rookMoveToInvalidPoint() {
        board.move(Point.of("b2"), Point.of("b3"));
        List<String> moveCommander = Arrays.asList("move", "a1", "f6");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("나이트을 유효한 위치로 이동 테스트")
    void knightWithValidMove() {
        List<String> moveCommander = Arrays.asList("move", "b1", "c3");
        chessGame.move(moveCommander);
        assertThat(board.getSquareState(Point.of("c3")))
                .isEqualTo(SquareState.of(Piece.KNIGHT, Team.WHITE));
    }

    @Test
    @DisplayName("나이트 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void knightMoveToInvalidPoint() {
        List<String> moveCommander = Arrays.asList("move", "b1", "b3");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트")
    void pawnWithValidMove() {
        List<String> moveCommander = Arrays.asList("move", "b2", "b3");
        chessGame.move(moveCommander);
        assertThat(board.getSquareState(Point.of("b3")))
                .isEqualTo(SquareState.of(Piece.PAWN, Team.WHITE));
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(첫 이동인 경우 2칸 허용)")
    void pawnWithValidMoveWhenFirstMove() {
        List<String> moveCommander = Arrays.asList("move", "b2", "b4");
        chessGame.move(moveCommander);
        assertThat(board.getSquareState(Point.of("b4")))
                .isEqualTo(SquareState.of(Piece.PAWN, Team.WHITE));
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(대각선에 적이 있는 경우에만 대각선 이동 허용)")
    void pawnWithValidMoveWhenFindEnemy() {
        List<String> moveCommander1 = Arrays.asList("move", "d2", "d4");
        List<String> moveCommander2 = Arrays.asList("move", "e7", "e5");
        List<String> moveCommander3 = Arrays.asList("move", "d4", "e5");

        chessGame.move(moveCommander1);
        chessGame.move(moveCommander2);
        chessGame.move(moveCommander3);

        assertThat(board.getSquareState(Point.of("e5")))
                .isEqualTo(SquareState.of(Piece.PAWN, Team.WHITE));
        assertThat(board.getSquareState(Point.of("d4")))
                .isEqualTo(SquareState.of(Piece.EMPTY, Team.NONE));
    }

    @Test
    @DisplayName("폰 이동 테스트(첫 이동 외에 2칸 이동을 시도한 경우 예외처리)")
    void pawnMoveToInvalidPointWhenSecondMove() {
        List<String> moveCommander = Arrays.asList("move", "b2", "b3");
        chessGame.move(moveCommander);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(Arrays.asList("move", "b3", "b5")))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰 이동 테스트(적이 없을 때 대각선으로 이동하려는 경우 예외처리)")
    void pawnMoveToInvalidPoint() {
        List<String> moveCommander = Arrays.asList("move", "b2", "c3");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰 이동 테스트(적이 바로 앞에 있는 경우 2칸 이동 불가 테스트")
    void testPawnMoveWhenFrontOfEnemy() {
        List<String> moveCommander1 = Arrays.asList("move", "d2", "d4");
        List<String> moveCommander2 = Arrays.asList("move", "d7", "d5");
        List<String> moveCommander3 = Arrays.asList("move", "c1", "h6");

        chessGame.move(moveCommander1);
        chessGame.move(moveCommander2);
        chessGame.move(moveCommander3);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(Arrays.asList("move", "h7", "h5")))
                .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("빈 공간을 이동하려는 경우 예외 처리")
    void moveEmptyPoint() {
        List<String> moveCommander = Arrays.asList("move", "c3", "c4");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(moveCommander))
                .withMessage("불가능한 이동입니다.");
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
