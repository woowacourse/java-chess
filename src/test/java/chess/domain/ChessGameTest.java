package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Team;
import chess.domain.utils.BoardInitializer;
import chess.domain.piece.Pawn;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.EnumMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ChessGameTest {
    ChessGame chessGame;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void isRunning() {
        chessGame.initBoard(BoardInitializer.init());
        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    void isReady() {
        assertThat(chessGame.isReady()).isTrue();
    }

    @Test
    void move() {
        chessGame.initBoard(BoardInitializer.init());
        chessGame.move("move a2 a3");
        Board board = chessGame.board();
        assertThat(board.pieceAt(Position.of("a3"))).isInstanceOf(Pawn.class);
    }

    @DisplayName("Pawn 이동 테스트 - 초기 2칸 이동")
    @Test
    void move_pawn() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when - then
        assertThatCode(() -> chessGame.move("move a2 a4")).doesNotThrowAnyException();
    }

    @DisplayName("Pawn 이동 테스트 - 초기 2칸 이동 시 상대팀의 말이 있는 경우 예외")
    @Test
    void move_pawn2() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move a2 a3");
        chessGame.move("move b7 b5");
        chessGame.move("move a3 a4");
        chessGame.move("move b5 b4");

        // then
        chessGame.move("move b2 b4");
        assertThat(outContent.toString()).contains("[ERROR] 말이 존재합니다.");
    }

    @DisplayName("Pawn 이동 테스트 - 한칸 이동")
    @Test
    void move_pawn3() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when - then
        assertThatCode(() -> chessGame.move("move a2 a3")).doesNotThrowAnyException();
    }

    @DisplayName("Pawn 이동 테스트 - 한칸 이동 시 상대팀의 말이 있는 경우 예외")
    @Test
    void move_pawn4() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move a2 a3");
        chessGame.move("move a7 a5");
        chessGame.move("move a3 a4");

        // then
        chessGame.move("move a5 a4");
        assertThat(outContent.toString()).contains("[ERROR] 말이 존재합니다.");
    }

    @DisplayName("Pawn 이동 테스트 - 대각선 이동 시 상대팀의 말이 없는 경우 예외")
    @Test
    void move_pawn5() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when - then
        chessGame.move("move a2 b3");
        assertThat(outContent.toString()).contains("[ERROR] 폰은 대각선에 상대팀의 말이 있는 경우 한 칸 이동할 수 있습니다.");
    }

    @DisplayName("Pawn 이동 테스트 - 대각선 이동 시 상대팀의 말이 있는 경우")
    @Test
    void move_pawn6() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move a2 a4");
        chessGame.move("move b7 b5");
        chessGame.move("move a4 a5");

        // then
        assertThatCode(() -> chessGame.move("move b5 a5")).doesNotThrowAnyException();
    }

    @Test
    void calculatePoint() {
        EnumMap<Team, Double> result = new EnumMap<>(Team.class);
        result.put(Team.BLACK, 38.0);
        result.put(Team.WHITE, 38.0);

        chessGame.initBoard(BoardInitializer.init());
        assertThat(chessGame.calculatePoint()).isEqualTo(result);
    }
}