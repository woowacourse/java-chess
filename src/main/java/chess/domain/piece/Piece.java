package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    private static final Map<String, Piece> CACHE = new HashMap<>();

    static {
        CACHE.put(Team.WHITE.name() + Symbol.PAWN.name(), new Pawn(Symbol.PAWN, Team.WHITE));
        CACHE.put(Team.BLACK.name() + Symbol.PAWN.name(), new Pawn(Symbol.PAWN, Team.BLACK));
        CACHE.put(Team.WHITE.name() + Symbol.KING.name(), new King(Symbol.KING, Team.WHITE));
        CACHE.put(Team.BLACK.name() + Symbol.KING.name(), new King(Symbol.KING, Team.BLACK));
        CACHE.put(Team.WHITE.name() + Symbol.ROOK.name(), new Rook(Symbol.ROOK, Team.WHITE));
        CACHE.put(Team.BLACK.name() + Symbol.ROOK.name(), new Rook(Symbol.ROOK, Team.BLACK));
        CACHE.put(Team.WHITE.name() + Symbol.KNIGHT.name(), new Knight(Symbol.KNIGHT, Team.WHITE));
        CACHE.put(Team.BLACK.name() + Symbol.KNIGHT.name(), new Knight(Symbol.KNIGHT, Team.BLACK));
        CACHE.put(Team.WHITE.name() + Symbol.BISHOP.name(), new Bishop(Symbol.BISHOP, Team.WHITE));
        CACHE.put(Team.BLACK.name() + Symbol.BISHOP.name(), new Bishop(Symbol.BISHOP, Team.BLACK));
        CACHE.put(Team.WHITE.name() + Symbol.QUEEN.name(), new Queen(Symbol.QUEEN, Team.WHITE));
        CACHE.put(Team.BLACK.name() + Symbol.QUEEN.name(), new Queen(Symbol.QUEEN, Team.BLACK));
        CACHE.put(Team.NONE.name() + Symbol.EMPTY.name(), new Empty(Symbol.EMPTY, Team.NONE));
    }

    protected final Symbol symbol;
    protected final Team team;

    protected Piece(final Symbol symbol, final Team team) {
        this.symbol = symbol;
        this.team = team;
    }

    public static Piece of(String symbol, String team) {
        return CACHE.get(team + symbol);
    }

    public boolean isMovable(Board board, Coordinate from, Coordinate to) {
        Piece toPiece = board.findPiece(to);
        if (isSameTeam(toPiece.team) || hasNotDirection(Direction.of(from, to))) {
            return false;
        }
        return moveStrategy().isMovable(board, from, to);
    }

    public boolean hasDirection(Direction direction) {
        return direction().contains(direction);
    }

    public boolean hasNotDirection(Direction direction) {
        return !hasDirection(direction);
    }

    public abstract List<Direction> direction();

    public abstract MoveStrategy moveStrategy();

    public boolean isSameTeam(Team team) {
        return this.team.isSame(team);
    }

    public boolean isKing() {
        return symbol.isKing();
    }

    public boolean isPawn() {
        return symbol.isPawn();
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isExist() {
        return true;
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public String getSymbolByTeam() {
        if (team.isBlack()) {
            return symbol.getBlack();
        }
        return symbol.getWhite();
    }

    public String getTeam() {
        return team.name();
    }

    public String getSymbol() {
        return symbol.name();
    }

    public double getScore() {
        return symbol.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return symbol == piece.symbol && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, team);
    }
}
