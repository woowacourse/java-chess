package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BlackPawnMovingTest {

    @DisplayName("첫 시작 위치에서 움직일 경우 1칸 또는 2칸 이동 가능")
    @ParameterizedTest
    @ValueSource(strings = {"a6", "a5"})
    void isPawnMovingToInitialPositionTrue(String to) {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();
        Board board = new Board(new BoardInitializer());

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a7"), Position.from(to))).isTrue();
    }

    @DisplayName("첫 시작 위치에서 움직인 곳에 다른 기물이 있는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"a6", "a5"})
    void isPawnMovingToInitialPositionFalse(String to) {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a7"), new Pawn(Color.BLACK));
        havingPieceInPath.put(Position.from(to), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a7"), Position.from(to))).isFalse();
    }

    @Test
    @DisplayName("폰이 이동할 수 있는 범위로 이동")
    void isPawnMovingTrue() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("a5"))).isTrue();
    }

    @DisplayName("폰이 이동할 수 없는 방향으로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"a7", "b7", "b6", "b5", "a4"})
    void isPawnMovingFalse(String to) {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from(to))).isFalse();
    }

    @Test
    @DisplayName("폰이 이동하려는 위치에 다른 기물이 있는 경우")
    void isPawnMovingToHavingPieceFalse() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        havingPieceInPath.put(Position.from("a5"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("a5"))).isFalse();
    }

    @Test
    @DisplayName("대각선으로 움직인 곳에 다른 기물이 없는 경우")
    void isPawnMovingToNotHavingFalse() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("b5"))).isFalse();
    }
}
