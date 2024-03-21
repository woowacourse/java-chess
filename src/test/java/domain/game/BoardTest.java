package domain.game;

import domain.game.Board;
import domain.game.BoardInitializer;
import domain.game.Piece;
import domain.game.PieceFactory;
import domain.game.PieceType;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
        Position source = new Position(File.B, Rank.TWO);
        Position destination = new Position(File.D, Rank.FOUR);
        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(source, piece));
        Board board = new Board(piecePositions);

        // When
        board.movePiece(WHITE, source, destination);

        // Then
        assertThat(piecePositions.containsKey(destination)).isTrue();
        assertThat(piecePositions.containsKey(source)).isFalse();
    }

    @DisplayName("기물의 이동 목적지에 다른 색의 기물이 있으면 이동시킬 수 있다.")
    @Test
    void movePieceToEnemySpaceTest() {
        // Given
        Piece piece = PieceFactory.create(PieceType.WHITE_KNIGHT);
        Piece enemy = PieceFactory.create(PieceType.BLACK_KING);
        Position source = new Position(File.B, Rank.TWO);
        Position destination = new Position(File.C, Rank.FOUR);
        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(
                source, piece,
                destination, enemy
        ));
        Board board = new Board(piecePositions);

        // When
        board.movePiece(WHITE, source, destination);

        // Then
        assertThat(piecePositions.containsKey(destination)).isTrue();
        assertThat(piecePositions.containsKey(source)).isFalse();
    }

    @DisplayName("기물의 이동 목적지에 같은 색의 기물이 있으면 이동시킬 수 없다.")
    @Test
    void notMovePieceTest() {
        // Given
        Piece piece = PieceFactory.create(PieceType.WHITE_KNIGHT);
        Piece other = PieceFactory.create(PieceType.WHITE_ROOK);
        Position source = new Position(File.B, Rank.TWO);
        Position destination = new Position(File.C, Rank.FOUR);
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
