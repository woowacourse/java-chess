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
import model.position.File;
import model.position.Moving;
import model.position.Position;
import model.position.Rank;

public class ChessBoard {

    // TODO 처음에 각 기물들을 배치해두는 것이 좋을까 아니면 빈 Map 두고 메서드 실행하면 추가하는게 좋을지 고민하기
    private static final Camp START_CAMP = Camp.WHITE;
    private static final Map<File, Function<Camp, Piece>> initPosition = new EnumMap<>(File.class);

    static {
        initPosition.put(File.A, Rook::new);
        initPosition.put(File.B, Knight::new);
        initPosition.put(File.C, Bishop::new);
        initPosition.put(File.D, Queen::new);
        initPosition.put(File.E, King::new);
        initPosition.put(File.F, Bishop::new);
        initPosition.put(File.G, Knight::new);
        initPosition.put(File.H, Rook::new);
    }

    private final Map<Position, Piece> board;
    private Camp camp;


    public ChessBoard() {
        this.board = new HashMap<>();
        this.camp = START_CAMP;
    }

    public void setting() {
        settingExceptPawn(Camp.BLACK, Rank.EIGHT);
        settingPawn(Camp.BLACK, Rank.SEVEN);
        settingPawn(Camp.WHITE, Rank.TWO);
        settingExceptPawn(Camp.WHITE, Rank.ONE);
    }

    private void settingExceptPawn(final Camp camp, final Rank rank) {
        for (File file : File.values()) {
            board.put(new Position(file, rank), initPosition.get(file).apply(camp));
        }
    }

    private void settingPawn(final Camp camp, final Rank rank) {
        for (File file : File.values()) {
            board.put(new Position(file, rank), new Pawn(camp));
        }
    }

    public void move(final Moving moving) {
        validate(camp, moving);

        final Piece piece = board.get(moving.getCurrentPosition());
        board.put(moving.getNextPosition(), piece);
        board.remove(moving.getCurrentPosition());
        camp = camp.toggle();
    }

    private void validate(final Camp camp, final Moving moving) {
        final Position currentPosition = moving.getCurrentPosition();
        if (!board.containsKey(currentPosition)) {
            throw new PieceDoesNotExistException(ErrorCode.PIECE_DOES_NOT_EXIST_POSITION);
        }

        final Piece piece = board.get(currentPosition);
        if (!piece.isSameCamp(camp)) {
            throw new InvalidTurnException(ErrorCode.INVALID_CAMP_PIECE);
        }

        final Set<Position> positions = getRoute(moving, piece);

        for (Position position : positions) {
            if (board.containsKey(position)) {
                throw new PieceExistInRouteException(ErrorCode.PIECE_EXIST_IN_ROUTE);
            }
        }

        final Piece target = board.get(moving.getNextPosition());
        if (target != null && target.isSameCamp(piece.getCamp())) {
            throw new PieceExistInRouteException(ErrorCode.PIECE_EXIST_IN_ROUTE);
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
