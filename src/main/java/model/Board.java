package model;

import constant.ErrorCode;
import exception.InvalidTurnException;
import exception.PieceDoesNotExistException;
import exception.PieceExistInRouteException;
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

public class Board {

    private static final Map<File, Function<Camp, Piece>> startingPosition = Map.of(
            File.A, Rook::new,
            File.B, Knight::new,
            File.C, Bishop::new,
            File.D, Queen::new,
            File.E, King::new,
            File.F, Bishop::new,
            File.G, Knight::new,
            File.H, Rook::new
    );

    private final Map<Position, Piece> pieces;

    private Board(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board create() {
        Map<Position, Piece> result = new HashMap<>();
        settingExceptPawn(result, Camp.BLACK, Rank.EIGHT);
        settingPawn(result, Camp.BLACK, Rank.SEVEN);
        settingPawn(result, Camp.WHITE, Rank.TWO);
        settingExceptPawn(result, Camp.WHITE, Rank.ONE);
        return new Board(result);
    }

    private static void settingExceptPawn(final Map<Position, Piece> board, final Camp camp, final Rank rank) {
        for (File file : File.values()) {
            final Piece piece = startingPosition.get(file).apply(camp);
            board.put(new Position(file, rank), piece);
        }
    }

    private static void settingPawn(final Map<Position, Piece> board, final Camp camp, final Rank rank) {
        for (File file : File.values()) {
            final Piece piece = Pawn.create(camp);
            board.put(new Position(file, rank), piece);
        }
    }

    public void validate(final Moving moving, final Camp currentCamp) {
        final Position currentPosition = moving.getCurrentPosition();
        validateExistPiece(currentPosition);
        final Piece piece = pieces.get(currentPosition);
        validateOwnPiece(currentCamp, piece);
        final Set<Position> route = getRoute(moving, piece);
        validateUnBlocked(route);
        final Position nextPosition = moving.getNextPosition();
        validateTargetEnemy(currentCamp, nextPosition);
    }

    private void validateExistPiece(final Position currentPosition) {
        if (!pieces.containsKey(currentPosition)) {
            throw new PieceDoesNotExistException(ErrorCode.PIECE_DOES_NOT_EXIST_POSITION);
        }
    }

    private void validateOwnPiece(final Camp currentCamp, final Piece piece) {
        if (!piece.isSameCamp(currentCamp)) {
            throw new InvalidTurnException(ErrorCode.INVALID_CAMP_PIECE);
        }
    }

    private void validateUnBlocked(final Set<Position> route) {
        boolean blocked = route.stream()
                .anyMatch(pieces::containsKey);
        if (blocked) {
            throw new PieceExistInRouteException(ErrorCode.PIECE_EXIST_IN_ROUTE);
        }
    }

    private void validateTargetEnemy(final Camp currentCamp, final Position nextPosition) {
        if (pieces.containsKey(nextPosition) && pieces.get(nextPosition).isSameCamp(currentCamp)) {
            throw new PieceExistInRouteException(ErrorCode.OWN_PIECE_EXIST_POSITION);
        }
    }

    private Set<Position> getRoute(final Moving moving, final Piece piece) {
        if (pieces.containsKey(moving.getNextPosition())) {
            return piece.getAttackRoute(moving);
        }
        return piece.getMoveRoute(moving);
    }

    public void move(final Moving moving) {
        final Piece piece = pieces.get(moving.getCurrentPosition());
        pieces.put(moving.getNextPosition(), piece);
        pieces.remove(moving.getCurrentPosition());
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
