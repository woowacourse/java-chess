package model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import point.Column;
import point.Moving;
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

    public Piece findPieceByPosition(Position position) {
        return board.get(position);
    }

    public void move(Moving moving) {
        if (!board.containsKey(moving.getCurrentPosition())) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
        Piece piece = board.get(moving.getCurrentPosition());
        Set<Position> positions = piece.getRoute(moving.getCurrentPosition(), moving.getNextPosition());
        for (Position position : positions) {
            if (board.containsKey(position)) {
                throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
            }
        }

        if (piece.getCamp().equals(board.get(moving.getNextPosition()).getCamp())) {
            throw new IllegalArgumentException("도착 지점에 같은 진영의 기물이 있습니다.");
        }
    }

    public List<List<Square>> getBoard2() {
        return board2;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
