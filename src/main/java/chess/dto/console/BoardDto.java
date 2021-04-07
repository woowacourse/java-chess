package chess.dto.console;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Point;
import chess.domain.board.Row;
import chess.domain.board.SquareState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {

    private final List<List<String>> boardDto;

    public BoardDto(Board board) {
        this.boardDto = new ArrayList<>();
        Map<Point, SquareState> squares = board.squares();

        for (Row row : Row.reversedRows()) {
            boardDto.add(rowDto(row, squares));
        }
    }

    private List<String> rowDto(Row row, Map<Point, SquareState> squares) {
        List<String> rowResult = new ArrayList<>();
        for (Column column : Column.columns()) {
            SquareState squareState = squares.get(Point.of(column.coordinate() + row.coordinate()));
            rowResult.add(squareState.pieceName());
        }
        return rowResult;
    }

    public List<List<String>> board() {
        return boardDto;
    }
}
