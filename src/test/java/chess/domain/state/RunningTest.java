package chess.domain.state;

import chess.domain.piece.*;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RunningTest {
    private static final Map<Position, Piece> chessBoard = init();
    private GameState running;

    private static Map<Position, Piece> init() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        return chessBoard;
    }

    @BeforeEach
    void setup() {
        running = new Running(chessBoard, TeamColor.BLACK);
    }

    @Test
    @DisplayName("킹이 잡혔을 시 Finished 상태로 변경")
    void move() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        chessBoard.put(Position.valueOf("d4"), new King(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("c3"), new Pawn(TeamColor.WHITE));
        running = new Running(chessBoard, TeamColor.WHITE);

        assertThat(running.move(Position.valueOf("c3"), Position.valueOf("d4"))).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("움직일 수 없는 방향으로 이동 시, exception 발생")
    void move1() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        chessBoard.put(Position.valueOf("e4"), new King(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("c3"), new Pawn(TeamColor.WHITE));
        running = new Running(chessBoard, TeamColor.WHITE);

        assertThatThrownBy(() -> running.move(Position.valueOf("c3"), Position.valueOf("d4"))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈칸으로 이동")
    void move2() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        chessBoard.put(Position.valueOf("c3"), new Pawn(TeamColor.WHITE));
        running = new Running(chessBoard, TeamColor.WHITE);

        GameState moved = running.move(Position.valueOf("c3"), Position.valueOf("c4"));
        Map<Position, Piece> result = moved.getChessBoard();
        assertThat(result.get(Position.valueOf("c3"))).isEqualTo(Blank.INSTANCE);
        assertThat(result.get(Position.valueOf("c4"))).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("말을 잡은 경우")
    void move3() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        chessBoard.put(Position.valueOf("d4"), new Bishop(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("c3"), new Pawn(TeamColor.WHITE));
        running = new Running(chessBoard, TeamColor.WHITE);

        GameState moved = running.move(Position.valueOf("c3"), Position.valueOf("d4"));
        Map<Position, Piece> result = moved.getChessBoard();
        assertThat(result.get(Position.valueOf("c3"))).isEqualTo(Blank.INSTANCE);
        assertThat(result.get(Position.valueOf("d4"))).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("차례가 아닌데 이동하려고 하는 경우, exception 발생")
    void move4() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        chessBoard.put(Position.valueOf("c3"), new Pawn(TeamColor.WHITE));
        running = new Running(chessBoard, TeamColor.BLACK);

        assertThatThrownBy(() -> running.move(Position.valueOf("c3"), Position.valueOf("d4")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("잡는 말이 같은 팀의 말인 경우, exception 발생")
    void move5() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        chessBoard.put(Position.valueOf("a1"), new Rook(TeamColor.WHITE));
        chessBoard.put(Position.valueOf("a5"), new Pawn(TeamColor.WHITE));
        running = new Running(chessBoard, TeamColor.WHITE);

        assertThatThrownBy(() -> running.move(Position.valueOf("a1"), Position.valueOf("a5")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("종료시 Finished 상태로 변경")
    void terminate() {
        assertThat(running.terminate()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("Running 상태인지 확인")
    void isRunning() {
        assertTrue(running.isRunning());
    }
}