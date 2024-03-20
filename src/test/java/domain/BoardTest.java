package domain;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import domain.strategy.PawnMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static domain.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @DisplayName("기물의 시작 위치를 배치한 Board 인스턴스를 생성한다.")
    @Test
    void createBoard() {
        // When
        Board board = BoardInitializer.init();

        // Then
        assertThat(board).isNotNull();
    }

//    @DisplayName("기물을 이동시킨다.")
//    @Test
//    void movePieceTest() {
//        // Given
//        Piece piece = new Piece(PieceType.WHITE_BISHOP, new ContinuousMoveStrategy(diagonalVectors(), 8));
//        Position source = new Position(File.B, Rank.TWO);
//        Position destination = new Position(File.D, Rank.FOUR);
//        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(source, piece));
//        Board board = new Board(piecePositions);
//
//        // When
//        board.movePiece(WHITE, source, destination);
//
//        // Then
//        assertThat(piecePositions.containsKey(destination)).isTrue();
//        assertThat(piecePositions.containsKey(source)).isFalse();
//    }

//    @DisplayName("기물을 이동시킨다.")
//    @Test
//    void movePieceTest() {
//        // Given
//        Piece piece = new Piece(PieceType.WHITE_KNIGHT, new KnightMoveStrategy());
//        Position source = new Position(File.B, Rank.TWO);
//        Position destination = new Position(File.C, Rank.FOUR);
//        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(source, piece));
//        Board board = new Board(piecePositions);
//
//        // When
//        board.movePiece(WHITE, source, destination);
//
//        // Then
//        assertThat(piecePositions.containsKey(destination)).isTrue();
//        assertThat(piecePositions.containsKey(source)).isFalse();
//    }

    @DisplayName("기물을 이동시킨다.")
    @Test
    void movePieceTest() {
        // Given
        Piece piece = new Piece(PieceType.WHITE_PAWN, new PawnMoveStrategy(WHITE));
        Position source = new Position(File.C, Rank.TWO);
        Position destination = new Position(File.D, Rank.THREE);


        Map<Position, Piece> piecePositions = new HashMap<>(Map.of(source, piece, destination, new Piece(PieceType.BLACK_KING, null)));
        Board board = new Board(piecePositions);

        // When
        board.movePiece(WHITE, source, destination);

        // Then
        assertThat(piecePositions.containsKey(destination)).isTrue();
        assertThat(piecePositions.containsKey(source)).isFalse();
    }
}
