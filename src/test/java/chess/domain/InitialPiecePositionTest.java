package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialPiecePositionTest {

    @Test
    @DisplayName("전체 말들의 초기 위치 정보를 반환한다. - 기물들의 위치 확인")
    void initializeAllPieces() {
        Map<Position, Piece> initialPiecePositions = new InitialPiecePosition().getInitialPiecePositions();
        assertAll(
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 1), new Piece(PieceType.ROOK, Color.WHITE),
                                new Position(1, 2), new Piece(PieceType.KNIGHT, Color.WHITE),
                                new Position(1, 3), new Piece(PieceType.BISHOP, Color.WHITE),
                                new Position(1, 4), new Piece(PieceType.QUEEN, Color.WHITE),
                                new Position(1, 5), new Piece(PieceType.KING, Color.WHITE),
                                new Position(1, 6), new Piece(PieceType.BISHOP, Color.WHITE),
                                new Position(1, 7), new Piece(PieceType.KNIGHT, Color.WHITE),
                                new Position(1, 8), new Piece(PieceType.ROOK, Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(2, 1), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(2, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(2, 3), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(2, 4), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(2, 5), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(2, 6), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(2, 7), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(2, 8), new Piece(PieceType.PAWN, Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(7, 1), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(7, 2), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(7, 3), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(7, 4), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(7, 5), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(7, 6), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(7, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(7, 8), new Piece(PieceType.PAWN, Color.BLACK)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(8, 1), new Piece(PieceType.ROOK, Color.BLACK),
                                new Position(8, 2), new Piece(PieceType.KNIGHT, Color.BLACK),
                                new Position(8, 3), new Piece(PieceType.BISHOP, Color.BLACK),
                                new Position(8, 4), new Piece(PieceType.QUEEN, Color.BLACK),
                                new Position(8, 5), new Piece(PieceType.KING, Color.BLACK),
                                new Position(8, 6), new Piece(PieceType.BISHOP, Color.BLACK),
                                new Position(8, 7), new Piece(PieceType.KNIGHT, Color.BLACK),
                                new Position(8, 8), new Piece(PieceType.ROOK, Color.BLACK)
                        ))
        );
    }

    @Test
    @DisplayName("말의 위치가 비어있는 경우 비어있는 말의 타입을 반환한다.")
    void initializeEmptyPieces() {
        Map<Position, Piece> initialPiecePositions = new InitialPiecePosition().getInitialPiecePositions();
        IntStream.rangeClosed(3, 6).boxed()
                .flatMap(row -> IntStream.rangeClosed(1, 8).boxed()
                        .map(column -> new Position(row, column)))
                .forEach(position -> assertThat(initialPiecePositions.get(position)).isEqualTo(
                        new Piece(PieceType.EMPTY, Color.NONE)));
    }
}
