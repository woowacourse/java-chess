package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

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
    @DisplayName("특정 위치에 있는 피스의 Color 가 일치하는 지 확인")
    void isMatchedColor() {
        Board board = new Board(new BoardInitializer());

        assertThat(board.isMatchedColor(Position.from("a2"), Color.WHITE)).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 말을 선택한 경우 예외발생")
    void moveFromEmptyException() {
        Board emptyBoard = new Board(HashMap::new);

        assertThatThrownBy(() -> emptyBoard.move(Position.from("e5"), Position.from("e7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @DisplayName("비숍의 이동 경로에 기물이 있을 경우 예외 발생")
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

    @DisplayName("룩의 이동 경로에 다른 기물이 있는 경우 예외 발생")
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

    @DisplayName("나이트는 이동 경로에 다른 기물이 있어도 이동 가능")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "c3"})
    void knightMove(String to) {
        Board board = new Board(new BoardInitializer());

        assertThatCode(() -> board.move(Position.from("b1"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("킹이 존재하는지 확인")
    void hasKing() {
        Board board = new Board(new BoardInitializer());

        assertThat(board.hasKing(Color.WHITE)).isTrue();
    }

    @Test
    @DisplayName("특정 위치에 피스가 존재하는지 확인")
    void hasPiece() {
        Board board = new Board(new BoardInitializer());

        assertThat(board.hasPiece(Position.from("a2"))).isTrue();
    }
}
