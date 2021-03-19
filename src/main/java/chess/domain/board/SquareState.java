package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.MoveVector;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SquareState {

    private static final List<SquareState> SQUARE_STATE_POOL = new ArrayList<>();

    static {
        for (Piece piece : Piece.values()) {
            iterateTeam(piece);
        }
    }

    private final Piece piece;
    private final Team team;

    private SquareState(Piece piece, Team team) {
        this.piece = piece;
        this.team = team;
    }

    private static void iterateTeam(Piece piece) {
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

    public MoveVector findMovableVector(Point source, Point destination) {
        if (team.isBlack()) {
            return piece.findMovableVector(source.opposite(), destination.opposite()).opposite();
        }
        return piece.findMovableVector(source, destination);
    }

    public int getMoveLength() {
        return piece.getMoveLength();
    }

    public String pieceName() {
        String pieceName = piece.pieceName();
        if (team == Team.BLACK) {
            pieceName = pieceName.toUpperCase(Locale.ROOT);
        }
        return pieceName;
    }

    public boolean isTeam(Team team) {
        return this.team == team;
    }

    public boolean isNotSameTeam(SquareState squareState) {
        return this.team != squareState.team;
    }

    public boolean isNotEmpty() {
        return team != Team.NONE;
    }

    public boolean isEmpty() {
        return team == Team.NONE;
    }

    public boolean isPawn() {
        return this.piece == Piece.PAWN;
    }

    public boolean hasMovableVector(Point source, Point destination) {
        MoveVector moveVector = this.findMovableVector(source, destination);

        return moveVector != null;
    }

    public boolean isKing() {
        return piece == Piece.KING;
    }

    public double score() {
        return piece.score();
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
