package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardInitializerTest {

    @Test
    @DisplayName("전체 말들의 초기 위치 정보를 반환한다. - 기물들의 위치 확인")
    void initializeAllPieces() {
        Map<Position, Piece> initialPiecePositions = new BoardInitializer().initialize();
        assertAll(
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 1), new Piece(PieceType.ROOK, Color.WHITE),
                                new Position(2, 1), new Piece(PieceType.KNIGHT, Color.WHITE),
                                new Position(3, 1), new Piece(PieceType.BISHOP, Color.WHITE),
                                new Position(4, 1), new Piece(PieceType.QUEEN, Color.WHITE),
                                new Position(5, 1), new Piece(PieceType.KING, Color.WHITE),
                                new Position(6, 1), new Piece(PieceType.BISHOP, Color.WHITE),
                                new Position(7, 1), new Piece(PieceType.KNIGHT, Color.WHITE),
                                new Position(8, 1), new Piece(PieceType.ROOK, Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(2, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(3, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(4, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(5, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(6, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(7, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                new Position(8, 2), new Piece(PieceType.PAWN, Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(2, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(3, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(4, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(5, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(6, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(7, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                new Position(8, 7), new Piece(PieceType.PAWN, Color.BLACK)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 8), new Piece(PieceType.ROOK, Color.BLACK),
                                new Position(2, 8), new Piece(PieceType.KNIGHT, Color.BLACK),
                                new Position(3, 8), new Piece(PieceType.BISHOP, Color.BLACK),
                                new Position(4, 8), new Piece(PieceType.QUEEN, Color.BLACK),
                                new Position(5, 8), new Piece(PieceType.KING, Color.BLACK),
                                new Position(6, 8), new Piece(PieceType.BISHOP, Color.BLACK),
                                new Position(7, 8), new Piece(PieceType.KNIGHT, Color.BLACK),
                                new Position(8, 8), new Piece(PieceType.ROOK, Color.BLACK)
                        ))
        );
    }

    @Test
    @DisplayName("말의 위치가 비어있는 경우 비어있는 말의 타입을 반환한다.")
    void initializeEmptyPieces() {
        Map<Position, Piece> initialPiecePositions = new BoardInitializer().initialize();
        IntStream.rangeClosed(3, 6).boxed()
                .flatMap(row -> IntStream.rangeClosed(1, 8).boxed()
                        .map(column -> new Position(column, row)))
                .forEach(position -> assertThat(initialPiecePositions.get(position)).isEqualTo(
                        new Piece(PieceType.EMPTY, Color.NONE)));
    }
}
