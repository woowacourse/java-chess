package model;

import constant.ErrorCode;
import exception.InvalidTurnException;
import exception.PieceDoesNotExistException;
import exception.PieceExistInRouteException;
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

    // TODO 처음에 각 기물들을 배치해두는 것이 좋을까 아니면 빈 Map 두고 메서드 실행하면 추가하는게 좋을지 고민하기
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
        settingExceptPawn(Camp.BLACK, Row.EIGHT);
        settingPawn(Camp.BLACK, Row.SEVEN);
        settingPawn(Camp.WHITE, Row.TWO);
        settingExceptPawn(Camp.WHITE, Row.ONE);
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
        validate(camp, moving);

        //TODO 여기 테스트 보충
        Piece piece = board.get(moving.getCurrentPosition());
        board.put(moving.getNextPosition(), piece);
        board.remove(moving.getCurrentPosition());
    }

    private void validate(Camp camp, Moving moving) {
        Position currentPosition = moving.getCurrentPosition();
        if (!board.containsKey(currentPosition)) {
            throw new PieceDoesNotExistException(ErrorCode.PIECE_DOES_NOT_EXIST_POSITION);  //TODO ("해당 위치에 기물이 없습니다.");
        }

        Piece piece = board.get(currentPosition);
        if (!piece.isSameCamp(camp)) {
            throw new InvalidTurnException(ErrorCode.INVALID_CAMP_PIECE); // TODO ("자신의 기물만 이동 가능합니다.");
        }

        Set<Position> positions = getRoute(moving, piece);

        for (Position position : positions) {
            if (board.containsKey(position)) {
                throw new PieceExistInRouteException(ErrorCode.PIECE_EXIST_IN_ROUTE); // TODO ("이동 경로에 다른 기물이 있습니다.");
            }
        }

        // TODO 테스트 보충
        Piece target = board.get(moving.getNextPosition());
        if (target != null && target.isSameCamp(piece.getCamp())) {
            throw new PieceExistInRouteException(ErrorCode.PIECE_EXIST_IN_ROUTE); // TODO 도착 지점에 같은 진영의 기물이 있습니다.
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
}
