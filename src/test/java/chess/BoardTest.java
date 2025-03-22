package chess;

import chess.piece.Pawn;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("보드의 크기는 8 * 8 크기이다")
    void boardSize() {
        //given
        //when
        Board board = new Board(new ArrayList<>());

        //then
        Map<Position, Piece> positionPieceMap = board.getPositionToPiece();
        assertThat(positionPieceMap.keySet()).hasSize(8 * 8);
    }

    @Test
    @DisplayName("보드를 주어진 기물을 기반으로 배치한다")
    void initBoard() {
        //given
        //when
        Position position = new Position(Column.A, Row.ONE);
        Piece piece = new Pawn(position, Color.BLACK, true);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //then
        Map<Position, Piece> positionPieceMap = board.getPositionToPiece();
        assertThat(positionPieceMap.get(position)).isEqualTo(piece);
    }

    @ParameterizedTest
    @CsvSource(value = {"A,ONE,true", "A,TWO,false"})
    @DisplayName("보드에서 특정 위치에 기물이 존재하는지 확인한다")
    void isExist(Column column, Row row, boolean expected) {
        //given
        Position position = new Position(Column.A, Row.ONE);
        Piece piece = new Pawn(position, Color.BLACK, true);
        List<Piece> pieces = List.of(piece);
        Board board = new Board(pieces);

        //when
        Position target = new Position(column, row);
        boolean actual = board.isExist(target);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
