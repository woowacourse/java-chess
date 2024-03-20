package model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import point.Column;
import point.Position;
import point.Row;

public class GameBoard {

    private static final Map<Column, Function<Camp, Piece>> initPosition = new EnumMap<>(Column.class);

    static {
        initPosition.put(Column.FIRST, Rook::new);
        initPosition.put(Column.SECOND, Knight::new);
        initPosition.put(Column.THIRD, Bishop::new);
        initPosition.put(Column.FOURTH, Queen::new);
        initPosition.put(Column.FIFTH, King::new);
        initPosition.put(Column.SIXTH, Bishop::new);
        initPosition.put(Column.SEVENTH, Knight::new);
        initPosition.put(Column.EIGHTH, Rook::new);
    }

    private List<List<Square>> board2;
    private Map<Position, Piece> board;


    public GameBoard() {
        this.board = new HashMap<>();
    }

    public void setting() {

        settingExceptPawn(Camp.BLACK, Row.EIGHTH);
        settingPawn(Camp.BLACK, Row.SEVENTH);
        settingPawn(Camp.WHITE, Row.SECOND);
        settingExceptPawn(Camp.WHITE, Row.FIRST);
    }

    private void settingExceptPawn(final Camp camp, Row row) {
        for (Column column : Column.values()) {
            board.put(new Position(row, column), initPosition.get(column).apply(camp));
        }
    }
    private void settingPawn(final Camp camp, final Row row) {
        for (Column column : Column.values()) {
            board.put(new Position(row, column), new Pawn(camp));
        }
    }

    public Square findByPosition(Position position) {
        int rowIndex = position.getRow().getIndex();
        int colIndex = position.getColumn().getIndex();
        return board2.get(rowIndex).get(colIndex);
    }

    public List<List<Square>> getBoard2() {
        return board2;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
