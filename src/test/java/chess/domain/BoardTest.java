package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

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
    @DisplayName("초기화된 체스판인지 확인")
    void isInitializedTrue() {
        Board board = new Board(new BoardInitializer());

        assertThat(board.isInitialized(new BoardInitializer())).isTrue();
    }

    @Test
    @DisplayName("체스 말 이동 후 초기화된 체스판이 아닌 것을 확인")
    void isInitializedFalse() {
        Board board = new Board(new BoardInitializer());
        board.move(Position.from("a2"), Position.from("a3"));

        assertThat(board.isInitialized(new BoardInitializer())).isFalse();
    }

    @Test
    @DisplayName("존재하지 않는 말을 선택한 경우 예외발생")
    void move() {
        Board emptyBoard = new Board(HashMap::new);

        assertThatThrownBy(() -> emptyBoard.move(Position.from("e5"), Position.from("e7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("게임 점수 계산")
    void showStatus() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.from("a1"), new Queen(Color.WHITE));
        pieces.put(Position.from("a2"), new Rook(Color.BLACK));

        Board board = new Board(() -> pieces);

        board.showStatus(Output::printScore);
        assertEquals("흰색의 점수는 9.0점 입니다.\n검은색의 점수는 5.0점 입니다.\n", outputMessage.toString());
    }

    @Test
    @DisplayName("폰이 같은 x 에 존재할 경우의 게임 점수 계산")
    void showStatusPawnScore0_5() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.from("a1"), new Pawn(Color.WHITE));
        pieces.put(Position.from("a2"), new Pawn(Color.WHITE));
        pieces.put(Position.from("a3"), new Pawn(Color.BLACK));

        Board board = new Board(() -> pieces);

        board.showStatus(Output::printScore);
        assertEquals("흰색의 점수는 1.0점 입니다.\n검은색의 점수는 1.0점 입니다.\n", outputMessage.toString());
    }
}
