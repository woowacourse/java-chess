package chess.domain.state;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

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
    void setup(){
        running = new Running(chessBoard, TeamColor.BLACK);
    }

    //todo: move 테스트코드 작성, result 테스트코드 작성
    @Test
    void move() {
    }

    @Test
    void terminate() {
        assertThat(running.terminate()).isInstanceOf(Finished.class);
    }

    @Test
    void isRunning() {
        assertTrue(running.isRunning());
    }
}