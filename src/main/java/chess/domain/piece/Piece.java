package chess.domain.piece;

import chess.domain.piece.direction.MoveStrategies;
import chess.domain.piece.direction.MoveStrategy;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.*;
import java.util.regex.Pattern;

public abstract class Piece {
    private static final Pattern PATTERN = Pattern.compile("[A-Z]");

    private final String piece;
    private final Color color;
    private final MoveStrategies moveStrategies;

    private Position position;

    protected Piece(final String piece, final Color color, final MoveStrategies moveStrategies, final Position position) {
        this.piece = piece;
        this.color = color;
        this.moveStrategies = moveStrategies;
        this.position = position;
    }

    public static boolean isBlack(final String piece) {
        return PATTERN.matcher(piece).matches();
    }

    public final boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public final Position getPosition() {
        return position;
    }

    public final String getPiece() {
        return piece;
    }

    public final boolean isSamePosition(final Position position) {
        return this.position.equals(position);
    }

    public final void changePosition(final Position position) {
        this.position = position;
    }

    public final boolean isSameColor(final Piece piece) {
        return color.equals(piece.color);
    }

    public final File getFile() {
        return position.getFile();
    }

    public final List<Position> possiblePositions(final Pieces basePieces, final Pieces targetPieces) {
        return findRoutes(basePieces, targetPieces);

    }

    private final List<Position> findRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        List<MoveStrategy> strategies = moveStrategies.strategies();
        for (MoveStrategy strategy : strategies) {
            positions.addAll(makeRoutes(basePieces, targetPieces, strategy));
        }
        return positions;
    }

    private List<Position> makeRoutes(final Pieces basePieces, final Pieces targetPieces, final MoveStrategy strategy) {
        if (isKing() || isPawn() || isKnight()) {
            return singleMove(basePieces, targetPieces, strategy);
        }
        return Collections.emptyList();
    }

    private List<Position> singleMove(final Pieces basePieces, final Pieces targetPieces, final MoveStrategy strategy) {
        List<Position> positions = new ArrayList<>();
        Position nextPosition = strategy.move(position);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            positions.add(nextPosition);
        }
        if (!basePiece.isPresent()) {
            positions.add(nextPosition);
        }
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece1 = (Piece) o;
        return Objects.equals(piece, piece1.piece) && color == piece1.color && Objects.equals(position, piece1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, color, position);
    }

    public abstract void move(final Target target, final Pieces basePieces, final Pieces targetPieces);

    public abstract double score(final List<Piece> pieces);

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract boolean isKnight();
}
