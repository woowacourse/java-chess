package domain.game;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static domain.Fixture.Positions.*;
import static domain.game.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    @DisplayName("기물의 시작 위치를 배치한 Board 인스턴스를 생성한다.")
    @Test
    void createBoard() {
        // When
        Board board = BoardInitializer.init();

        // Then
        assertThat(board).isNotNull();
    }

    @DisplayName("기물의 이동 목적지가 비어있으면 이동시킬 수 있다.")
    @Test
    void movePieceToEmptySpaceTest() {
        // Given
        Piece piece = PieceFactory.create(PieceType.WHITE_BISHOP);
        Position source = B2;
        Position destination = Position.of(File.D, Rank.FOUR);
        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(source, piece));
        Board board = new Board(piecePositions);

        // When
        board.movePiece(WHITE, source, destination);

        // Then
        assertThat(piecePositions).doesNotContainKey(source).containsKey(destination);
    }

    @DisplayName("기물의 이동 목적지에 다른 색의 기물이 있으면 이동시킬 수 있다.")
    @Test
    void movePieceToEnemySpaceTest() {
        // Given
        Piece piece = PieceFactory.create(PieceType.WHITE_KNIGHT);
        Piece enemy = PieceFactory.create(PieceType.BLACK_KING);
        Position source = B2;
        Position destination = C4;
        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(
                source, piece,
                destination, enemy
        ));
        Board board = new Board(piecePositions);

        // When
        board.movePiece(WHITE, source, destination);

        // Then
        assertThat(piecePositions).doesNotContainKey(source).containsKey(destination);
    }

    @DisplayName("기물의 이동 목적지에 같은 색의 기물이 있으면 이동시킬 수 없다.")
    @Test
    void notMovePieceTest() {
        // Given
        Piece piece = PieceFactory.create(PieceType.WHITE_KNIGHT);
        Piece other = PieceFactory.create(PieceType.WHITE_ROOK);
        Position source = B2;
        Position destination = C4;
        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(
                source, piece,
                destination, other
        ));
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> board.movePiece(WHITE, source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 위치에 아군 기물이 존재합니다.");
    }
}
