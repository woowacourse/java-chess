package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.view.Output;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    private static ByteArrayOutputStream outputMessage;

    @BeforeEach
    void setUpStreams() {
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMessage));
    }

    @AfterEach
    void restoresStreams() {
        System.setOut(System.out);
    }

    @Test
    @DisplayName("게임 점수 계산")
    void showStatus() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.from("a1"), new Queen(Color.WHITE));
        pieces.put(Position.from("a2"), new Rook(Color.BLACK));

        Board board = new Board(() -> pieces);

        Status status = new Status(board);
        status.show(Output::printScore);
        assertEquals("검은색의 점수는 5.0점 입니다.\n흰색의 점수는 9.0점 입니다.\n", outputMessage.toString());
    }

    @Test
    @DisplayName("폰이 같은 x 에 존재할 경우의 게임 점수 계산")
    void showStatusPawnScore0_5() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.from("a1"), new Pawn(Color.WHITE));
        pieces.put(Position.from("a2"), new Pawn(Color.WHITE));
        pieces.put(Position.from("a3"), new Pawn(Color.BLACK));

        Board board = new Board(() -> pieces);

        Status status = new Status(board);
        status.show(Output::printScore);
        assertEquals("검은색의 점수는 1.0점 입니다.\n흰색의 점수는 1.0점 입니다.\n", outputMessage.toString());
    }
}
