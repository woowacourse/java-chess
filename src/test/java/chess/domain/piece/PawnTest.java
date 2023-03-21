package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

class PawnTest {

    @Test
    @DisplayName("화이트 폰이 처음 시작 지점이 아닌데, 2칸 가려고 할 때 false가 반환된다.")
    void canMove_WhitePawn_NotStartPosition() {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.WHITE);
        emptySquares.put(Position.of(0, 3), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(0, 3);
        Position targetPosition = Position.of(0, 5);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isFalse();
    }

    @Test
    @DisplayName("블랙 폰이 처음 처음 시작 지점이 아닌데, 2칸 가려고 할 때 false가 반환된다.")
    void canMove_BlackPawn_NotStartPosition() {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.WHITE);
        emptySquares.put(Position.of(0, 5), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(0, 5);
        Position targetPosition = Position.of(0, 3);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isFalse();
    }

    @Test
    @DisplayName("화이트 폰이 후진(y감소)하려고 할 때 false가 반환된다.")
    void canMove_WhitePawnMoveToBackward() {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.WHITE);
        emptySquares.put(Position.of(0, 2), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(0, 2);
        Position targetPosition = Position.of(0, 1);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isFalse();
    }

    @Test
    @DisplayName("블랙 폰이 전진(y증가)하려고 할 때 false가 반환된다.")
    void canMove_BlackPawnMoveToForward() {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.BLACK);
        emptySquares.put(Position.of(0, 6), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(0, 6);
        Position targetPosition = Position.of(0, 7);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    @DisplayName("폰은 옆으로 이동 할 수 없다.")
    void canMove_WithSameRow(int x) {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.WHITE);
        emptySquares.put(Position.of(3, 3), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(x, 3);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isFalse();
    }

    @Test
    @DisplayName("화이트 폰이 처음 시작 지점이고, 2칸 가려고 할 때 true가 반환된다.")
    void canMove_SuccessFirstWhite() {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.WHITE);
        emptySquares.put(Position.of(0, 1), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(0, 1);
        Position targetPosition = Position.of(0, 3);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isTrue();
    }

    @Test
    @DisplayName("블랙 폰이 처음 시작 지점이고, 2칸 가려고 할 때 true가 반환된다.")
    void canMove_SuccessFirstBlack() {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.BLACK);
        emptySquares.put(Position.of(0, 6), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(0, 6);
        Position targetPosition = Position.of(0, 4);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    @DisplayName("화이트 폰이 대각선 위 방향으로 1칸 가려고 할 때 true가 반환된다.")
    void canMove_SuccessCornerWhite(int x) {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.WHITE);
        emptySquares.put(Position.of(2, 3), piece);
        emptySquares.put(Position.of(x, 4), new Pawn(Team.BLACK));

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(2, 3);
        Position targetPosition = Position.of(x, 4);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    @DisplayName("블랙 폰이 대각선 밑 방향으로 1칸 가려고 할 때 true가 반환된다.")
    void canMove_SuccessCornerBlack(int x) {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.BLACK);
        emptySquares.put(Position.of(2, 4), piece);
        emptySquares.put(Position.of(x, 3), new Pawn(Team.WHITE));

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(2, 4);
        Position targetPosition = Position.of(x, 3);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isTrue();
    }

    @Test
    @DisplayName("화이트 폰이 위 방향으로 1칸 가려고 할 때 true가 반환된다.")
    void canMove_SuccessWhite() {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.WHITE);
        emptySquares.put(Position.of(3, 3), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(3, 3);
        Position targetPosition = Position.of(3, 4);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isTrue();
    }

    @Test
    @DisplayName("블랙 폰이 밑 방향으로 1칸 가려고 할 때 true가 반환된다.")
    void canMove_SuccessBlack() {
        // given
        Map<Position, Piece> emptySquares = getEmptySquares();
        Piece piece = new Pawn(Team.BLACK);
        emptySquares.put(Position.of(3, 4), piece);

        Board board = new Board(emptySquares);
        Position sourcePosition = Position.of(3, 4);
        Position targetPosition = Position.of(3, 3);

        // expect
        assertThat(piece.canMove(sourcePosition, targetPosition, new BoardSnapshot(board.getBoard())))
                .isTrue();
    }

    private Map<Position, Piece> getEmptySquares() {
        Map<Position, Piece> squares = new HashMap<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares.put(Position.of(x, y), Empty.INSTANCE);
            }
        }
        return squares;
    }
}
