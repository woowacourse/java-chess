package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"EIGHT,A", "EIGHT,G", "TWO,A", "TWO,G"})
    @DisplayName("비숍은 대각선으로 이동한다")
    void moveBishop(Row row, Column column) {
        //given
        Position position = new Position(Row.FIVE, Column.D);
        Piece piece = new Bishop(position, Color.BLACK);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //when
        Position destination = new Position(column, row);

        //then
        assertDoesNotThrow(() -> piece.move(board, destination));
    }

    @ParameterizedTest
    @CsvSource(value = {"EIGHT,A", "EIGHT,G", "TWO,A", "TWO,G"})
    @DisplayName("경로에 기물이 존재하면 이동할 수 없다")
    void cannotMoveRook(Row row, Column column) {
        //given
        Position position = new Position(Row.FIVE, Column.D);
        Piece piece = new Bishop(position, Color.BLACK);
        List<Piece> pieces = List.of(piece,
                new Rook(position.moveLeftUp(), Color.BLACK),
                new Rook(position.moveRightUp(), Color.BLACK),
                new Rook(position.moveLeftDown(), Color.BLACK),
                new Rook(position.moveRightDown(), Color.BLACK)
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
