package chess.model.piece;

import static chess.model.Team.BLACK;
import static chess.model.Team.NONE;
import static chess.model.Team.WHITE;

import chess.model.Team;
import chess.model.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    private final static Map<String, Piece> cache = new HashMap<>();

    static {
        cache.put("BLACK_KING", new King(BLACK));
        cache.put("BLACK_QUEEN", new Queen(BLACK));
        cache.put("BLACK_ROOK", new Rook(BLACK));
        cache.put("BLACK_BISHOP", new Bishop(BLACK));
        cache.put("BLACK_KNIGHT", new Knight(BLACK));
        cache.put("BLACK_PAWN", new Pawn(BLACK));
        cache.put("WHITE_KING", new King(WHITE));
        cache.put("WHITE_QUEEN", new Queen(WHITE));
        cache.put("WHITE_ROOK", new Rook(WHITE));
        cache.put("WHITE_BISHOP", new Bishop(WHITE));
        cache.put("WHITE_KNIGHT", new Knight(WHITE));
        cache.put("WHITE_PAWN", new Pawn(WHITE));
        cache.put("NONE_NONE", new Blank());
    }

    public static Piece getPiece(String key) {
        return cache.get(key);
    }

    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    public boolean isOpponentTeam(Team team) {
        return team != this.team && this.team != NONE;
    }

    public String getTeam() {
        return team.getName();
    }

    public abstract boolean canMove(Position source, Position target, Map<Position, Piece> board);

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract double score();

    public abstract String getSymbol();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Piece that = (Piece) obj;
        return this.team == that.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
