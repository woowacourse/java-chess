package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WhitePawnMovingTest {

    @Test
    @DisplayName("첫 시작 위치에서 1칸 움직일 경우")
    void isPawnMovingToInitialPositionOneStepTrue() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();
        Board board = new Board(new BoardInitializer());

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a2"), Position.from("a3"))).isTrue();
    }

    @Test
    @DisplayName("첫 시작 위치에서 1칸 움직인 곳에 다른 기물이 있는 경우")
    void isPawnMovingToInitialPositionOneStepFalse() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a2"), new Pawn(Color.WHITE));
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a2"), Position.from("a3"))).isFalse();
    }

    @Test
    @DisplayName("첫 시작 위치에서 2칸 움직일 경우")
    void isPawnMovingToInitialPositionTwoStepTrue() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();
        Board board = new Board(new BoardInitializer());

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a2"), Position.from("a4"))).isTrue();
    }

    @Test
    @DisplayName("첫 시작 위치에서 2칸 움직인 곳에 다른 기물이 있는 경우")
    void isPawnMovingToInitialPositionTwoStepFalse() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a2"), new Pawn(Color.WHITE));
        havingPieceInPath.put(Position.from("a4"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a2"), Position.from("a4"))).isFalse();
    }

    @Test
    @DisplayName("1칸 움직일 경우")
    void isPawnMovingOneStepTrue() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("a4"))).isTrue();
    }

    @Test
    @DisplayName("1칸 움직인 곳에 다른 기물이 있는 경우")
    void isPawnMovingOneStepFalse() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        havingPieceInPath.put(Position.from("a4"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("a4"))).isFalse();
    }

    @Test
    @DisplayName("2칸 움직일 경우")
    void isPawnMovingTwoStepFalse() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("a5"))).isFalse();
    }

    @Test
    @DisplayName("대각선으로 움직일 경우")
    void isPawnMovingDiagonalTrue() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        havingPieceInPath.put(Position.from("b4"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("b4"))).isTrue();
    }

    @Test
    @DisplayName("대각선으로 움직인 곳에 다른 기물이 없는 경우")
    void isPawnMovingDiagonalFalse() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("b4"))).isFalse();
    }

    @Test
    @DisplayName("폰이 이동할 수 없는 방향으로 이동한 경우")
    void isPawnMovingFalse() {
        WhitePawnMoving whitePawnMoving = new WhitePawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a3"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(whitePawnMoving.isPawnMoving(board, Position.from("a3"), Position.from("c3"))).isFalse();
    }
}