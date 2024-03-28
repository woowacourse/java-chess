package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
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
                                Position.of(1, 1), new Piece(PieceType.ROOK, Color.WHITE),
                                Position.of(2, 1), new Piece(PieceType.KNIGHT, Color.WHITE),
                                Position.of(3, 1), new Piece(PieceType.BISHOP, Color.WHITE),
                                Position.of(4, 1), new Piece(PieceType.QUEEN, Color.WHITE),
                                Position.of(5, 1), new Piece(PieceType.KING, Color.WHITE),
                                Position.of(6, 1), new Piece(PieceType.BISHOP, Color.WHITE),
                                Position.of(7, 1), new Piece(PieceType.KNIGHT, Color.WHITE),
                                Position.of(8, 1), new Piece(PieceType.ROOK, Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                Position.of(1, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                Position.of(2, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                Position.of(3, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                Position.of(4, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                Position.of(5, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                Position.of(6, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                Position.of(7, 2), new Piece(PieceType.PAWN, Color.WHITE),
                                Position.of(8, 2), new Piece(PieceType.PAWN, Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                Position.of(1, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                Position.of(2, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                Position.of(3, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                Position.of(4, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                Position.of(5, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                Position.of(6, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                Position.of(7, 7), new Piece(PieceType.PAWN, Color.BLACK),
                                Position.of(8, 7), new Piece(PieceType.PAWN, Color.BLACK)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                Position.of(1, 8), new Piece(PieceType.ROOK, Color.BLACK),
                                Position.of(2, 8), new Piece(PieceType.KNIGHT, Color.BLACK),
                                Position.of(3, 8), new Piece(PieceType.BISHOP, Color.BLACK),
                                Position.of(4, 8), new Piece(PieceType.QUEEN, Color.BLACK),
                                Position.of(5, 8), new Piece(PieceType.KING, Color.BLACK),
                                Position.of(6, 8), new Piece(PieceType.BISHOP, Color.BLACK),
                                Position.of(7, 8), new Piece(PieceType.KNIGHT, Color.BLACK),
                                Position.of(8, 8), new Piece(PieceType.ROOK, Color.BLACK)
                        ))
        );
    }

    @Test
    @DisplayName("말의 위치가 비어있는 경우 비어있는 말의 타입을 반환한다.")
    void initializeEmptyPieces() {
        Map<Position, Piece> initialPiecePositions = new BoardInitializer().initialize();
        IntStream.rangeClosed(3, 6).boxed()
                .flatMap(rank -> IntStream.rangeClosed(1, 8).boxed()
                        .map(file -> Position.of(file, rank)))
                .forEach(position -> assertThat(initialPiecePositions.get(position)).isEqualTo(
                        new Piece(PieceType.EMPTY, Color.NONE)));
    }
}
