package chess.domain.chessgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.dto.console.BoardDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @DisplayName("팀 화이트 초기설정 테스트")
    void initializeBoard() {
        BoardDto actualBoard = new BoardDto(board);

        List<List<String>> expectedBoard = new ArrayList<>();
        expectedBoard.add(Arrays.asList("R", "N", "B", "Q", "K", "B", "N", "R"));
        expectedBoard.add(Arrays.asList("P", "P", "P", "P", "P", "P", "P", "P"));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."));
        expectedBoard.add(Arrays.asList("p", "p", "p", "p", "p", "p", "p", "p"));
        expectedBoard.add(Arrays.asList("r", "n", "b", "q", "k", "b", "n", "r"));

        assertThat(actualBoard.board()).isEqualTo(expectedBoard);
    }

    @Test
    @DisplayName("킹 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void kingWithInvalidMove() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("e1"), Point.of("e2")))
            .withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("킹 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void kingsMoveToInvalidPoint() {
        board.move(Point.of("e2"), Point.of("f3"));

        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("e1"), Point.of("e3")))
            .withMessage("불가능한 이동입니다.");
    }

    @ParameterizedTest
    @DisplayName("킹을 유효한 위치로 이동 테스트")
    @CsvSource(value = {"e3,f4", "f4,f5", "f5,e6", "e6,d6", "d6,c5", "c5,c4", "c4,d3", "d3,e3"})
    void kingWithValidMove(String source, String destination) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);

        chessGame.start();
        board.move(Point.of("e1"), Point.of(source));

        assertThatCode(() -> {
            chessGame.move(Point.of(source), Point.of(destination));
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("퀸을 유효한 위치로 이동 테스트")
    @CsvSource(value = {"d3,d6", "d6,d3", "d3,g6", "g6,d3", "d3,a6", "a6,d3"})
    void queenWithValidMove(String source, String destination) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);

        chessGame.start();
        board.move(Point.of("d1"), Point.of(source));

        assertThatCode(() -> {
            chessGame.move(Point.of(source), Point.of(destination));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void queensMoveToInvalidMove() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("d1"), Point.of("d2"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("퀸 이동 테스트(해당 위치로 가는 길이 막힌 경우 예외처리)")
    void queensMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("d1"), Point.of("d3"))
        ).withMessage("불가능한 이동입니다.");
    }

    @ParameterizedTest
    @DisplayName("비숍을 유효한 위치로 이동 테스트")
    @CsvSource(value = {"d4,f6", "f6,d4", "e3,b6", "b6,e3"})
    void bishopWithValidMove(String source, String destination) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        chessGame.start();

        board.move(Point.of("c1"), Point.of(source));

        assertThatCode(() -> {
            chessGame.move(Point.of(source), Point.of(destination));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("비숍 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void bishopMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("c1"), Point.of("c4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @ParameterizedTest
    @DisplayName("룩을 유효한 위치로 이동 테스트")
    @CsvSource(value = {"d3,d6", "d6,d3", "d3,h3", "h3,d3"})
    void rookWithValidMove(String source, String destination) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);

        chessGame.start();
        board.move(Point.of("a1"), Point.of(source));

        assertThatCode(() -> {
            chessGame.move(Point.of(source), Point.of(destination));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("룩 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void rookMoveToInvalidPoint() {
        board.move(Point.of("b2"), Point.of("b3"));

        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("a1"), Point.of("f6"))
        ).withMessage("불가능한 이동입니다.");
    }

    @ParameterizedTest
    @DisplayName("나이트을 유효한 위치로 이동 테스트")
    @CsvSource(value = {"c3,d5", "c3,e4", "e4,c3", "c3,a4", "a4,c3", "c3,b5", "b5,c3", "c3,b1"})
    void knightWithValidMove(String source, String destination) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);

        chessGame.start();

        board.move(Point.of("b1"), Point.of(source));

        assertThatCode(() -> {
            chessGame.move(Point.of(source), Point.of(destination));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("나이트 이동 테스트(해당 위치로 갈 수 없는 경우 예외처리)")
    void knightMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b1"), Point.of("b3"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트")
    void pawnWithValidMove() {
        assertThatCode(() -> {
            chessGame.move(Point.of("b2"), Point.of("b3"));
            chessGame.move(Point.of("b7"), Point.of("b6"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰은 뒤로 이동할 수 없다.")
    void pawnMovesBack() {
        board.move(Point.of("b2"), Point.of("b3"));
        board.move(Point.of("b7"), Point.of("b6"));

        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b3"), Point.of("b2"))
        ).withMessage("불가능한 이동입니다.");
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b6"), Point.of("b7"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(첫 이동인 경우 2칸 허용)")
    void pawnWithValidMoveWhenFirstMove() {
        assertThatCode(() -> {
            chessGame.move(Point.of("b2"), Point.of("b4"));
            chessGame.move(Point.of("b7"), Point.of("b5"));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(2칸 이동시 앞에 길이 막혀있으면 예외처리)")
    void pawnWithInvalidMoveWhenFirstMove() {
        board.move(Point.of("b2"), Point.of("c3"));

        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("c2"), Point.of("c4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰 이동 테스트(첫 이동 외에 2칸 이동을 시도한 경우 예외처리)")
    void pawnMoveToInvalidPointWhenSecondMove() {
        board.move(Point.of("b2"), Point.of("b3"));
        board.move(Point.of("c7"), Point.of("c6"));

        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b3"), Point.of("b5"))
        ).withMessage("불가능한 이동입니다.");
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("c6"), Point.of("c4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(대각선에 적이 있는 경우에만 대각선 이동 허용)")
    void pawnWithValidMoveWhenFindEnemy() {
        board.move(Point.of("e7"), Point.of("e5"));
        board.move(Point.of("d2"), Point.of("d4"));

        assertThatCode(() -> chessGame.move(Point.of("d4"), Point.of("e5")))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰을 유효한 위치로 이동 테스트(뒤쪽 대각선에 적이 있는 경우에는 예외처리)")
    void pawnWithInvalidMoveWhenFindEnemy() {
        board.move(Point.of("e7"), Point.of("e4"));
        board.move(Point.of("d2"), Point.of("d5"));

        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("d5"), Point.of("e4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("폰 이동 테스트(적이 없을 때 대각선으로 이동하려는 경우 예외처리)")
    void pawnMoveToInvalidPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("b2"), Point.of("c3"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("빈 공간을 이동하려는 경우 예외 처리")
    void moveEmptyPoint() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("c3"), Point.of("c4"))
        ).withMessage("불가능한 이동입니다.");
    }

    @Test
    @DisplayName("자신의 턴이 아닌 말을 이동시키려는 경우 예외처리")
    void moveEnemyPiece() {
        chessGame.move(Point.of("b2"), Point.of("b3"));
        assertThatIllegalArgumentException().isThrownBy(() ->
            chessGame.move(Point.of("a2"), Point.of("a3"))
        ).withMessage("불가능한 이동입니다.");
    }
}
