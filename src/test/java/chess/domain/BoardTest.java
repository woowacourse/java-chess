package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Bishop;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("비숍 이동 경로에 기물이 있을 경우 예외발생")
    @ParameterizedTest
    @ValueSource(strings = {"b8", "h8", "a1", "h2"})
    void bishopMoveException(String to) {
        Board board = setBishopBoard();

        assertThatThrownBy(() -> board.move(Position.from("e5"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 기물이 존재합니다.");
    }

    private Board setBishopBoard() {
        Map<Position, Piece> pieceExistBoard = new HashMap<>();

        Bishop bishop = new Bishop(Color.BLACK);

        pieceExistBoard.put(Position.from("e5"), bishop);
        pieceExistBoard.put(Position.from("d6"), bishop);
        pieceExistBoard.put(Position.from("f6"), bishop);
        pieceExistBoard.put(Position.from("d4"), bishop);
        pieceExistBoard.put(Position.from("f4"), bishop);

        return new Board(() -> pieceExistBoard);
    }

    @DisplayName("퀸의 이동 경로에 다른 기물이 있을 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"e8", "e1", "a5", "h5", "b8", "h8", "a1", "h2"})
    void queenMoveException(String to) {
        Board board = setQueenBoard();

        assertThatThrownBy(() -> board.move(Position.from("e5"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 기물이 존재합니다.");
    }

    private Board setQueenBoard() {
        Map<Position, Piece> pieceExistBoard = new HashMap<>();

        Piece queen = new Queen(Color.WHITE);
        pieceExistBoard.put(Position.from("e5"), queen);
        pieceExistBoard.put(Position.from("e6"), queen);
        pieceExistBoard.put(Position.from("e4"), queen);
        pieceExistBoard.put(Position.from("d5"), queen);
        pieceExistBoard.put(Position.from("f5"), queen);
        pieceExistBoard.put(Position.from("d6"), queen);
        pieceExistBoard.put(Position.from("f6"), queen);
        pieceExistBoard.put(Position.from("b2"), queen);
        pieceExistBoard.put(Position.from("f4"), queen);

        return new Board(() -> pieceExistBoard);
    }

    @DisplayName("룩 이동거리 사이에 기물이 있는 경우 예외")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c5", "c3", "e3"})
    void rookMoveException() {
        Board board = setRookBoard();

        assertThatThrownBy(() -> board.move(Position.from("a1"), Position.from("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 기물이 존재합니다.");
    }

    private Board setRookBoard() {
        Map<Position, Piece> pieceExistBoard = new HashMap<>();

        Piece rook = new Rook(Color.WHITE);
        pieceExistBoard.put(Position.from("a1"), rook);
        pieceExistBoard.put(Position.from("a2"), rook);

        return new Board(() -> pieceExistBoard);
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
