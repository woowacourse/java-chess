package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"SEVEN,B", "SEVEN,D", "THREE,B", "THREE,D", "SIX,A", "FOUR,A", "SIX,E", "FOUR,E"})
    @DisplayName("나이트는 곡선 모양 형태로 1회 이동한다")
    void movePawn(Row row, Column column) {
        //given
        Position position = new Position(Row.FIVE, Column.C);
        Piece piece = new Knight(position, Color.BLACK);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //when
        Position destination = new Position(column, row);

        //then
        assertDoesNotThrow(() -> piece.move(board, destination));
    }
}
