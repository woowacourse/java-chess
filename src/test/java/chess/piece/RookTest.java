package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"ONE,C", "EIGHT,C", "FIVE,A", "FIVE,H"})
    @DisplayName("룩은 상하좌우로 직선 이동한다")
    void movePawn(Row row, Column column) {
        //given
        Position position = new Position(Row.FIVE, Column.C);
        Piece piece = new Rook(position, Color.BLACK);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //when
        Position destination = new Position(column, row);

        //then
        assertDoesNotThrow(() -> piece.move(board, destination));
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE,C", "EIGHT,C", "FIVE,A", "FIVE,H"})
    @DisplayName("룩은 경로에 기물이 존재하면 이동할 수 없다")
    void cannotMoveRook(Row row, Column column) {
        //given
        Position position = new Position(Row.FIVE, Column.C);
        Piece piece = new Rook(position, Color.BLACK);
        List<Piece> pieces = List.of(piece,
                new Rook(position.moveUp(), Color.BLACK),
                new Rook(position.moveDown(), Color.BLACK),
                new Rook(position.moveLeft(), Color.BLACK),
                new Rook(position.moveRight(), Color.BLACK)
        );
        Board board = new Board(pieces);

        //when
        Position destination = new Position(column, row);

        //then
        Assertions.assertThatThrownBy(() -> piece.move(board, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물이 존재합니다");
    }
}
