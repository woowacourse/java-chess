package model;

import model.piece.*;
import model.position.Column;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class GameBoard {

    private static final Map<Column, Function<Camp, Piece>> initPosition = new EnumMap<>(Column.class);

    private Camp camp;

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
        this.camp = Camp.WHITE;
        setting();
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

    public void move(Moving moving) {
        validate(camp, moving);

        Piece piece = board.get(moving.getCurrentPosition());
        board.put(moving.getNextPosition(), piece);
        board.remove(moving.getCurrentPosition());
        camp = camp.toggle();
    }

    private void validate(Camp camp, Moving moving) {
        Position currentPosition = moving.getCurrentPosition();
        if (!board.containsKey(currentPosition)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }

        Piece piece = board.get(currentPosition);
        if (!piece.isSameCamp(camp)) {
            throw new IllegalArgumentException("자신의 기물만 이동 가능합니다.");
        }

        Set<Position> positions = getRoute(moving, piece);

        for (Position position : positions) {
            if (board.containsKey(position)) {
                throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
            }
        }

        Piece target = board.get(moving.getNextPosition());
        if (target != null && target.isSameCamp(piece.getCamp())) {
            throw new IllegalArgumentException("도착 지점에 같은 진영의 기물이 있습니다.");
        }
    }

    private Set<Position> getRoute(final Moving moving, final Piece piece) {
        if (board.containsKey(moving.getNextPosition())) {
            return piece.getAttackRoute(moving);
        }
        return piece.getMoveRoute(moving);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Camp getCamp() {
        return camp;
    }
}
