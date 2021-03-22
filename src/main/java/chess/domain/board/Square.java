package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Square {

    private static final List<Square> SQUARE_STATE_POOL = new ArrayList<>();

    static {
        for (Piece piece : Piece.values()) {
            iterateTeam(piece);
        }
    }

    private final Piece piece;
    private final Team team;

    private Square(Piece piece, Team team) {
        this.piece = piece;
        this.team = team;
    }

    private static void iterateTeam(Piece piece) {
        for (Team team : Team.values()) {
            SQUARE_STATE_POOL.add(new Square(piece, team));
        }
    }

    public static Square of(Piece piece, Team team) {
        return SQUARE_STATE_POOL.stream()
            .filter(square -> square.piece == piece && square.team == team)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public Vector findMovableVector(Point source, Point destination) {
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

    public boolean isNotSameTeam(Square square) {
        return this.team != square.team;
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
        Vector vector = this.findMovableVector(source, destination);

        return vector != null;
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
        Square square = (Square) o;
        return Objects.equals(piece, square.piece) && team == square.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, team);
    }
}
