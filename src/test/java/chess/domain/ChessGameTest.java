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

    @DisplayName("나이트 이동 테스트 - 앞에 말이 있는 경우에도 이동 가능")
    @Test
    void move_knight1() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when - then
        assertThatCode(() -> chessGame.move("move b1 c3")).doesNotThrowAnyException();
    }

    @DisplayName("나이트 이동 테스트 - 이동 불가능한 곳인 경우 예외")
    @Test
    void move_knight2() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move b1 c4");

        //then
        assertThat(outContent.toString()).contains("[ERROR] 해당 방향은 존재하지 않습니다.");
    }

    @DisplayName("룩 이동 테스트 - 앞에 같은 팀의 말이 있는 경우 이동 불가")
    @Test
    void move_rook1() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move a1 a2");

        //then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("룩 이동 테스트 - 앞에 말이 없는 경우 이동 가능")
    @Test
    void move_rook2() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move a2 a4");
        chessGame.move("move b7 b5");

        //then
        assertThatCode(() -> chessGame.move("move a1 a3")).doesNotThrowAnyException();
    }

    @DisplayName("룩 이동 테스트 - 앞에 말이 없는 경우 무한정 이동 가능")
    @Test
    void move_rook3() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move a2 a4");
        chessGame.move("move b7 b5");
        chessGame.move("move a4 a5");
        chessGame.move("move c7 c6");
        chessGame.move("move a5 b5");
        chessGame.move("move c6 c5");

        //then
        assertThatCode(() -> chessGame.move("move a1 a6")).doesNotThrowAnyException();
    }

    @DisplayName("룩 이동 테스트 - 앞에 같은 팀의 말이 없는 경우 무한정 이동 가능")
    @Test
    void move_rook4() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move a2 a4");
        chessGame.move("move b7 b5");
        chessGame.move("move a4 a5");
        chessGame.move("move c7 c6");
        chessGame.move("move a5 b5");
        chessGame.move("move c6 c5");

        //then
        assertThatCode(() -> chessGame.move("move a1 a7")).doesNotThrowAnyException();
    }

    @DisplayName("비숍 이동 테스트 - 앞에 같은 팀의 말이 있는 경우 이동 불가")
    @Test
    void move_bishop1() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move c1 b2");

        // then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("비숍 이동 테스트 - 가로 막는 말이 없는 경우 대각선으로 모든 방향 이동 가능")
    @Test
    void move_bishop2() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move b2 b3");
        chessGame.move("move b7 b5");

        // then
        assertThatCode(() -> chessGame.move("move c1 a3")).doesNotThrowAnyException(); // 좌상향
        assertThatCode(() -> chessGame.move("move c8 c6")).doesNotThrowAnyException(); // 좌하향
        assertThatCode(() -> chessGame.move("move a3 d6")).doesNotThrowAnyException(); // 우상향
        assertThatCode(() -> chessGame.move("move c6 d3")).doesNotThrowAnyException(); // 우하향
    }

    @DisplayName("비숍 이동 테스트 - 가로 막는 말이 다른 팀의 말인 경우 이동 가능")
    @Test
    void move_bishop3() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move b2 b3");
        chessGame.move("move b7 b5");
        chessGame.move("move c1 a3");
        chessGame.move("move c8 c6");

        // then
        assertThatCode(() -> chessGame.move("move a3 e7")).doesNotThrowAnyException();
    }

    @DisplayName("퀸 이동 테스트 - 같은 팀의 말이 앞에 존재하는 경우 이동 불가")
    @Test
    void move_queen1() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move d1 d2");

        // then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("퀸 이동 테스트 - 앞에 말이 없는 경우 8방향 이동 가능")
    @Test
    void move_queen2() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move d2 d4");
        chessGame.move("move d7 d5");

        // then
        assertThatCode(() -> chessGame.move("move d1 d3")).doesNotThrowAnyException(); // 위로 직진
        assertThatCode(() -> chessGame.move("move d8 d6")).doesNotThrowAnyException(); // 아래로 직진
        assertThatCode(() -> chessGame.move("move d3 c3")).doesNotThrowAnyException(); // 왼쪽으로 직진
        assertThatCode(() -> chessGame.move("move d6 e6")).doesNotThrowAnyException(); // 오른쪽으로 직진
        assertThatCode(() -> chessGame.move("move c3 d4")).doesNotThrowAnyException(); // 우상향 대각선
        assertThatCode(() -> chessGame.move("move e6 f5")).doesNotThrowAnyException(); // 우하향 대각선
        assertThatCode(() -> chessGame.move("move d4 e5")).doesNotThrowAnyException(); // 좌상향 대각선
        assertThatCode(() -> chessGame.move("move f5 e4")).doesNotThrowAnyException(); // 좌하향 대각선

    }

    @DisplayName("퀸 이동 테스트 - 앞에 말이 없는 경우 8방향 거리 무한정 이동 가능")
    @Test
    void move_queen3() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move d2 d4");
        chessGame.move("move e7 e5");
        chessGame.move("move d4 d5");
        chessGame.move("move a7 a6");
        chessGame.move("move d5 e5");
        chessGame.move("move a6 a5");

        // then
        assertThatCode(() -> chessGame.move("move d1 d6")).doesNotThrowAnyException(); // 위로 무한정 직진
    }

    @DisplayName("퀸 이동 테스트 - 다른 팀의 말이 존재하는 경우 이동 가능")
    @Test
    void move_queen4() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move d2 d4");
        chessGame.move("move e7 e5");
        chessGame.move("move d4 d5");
        chessGame.move("move a7 a6");
        chessGame.move("move d5 e5");
        chessGame.move("move a6 a5");

        // then
        assertThatCode(() -> chessGame.move("move d1 d7")).doesNotThrowAnyException();
    }

    @DisplayName("킹 이동 테스트 - 같은 팀의 말이 앞에 존재하는 경우 이동 불가")
    @Test
    void move_king1() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move e1 e2");

        // then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("킹 이동 테스트 - 앞에 말이 없는 경우 8방향 이동 가능")
    @Test
    void move_king2() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move e2 e4");
        chessGame.move("move e7 e5");
        chessGame.move("move e1 e2");
        chessGame.move("move e8 e7");

        // then
        assertThatCode(() -> chessGame.move("move e2 e3")).doesNotThrowAnyException(); // 위로 직진
        assertThatCode(() -> chessGame.move("move e7 e6")).doesNotThrowAnyException(); // 아래로 직진
        assertThatCode(() -> chessGame.move("move e3 d3")).doesNotThrowAnyException(); // 왼쪽으로 직진
        assertThatCode(() -> chessGame.move("move e6 f6")).doesNotThrowAnyException(); // 오른쪽으로 직진
        assertThatCode(() -> chessGame.move("move d3 e4")).doesNotThrowAnyException(); // 우상향 대각선
        assertThatCode(() -> chessGame.move("move f6 g5")).doesNotThrowAnyException(); // 우하향 대각선
        assertThatCode(() -> chessGame.move("move e4 d5")).doesNotThrowAnyException(); // 좌상향 대각선
        assertThatCode(() -> chessGame.move("move g5 f4")).doesNotThrowAnyException(); // 좌하향 대각선
    }

    @DisplayName("킹 이동 테스트 - 앞에 말이 없는 경우 8방향으로 한 칸만 이동 가능, 두칸 이상 시 예외")
    @Test
    void move_king3() {
        // given
        chessGame.initBoard(BoardInitializer.init());

        // when
        chessGame.move("move e2 e4");
        chessGame.move("move e7 e5");
        chessGame.move("move e1 e3");

        // then
        assertThat(outContent.toString()).contains("[ERROR] 이동할 수 있는 거리를 벗어났습니다.");
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