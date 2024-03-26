package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("기본 체스 보드를 반환한다.")
    void Given_BoardCreator_When_Create_Then_BasicBoardCreated() {
        //given, when
        Board board = BoardFactory.create();
        Map<Position, Piece> initialPiecePositions = board.getBoard();
        //then
        assertAll(
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 1), new Rook(Color.WHITE),
                                new Position(2, 1), new Knight(Color.WHITE),
                                new Position(3, 1), new Bishop(Color.WHITE),
                                new Position(4, 1), new Queen(Color.WHITE),
                                new Position(5, 1), new King(Color.WHITE),
                                new Position(6, 1), new Bishop(Color.WHITE),
                                new Position(7, 1), new Knight(Color.WHITE),
                                new Position(8, 1), new Rook(Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 2), new Pawn(Color.WHITE),
                                new Position(2, 2), new Pawn(Color.WHITE),
                                new Position(3, 2), new Pawn(Color.WHITE),
                                new Position(4, 2), new Pawn(Color.WHITE),
                                new Position(5, 2), new Pawn(Color.WHITE),
                                new Position(6, 2), new Pawn(Color.WHITE),
                                new Position(7, 2), new Pawn(Color.WHITE),
                                new Position(8, 2), new Pawn(Color.WHITE)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 7), new Pawn(Color.BLACK),
                                new Position(2, 7), new Pawn(Color.BLACK),
                                new Position(3, 7), new Pawn(Color.BLACK),
                                new Position(4, 7), new Pawn(Color.BLACK),
                                new Position(5, 7), new Pawn(Color.BLACK),
                                new Position(6, 7), new Pawn(Color.BLACK),
                                new Position(7, 7), new Pawn(Color.BLACK),
                                new Position(8, 7), new Pawn(Color.BLACK)
                        )),
                () -> assertThat(initialPiecePositions)
                        .containsAllEntriesOf(Map.of(
                                new Position(1, 8), new Rook(Color.BLACK),
                                new Position(2, 8), new Knight(Color.BLACK),
                                new Position(3, 8), new Bishop(Color.BLACK),
                                new Position(4, 8), new Queen(Color.BLACK),
                                new Position(5, 8), new King(Color.BLACK),
                                new Position(6, 8), new Bishop(Color.BLACK),
                                new Position(7, 8), new Knight(Color.BLACK),
                                new Position(8, 8), new Rook(Color.BLACK)
                        ))
        );
    }

    @Test
    @DisplayName("말의 위치가 비어있는 경우 비어있는 말의 타입을 반환한다.")
    void Given_BoardCreator_When_GetPieceFromEmptyPosition_Then_ReturnEmptyPiece() {
        //given
        Board board = BoardFactory.create();
        // when, then
        Map<Position, Piece> initialPiecePositions = board.getBoard();
        IntStream.rangeClosed(3, 6).boxed()
                .flatMap(rank -> IntStream.rangeClosed(1, 8).boxed().map(file -> new Position(file, rank)))
                .forEach(position -> assertThat(initialPiecePositions.get(position)).isEqualTo(new Empty()));
    }
}
