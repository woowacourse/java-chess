package model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import model.piece.Bishop;
import model.piece.King;
import model.piece.Knight;
import model.piece.Pawn;
import model.piece.Piece;
import model.piece.Queen;
import model.piece.Rook;
import model.position.Column;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

public class GameBoard {

    private static final Map<Column, Function<Camp, Piece>> initPosition = new EnumMap<>(Column.class);

    static {
        initPosition.put(Column.A, Rook::new);
        initPosition.put(Column.B, Knight::new);
        initPosition.put(Column.C, Bishop::new);
        initPosition.put(Column.D, Queen::new);
        initPosition.put(Column.E, King::new);
        initPosition.put(Column.F, Bishop::new);
        initPosition.put(Column.G, Knight::new);
        initPosition.put(Column.H, Rook::new);
    }

    private final Map<Position, Piece> board;


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
            board.put(new Position(column, row), initPosition.get(column).apply(camp));
        }
    }

    private void settingPawn(final Camp camp, final Row row) {
        for (Column column : Column.values()) {
            board.put(new Position(column, row), new Pawn(camp));
        }
    }

    public void move(Moving moving, Camp camp) {
        if (!board.containsKey(moving.currentPosition())) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }

        Piece piece = board.get(moving.currentPosition());
        if (!piece.isSameCamp(camp)) {
            throw new IllegalArgumentException("자신의 기물만 이동 가능합니다.");
        }

        Position nextPosition = moving.nextPosition();

        Set<Position> positions;

        if (board.containsKey(nextPosition)) {
            positions = piece.getAttackRoute(moving);
        } else {
            positions = piece.getRoute(moving);
        }
        for (Position position : positions) {
            if (board.containsKey(position)) {
                throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
            }
        }

        if (board.containsKey(nextPosition) && piece.getCamp().equals(board.get(nextPosition).getCamp())) {
            throw new IllegalArgumentException("도착 지점에 같은 진영의 기물이 있습니다.");
        }

        board.put(moving.nextPosition(), piece);
        board.remove(moving.currentPosition());
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
