package chess.piece;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"SIX,C,BLACK", "FOUR,C,WHITE"})
    @DisplayName("처음이 아닌 움직임에서는 폰은 본진 방향을 제외한 상하 1칸 이동한다")
    void movePawn(Row row, Column column, Color color) {
        //given
        Position position = new Position(Row.FIVE, Column.C);
        Piece piece = new Pawn(position, color, false);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //when
        Position destination = new Position(column, row);

        //then
        assertDoesNotThrow(() -> piece.move(board, destination));
    }

    @ParameterizedTest
    @CsvSource(value = {"SIX,C,BLACK", "FOUR,C,WHITE"})
    @DisplayName("처음 움직임에서는 폰은 본진 방향을 제외한 상하 1-2칸 이동한다")
    void movePawnNotFirst(Row row, Column column, Color color) {
        //given
        Position position = new Position(Row.FIVE, Column.C);
        Piece piece = new Pawn(position, color, false);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //when
        Position destination = new Position(column, row);

        //then
        assertDoesNotThrow(() -> piece.move(board, destination));
    }

    private static Stream<Arguments> providePawn() {
        return Stream.of(
                Arguments.of(
                        new Pawn(new Position(Row.FIVE, Column.B), Color.WHITE, true),
                        new Pawn(new Position(Row.SIX, Column.C), Color.BLACK, true),
                        new Position(Row.SIX, Column.C)
                ),
                Arguments.of(
                        new Pawn(new Position(Row.FIVE, Column.B), Color.WHITE, true),
                        new Pawn(new Position(Row.SIX, Column.A), Color.BLACK, true),
                        new Position(Row.SIX, Column.A)
                ),
                Arguments.of(
                        new Pawn(new Position(Row.FIVE, Column.B), Color.BLACK, true),
                        new Pawn(new Position(Row.FOUR, Column.C), Color.WHITE, true),
                        new Position(Row.FOUR, Column.C)
                ),
                Arguments.of(
                        new Pawn(new Position(Row.FIVE, Column.B), Color.BLACK, true),
                        new Pawn(new Position(Row.FOUR, Column.A), Color.WHITE, true),
                        new Position(Row.FOUR, Column.A)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("providePawn")
    @DisplayName("폰은 적이 있으면 대각선 이동이 가능하다")
    void movePawnNotFirst(Pawn pawn, Pawn enemy, Position move) {
        //given
        List<Piece> pieces = List.of(pawn, enemy);
        Board board = new Board(pieces);

        //when
        //then
        assertDoesNotThrow(() -> pawn.move(board, move));
    }
}
