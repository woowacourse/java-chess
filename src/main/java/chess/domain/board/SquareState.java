package chess.domain.board;

import chess.domain.piece.MoveVector;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SquareState {

    private static final Map<Piece, Map<Team, SquareState>> SQUARE_STATE_POOL;

    static {
        SQUARE_STATE_POOL = Arrays.stream(Piece.values())
            .collect(Collectors.toMap(piece -> piece, SquareState::squareStateMapByPiece));
    }

    private final Piece piece;
    private final Team team;

    private SquareState(Piece piece, Team team) {
        this.piece = piece;
        this.team = team;
    }

    private static Map<Team, SquareState> squareStateMapByPiece(Piece piece) {
        return Arrays.stream(Team.values())
            .collect(Collectors.toMap(team -> team, team -> new SquareState(piece, team)));
    }

    public static SquareState of(Piece piece, Team team) {
        return SQUARE_STATE_POOL.get(piece).get(team);
    }

    public MoveVector movableVector(Point source, Point destination) {
        if (team.isBlack()) {
            return piece
                .movableVector(source.originSymmetricPoint(), destination.originSymmetricPoint())
                .oppositeVector();
        }
        return piece.movableVector(source, destination);
    }

    public boolean hasMovableVector(Point source, Point destination) {
        if (team.isBlack()) {
            return piece.hasMovableVector(source.originSymmetricPoint(),
                destination.originSymmetricPoint());
        }
        return piece.hasMovableVector(source, destination);
    }

    public int movementRange() {
        return piece.movementRange();
    }

    public String pieceName() {
        String pieceName = piece.pieceName();
        if (team == Team.BLACK) {
            pieceName = pieceName.toUpperCase();
        }
        return pieceName;
    }

    public double score() {
        return piece.score();
    }

    public Team team() {
        return team;
    }

    public Piece piece() {
        return piece;
    }

    public boolean isOnTeam(Team team) {
        return this.team == team;
    }

    public boolean isEnemyOf(SquareState sourceSquareState) {
        return team == sourceSquareState.team.opposingTeam();
    }

    public boolean isPieceTypeOf(Piece piece) {
        return this.piece == piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SquareState squareState = (SquareState) o;
        return Objects.equals(piece, squareState.piece) && team == squareState.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, team);
    }
}
