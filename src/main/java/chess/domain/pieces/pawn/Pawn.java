package chess.domain.pieces.pawn;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.Piece;
import java.util.List;

public abstract class Pawn extends Piece {

    private static final int MIN_MOVABLE_DISTANCE = 0;
    private static final int MAX_MOVABLE_DISTANCE = 2;
    protected static final int DEFAULT_MOVABLE_DISTANCE = 1;
    protected static final int OTHER_PIECE_INDEX = 0;
    static final String INVALID_TEAM = "해당 기물은 중립일 수 없습니다.";
    static final String INVALID_DEFAULT_DISTANCE = "첫 이동이 아닌 폰은 1칸만 이동할 수 있습니다.";
    static final String INVALID_FIRST_DISTANCE = "처음 이동하는 폰은 2칸 이내로 이동할 수 있습니다.";
    static final String INVALID_MOVE_DIAGONAL = "대각선은 적이 있을 때만 이동할 수 있습니다.";
    static final String INVALID_MOVE_STRAIGHT = "다른 기물이 있을 때는 수직으로 이동할 수 없습니다.";

    private boolean isMoved = false;

    public Pawn(final Team team, final List<Direction> directions) {
        super(team, List.copyOf(directions));
    }

    @Override
    protected void validateTeam(final Team team) {
        if (team.isNeutrality()) {
            throw new IllegalArgumentException(INVALID_TEAM);
        }
    }

    @Override
    public void validateMove(final Direction correctDirection, final List<Piece> onRoutePieces) {
        validateDirection(correctDirection);
        validateDistance(onRoutePieces);
        validateOnRoutePiecesExistAlly(onRoutePieces);
        validateKill(correctDirection, onRoutePieces);

        checkMoved();
    }

    abstract protected void validateKill(final Direction movableDirection, final List<Piece> onRoutePieces);

    protected void checkMoved() {
        this.isMoved = true;
    }

    protected void validateDistance(final List<Piece> otherPieces) {
        if (isValidDefaultMove(otherPieces)) {
            throw new IllegalArgumentException(INVALID_DEFAULT_DISTANCE);
        }
        if (isValidFirstMove(otherPieces)) {
            throw new IllegalArgumentException(INVALID_FIRST_DISTANCE);
        }
    }

    private boolean isValidDefaultMove(final List<Piece> otherPieces) {
        return isMoved && otherPieces.size() != DEFAULT_MOVABLE_DISTANCE;
    }

    private boolean isValidFirstMove(final List<Piece> otherPieces) {
        return !isMoved && (otherPieces.size() == MIN_MOVABLE_DISTANCE || otherPieces.size() > MAX_MOVABLE_DISTANCE);
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
