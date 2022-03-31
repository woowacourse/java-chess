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

class WhitePawnMovingTest {

    @DisplayName("첫 시작 위치에서 움직일 경우 1칸 또는 2칸 이동 가능")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "a4"})
    void isPawnMovingToInitialPositionTrue(String to) {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();
        Board board = new Board(new BoardInitializer());

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a2"), Position.from(to))).isTrue();
    }

    @DisplayName("첫 시작 위치에서 움직인 곳에 다른 기물이 있는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"a3", "a4"})
    void isPawnMovingToInitialPositionFalse(String to) {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a2"), new Pawn(Color.WHITE));
        havingPieceInPath.put(Position.from(to), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a2"), Position.from(to))).isFalse();
    }

    @Test
    @DisplayName("폰이 이동할 수 있는 범위로 이동")
    void isPawnMovingTrue() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("a4"))).isTrue();
    }

    @DisplayName("폰이 이동할 수 없는 방향으로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"a2", "b2", "b3", "b4", "a5"})
    void isPawnMovingFalse(String to) {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from(to))).isFalse();
    }

    @Test
    @DisplayName("폰이 이동하려는 위치에 다른 기물이 있는 경우")
    void isPawnMovingToHavingPieceFalse() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        havingPieceInPath.put(Position.from("a4"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("a4"))).isFalse();
    }

    @Test
    @DisplayName("대각선으로 움직인 곳에 다른 기물이 없는 경우")
    void isPawnMovingToNotHavingFalse() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("b4"))).isFalse();
    }
}