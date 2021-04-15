package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.command.Commands;
import chess.domain.dto.PointDto;
import chess.domain.utils.PieceInitializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    ChessGame chessGame;

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
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    void isReady() {
        assertThat(chessGame.isReady()).isTrue();
    }

    @DisplayName("이동 테스트 - 소스와 타겟이 같을 때 이동 불가")
    @Test
    void move1() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a2 a2"));

        // then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("Pawn 이동 테스트 - 초기 2칸 이동")
    @Test
    void move_pawn() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a2 a4"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("Pawn 이동 테스트 - 초기 2칸 이동 시 상대팀의 말이 있는 경우 예외")
    @Test
    void move_pawn2() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a2 a3"));
        chessGame.move(new Commands("move b7 b5"));
        chessGame.move(new Commands("move a3 a4"));
        chessGame.move(new Commands("move b5 b4"));

        // then
        chessGame.move(new Commands("move b2 b4"));
        assertThat(outContent.toString()).contains("[ERROR] 경로에 말이 존재합니다.");
    }

    @DisplayName("Pawn 이동 테스트 - 한칸 이동")
    @Test
    void move_pawn3() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when - then
        chessGame.move(new Commands("move a2 a3"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("Pawn 이동 테스트 - 한칸 이동 시 상대팀의 말이 있는 경우 예외")
    @Test
    void move_pawn4() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a2 a3"));
        chessGame.move(new Commands("move a7 a5"));
        chessGame.move(new Commands("move a3 a4"));

        // then
        chessGame.move(new Commands("move a5 a4"));
        assertThat(outContent.toString()).contains("[ERROR] 경로에 말이 존재합니다.");
    }

    @DisplayName("Pawn 이동 테스트 - 대각선 이동 시 상대팀의 말이 없는 경우 예외")
    @Test
    void move_pawn5() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when - then
        chessGame.move(new Commands("move a2 b3"));
        assertThat(outContent.toString()).contains("[ERROR] 폰은 대각선에 상대팀의 말이 있는 경우 한 칸 이동할 수 있습니다.");
    }

    @DisplayName("Pawn 이동 테스트 - 대각선 이동 시 상대팀의 말이 있는 경우")
    @Test
    void move_pawn6() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a2 a4"));
        chessGame.move(new Commands("move b7 b5"));
        chessGame.move(new Commands("move a4 b5"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("나이트 이동 테스트 - 앞에 말이 있는 경우에도 이동 가능")
    @Test
    void move_knight1() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move b1 c3"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("나이트 이동 테스트 - 이동 불가능한 곳인 경우 예외")
    @Test
    void move_knight2() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move b1 c4"));

        //then
        assertThat(outContent.toString()).contains("[ERROR] 해당 방향은 존재하지 않습니다.");
    }

    @DisplayName("나이트 이동 테스트 - 이동 가능")
    @Test
    void move_knight3() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move b1 c3"));

        //then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("나이트 이동 테스트 - 이동 가능한 거리를 벗어났습니다.")
    @Test
    void move_knight4() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move b1 d5"));

        //then
        assertThat(outContent.toString()).contains("[ERROR] 이동할 수 있는 거리를 벗어났습니다.");
    }

    @DisplayName("룩 이동 테스트 - 앞에 같은 팀의 말이 있는 경우 이동 불가")
    @Test
    void move_rook1() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a1 a2"));

        //then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("룩 이동 테스트 - 앞에 말이 없는 경우 이동 가능")
    @Test
    void move_rook2() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a2 a4"));
        chessGame.move(new Commands("move b7 b5"));
        chessGame.move(new Commands("move a1 a3"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("룩 이동 테스트 - 앞에 말이 없는 경우 무한정 이동 가능")
    @Test
    void move_rook3() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a2 a4"));
        chessGame.move(new Commands("move b7 b5"));
        chessGame.move(new Commands("move a4 b5"));
        chessGame.move(new Commands("move c7 c6"));
        chessGame.move(new Commands("move b5 c6"));
        chessGame.move(new Commands("move d7 d6"));
        chessGame.move(new Commands("move a1 a6"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("룩 이동 테스트 - 앞에 같은 팀의 말이 없는 경우 무한정 이동 가능")
    @Test
    void move_rook4() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move a2 a4"));
        chessGame.move(new Commands("move b7 b5"));
        chessGame.move(new Commands("move a4 b5"));
        chessGame.move(new Commands("move c7 c5"));
        chessGame.move(new Commands("move a1 a7"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("비숍 이동 테스트 - 앞에 같은 팀의 말이 있는 경우 이동 불가")
    @Test
    void move_bishop1() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move c1 b2"));

        // then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("비숍 이동 테스트 - 가로 막는 말이 없는 경우 대각선으로 모든 방향 이동 가능")
    @Test
    void move_bishop2() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
        chessGame.move(new Commands("move b2 b3"));
        chessGame.move(new Commands("move b7 b6"));

        // when
        chessGame.move(new Commands("move c1 a3")); // 좌상향
        chessGame.move(new Commands("move c8 a6")); // 좌하향
        chessGame.move(new Commands("move a3 d6")); // 우상향
        chessGame.move(new Commands("move a6 d3")); // 우하향

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("비숍 이동 테스트 - 가로 막는 말이 다른 팀의 말인 경우 이동 가능")
    @Test
    void move_bishop3() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
        chessGame.move(new Commands("move b2 b3"));
        chessGame.move(new Commands("move b7 b6"));
        chessGame.move(new Commands("move c1 a3"));
        chessGame.move(new Commands("move c8 a6"));

        // when
        chessGame.move(new Commands("move a3 e7"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("퀸 이동 테스트 - 같은 팀의 말이 앞에 존재하는 경우 이동 불가")
    @Test
    void move_queen1() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move d1 d2"));

        // then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("퀸 이동 테스트 - 앞에 말이 없는 경우 8방향 이동 가능")
    @Test
    void move_queen2() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
        chessGame.move(new Commands("move d2 d4"));
        chessGame.move(new Commands("move d7 d5"));

        // when
        chessGame.move(new Commands("move d1 d3")); // 위로 직진
        chessGame.move(new Commands("move d8 d6")); // 아래로 직진
        chessGame.move(new Commands("move d3 c3")); // 왼쪽으로 직진
        chessGame.move(new Commands("move d6 e6")); // 오른쪽으로 직진
        chessGame.move(new Commands("move c3 b4")); // 좌상향 대각선
        chessGame.move(new Commands("move e6 f5")); // 우하향 대각선
        chessGame.move(new Commands("move b4 c5")); // 좌상향 대각선
        chessGame.move(new Commands("move f5 e4")); // 좌하향 대각선

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("퀸 이동 테스트 - 앞에 말이 없는 경우 8방향 거리 무한정 이동 가능")
    @Test
    void move_queen3() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
        chessGame.move(new Commands("move d2 d4"));
        chessGame.move(new Commands("move e7 e5"));
        chessGame.move(new Commands("move h2 h3"));
        chessGame.move(new Commands("move a7 a6"));
        chessGame.move(new Commands("move d4 e5"));
        chessGame.move(new Commands("move a6 a5"));

        // when
        chessGame.move(new Commands("move d1 d6")); // 위로 무한정 직진

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("퀸 이동 테스트 - 다른 팀의 말이 존재하는 경우 이동 가능")
    @Test
    void move_queen4() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
        chessGame.move(new Commands("move d2 d4"));
        chessGame.move(new Commands("move e7 e5"));
        chessGame.move(new Commands("move h2 h3"));
        chessGame.move(new Commands("move a7 a6"));
        chessGame.move(new Commands("move d4 e5"));
        chessGame.move(new Commands("move a6 a5"));

        // when
        chessGame.move(new Commands("move d1 d7"));

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("킹 이동 테스트 - 같은 팀의 말이 앞에 존재하는 경우 이동 불가")
    @Test
    void move_king1() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move e1 e2"));

        // then
        assertThat(outContent.toString()).contains("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
    }

    @DisplayName("킹 이동 테스트 - 앞에 말이 없는 경우 8방향 이동 가능")
    @Test
    void move_king2() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
        chessGame.move(new Commands("move e2 e4"));
        chessGame.move(new Commands("move e7 e5"));
        chessGame.move(new Commands("move e1 e2"));
        chessGame.move(new Commands("move e8 e7"));

        // when
        chessGame.move(new Commands("move e2 e3")); // 위로 직진
        chessGame.move(new Commands("move e7 e6")); // 아래로 직진
        chessGame.move(new Commands("move e3 d3")); // 왼쪽으로 직진
        chessGame.move(new Commands("move e6 f6")); // 오른쪽으로 직진
        chessGame.move(new Commands("move d3 c4")); // 좌상향 대각선
        chessGame.move(new Commands("move f6 g5")); // 우하향 대각선
        chessGame.move(new Commands("move c4 d5")); // 우상향 대각선
        chessGame.move(new Commands("move g5 f4")); // 하향 대각선

        // then
        assertThat("").contains(outContent.toString());
    }

    @DisplayName("킹 이동 테스트 - 앞에 말이 없는 경우 8방향으로 한 칸만 이동 가능, 두칸 이상 시 예외")
    @Test
    void move_king3() {
        // given
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));

        // when
        chessGame.move(new Commands("move e2 e4"));
        chessGame.move(new Commands("move e7 e5"));
        chessGame.move(new Commands("move e1 e3"));

        // then
        assertThat(outContent.toString()).contains("[ERROR] 이동할 수 있는 거리를 벗어났습니다.");
    }

    @Test
    void calculatePoint() {
        EnumMap<Team, Double> result = new EnumMap<>(Team.class);
        result.put(Team.BLACK, 38.0);
        result.put(Team.WHITE, 38.0);

        PointDto resultDto = new PointDto(result);

        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
        assertThat(chessGame.pointDto()).isEqualTo(resultDto);
    }
}