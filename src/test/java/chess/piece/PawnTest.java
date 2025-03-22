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

public class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"SIX,C", "FOUR,C"})
    @DisplayName("처음이 아닌 움직임에서는 폰은 본진 방향을 제외한 상하 1칸 이동한다")
    void movePawn(Row row, Column column) {
        //given
        Position position = new Position(Row.FIVE, Column.C);
        Piece piece = new Pawn(position, Color.BLACK, false);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //when
        Position destination = new Position(column, row);

        //then
        assertDoesNotThrow(() -> piece.move(board, destination));
    }

    @ParameterizedTest
    @CsvSource(value = {"SIX,C", "FOUR,C, "})
    @DisplayName("처음 움직임에서는 폰은 본진 방향을 제외한 상하 1-2칸 이동한다")
    void movePawnNotFirst(Row row, Column column) {
        //given
        Position position = new Position(Row.FIVE, Column.C);
        Piece piece = new Pawn(position, Color.BLACK, false);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //when
        Position destination = new Position(column, row);

        //then
        assertDoesNotThrow(() -> piece.move(board, destination));
    }
}
