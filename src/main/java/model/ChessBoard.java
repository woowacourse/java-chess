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

    private static final Camp STARTING_CAMP = Camp.WHITE;
    private static final Map<File, Function<Camp, Piece>> startingPosition = new EnumMap<>(File.class);

    static {
        startingPosition.put(File.A, Rook::new);
        startingPosition.put(File.B, Knight::new);
        startingPosition.put(File.C, Bishop::new);
        startingPosition.put(File.D, Queen::new);
        startingPosition.put(File.E, King::new);
        startingPosition.put(File.F, Bishop::new);
        startingPosition.put(File.G, Knight::new);
        startingPosition.put(File.H, Rook::new);
    }

    private final Map<Position, Piece> board;
    private Camp camp;

    private ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
        this.camp = STARTING_CAMP;
    }

    public static ChessBoard setupStartingPosition() {
        Map<Position, Piece> res = new HashMap<>();
        settingExceptPawn(res, Camp.BLACK, Rank.EIGHT);
        settingPawn(res, Camp.BLACK, Rank.SEVEN);
        settingPawn(res, Camp.WHITE, Rank.TWO);
        settingExceptPawn(res, Camp.WHITE, Rank.ONE);
        return new ChessBoard(res);
    }

    private static void settingExceptPawn(final Map<Position, Piece> board, final Camp camp, final Rank rank) {
        for (File file : File.values()) {
            board.put(new Position(file, rank), startingPosition.get(file).apply(camp));
        }
    }

    private static void settingPawn(final Map<Position, Piece> board, final Camp camp, final Rank rank) {
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
