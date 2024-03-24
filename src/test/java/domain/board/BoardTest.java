package domain.board;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static domain.board.File.*;
import static domain.board.Rank.*;
import static domain.piece.PieceColor.BLACK;
import static domain.piece.PieceColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @DisplayName("기물을 이동시킨다.")
    @Test
    void movePieceTest() {
        // Given
        Position source = position(B, TWO);
        Position destination = position(B, FOUR);
        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(source, new Pawn(WHITE)));
        Board board = new Board(piecePositions);

        // When
        board.movePiece(WHITE, source, destination);
        boolean existsPieceOnSource = piecePositions.containsKey(source);
        boolean existsPieceOnDestination = piecePositions.containsKey(destination);

        // Then
        assertThat(existsPieceOnSource).isFalse();
        assertThat(existsPieceOnDestination).isTrue();
    }

    @DisplayName("목적지에 적 기물이 존재하면 적 기물을 제거하고 이동시킨다.")
    @Test
    void movePieceWithRemoveEnemyPieceTest() {
        // Given
        Position source = position(B, TWO);
        Position destination = position(B, FOUR);
        Rook myPiece = new Rook(WHITE);
        Pawn enemyPiece = new Pawn(BLACK);
        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(source, myPiece, destination, enemyPiece));
        Board board = new Board(piecePositions);

        // When
        board.movePiece(WHITE, source, destination);
        boolean existsPieceOnSource = piecePositions.containsKey(source);
        Piece destinationPiece = piecePositions.get(destination);

        // Then
        assertThat(existsPieceOnSource).isFalse();
        assertThat(destinationPiece).isNotEqualTo(enemyPiece);
        assertThat(destinationPiece).isEqualTo(myPiece);
    }

    @DisplayName("입력된 출발지와 목적지의 위치가 같으면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenSourceEqualsDestinationTest() {
        // Given
        Board board = BoardInitializer.initBoard();
        Position source = new Position(B, TWO);
        Position destination = new Position(B, TWO);

        // When & Then
        assertThatThrownBy(() -> board.movePiece(WHITE, source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발지와 목적지가 같을 수 없습니다.");
    }

    @DisplayName("기물이 존재하지 않는 출발지 위치가 입력되면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenNotExistPieceSourceTest() {
        // Given
        Board board = BoardInitializer.initBoard();
        Position source = new Position(D, FIVE);
        Position destination = new Position(B, TWO);

        // When & Then
        assertThatThrownBy(() -> board.movePiece(WHITE, source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발지에 기물이 존재하지 않습니다.");
    }

    @DisplayName("상대방의 기물을 이동시키려고 하면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenMoveEnemyPieceTest() {
        // Given
        Board board = BoardInitializer.initBoard();
        Position source = new Position(B, SEVEN);
        Position destination = new Position(B, SIX);

        // When & Then
        assertThatThrownBy(() -> board.movePiece(WHITE, source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 기물을 이동시킬 수 없습니다.");
    }

    private Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
