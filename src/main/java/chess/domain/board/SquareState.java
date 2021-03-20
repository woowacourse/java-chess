package chess.domain.board;

import chess.domain.piece.MoveVector;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SquareState {

    private static final List<SquareState> SQUARE_STATE_POOL = new ArrayList<>();

    static {
        for (Piece piece : Piece.values()) {
            generateSquareStatePoolByPiece(piece);
        }
    }

    private final Piece piece;
    private final Team team;

    private SquareState(Piece piece, Team team) {
        this.piece = piece;
        this.team = team;
    }

    private static void generateSquareStatePoolByPiece(Piece piece) {
        for (Team team : Team.values()) {
            SQUARE_STATE_POOL.add(new SquareState(piece, team));
        }
    }

    public static SquareState of(Piece piece, Team team) {
        return SQUARE_STATE_POOL.stream()
            .filter(squareState -> squareState.piece == piece && squareState.team == team)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
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

    public boolean isTeam(Team team) {
        return this.team == team;
    }

    public boolean isNotTeam(Team team) {
        return this.team != team;
    }

    public boolean isEnemy(SquareState sourceSquareState) {
        return team == sourceSquareState.team.opposingTeam();
    }

    public boolean isEmpty() {
        return team == Team.NONE;
    }

    public boolean isPieceTypeOf(Piece piece) {
        return this.piece == piece;
    }

    public boolean isNotPieceTypeOf(Piece piece) {
        return this.piece != piece;
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
