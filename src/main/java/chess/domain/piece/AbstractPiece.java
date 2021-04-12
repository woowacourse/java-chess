package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractPiece implements Piece {

    protected static final String ERROR_CAN_NOT_MOVE = "기물이 이동할 수 없는 위치입니다.";
    private static final String PAWN = "p";
    private static final String ROOK = "r";
    private static final String KNIGHT = "n";
    private static final String QUEEN = "q";
    private static final String KING = "k";
    private static final String BISHOP = "b";

    protected final Color color;
    protected final Position position;

    protected AbstractPiece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public static Piece of(final String symbol, final Position position) {
        String symbolType = symbol.toLowerCase();
        Color color = getColor(symbol, symbolType);
        if (PAWN.equals(symbolType)) {
            return new Pawn(color, position);
        }
        if (ROOK.equals(symbolType)) {
            return new Rook(color, position);
        }
        if (KNIGHT.equals(symbolType)) {
            return new Knight(color, position);
        }
        if (QUEEN.equals(symbolType)) {
            return new Queen(color, position);
        }
        if (KING.equals(symbolType)) {
            return new King(color, position);
        }
        if (BISHOP.equals(symbolType)) {
            return new Bishop(color, position);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private static Color getColor(final String symbol, final String symbolType) {
        if (symbol.equals(symbolType)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    @Override
    public boolean isSameColor(final Color color) {
        return color == this.color;
    }

    @Override
    public boolean isSameColor(final Piece piece) {
        return piece.isSameColor(color);
    }

    @Override
    public boolean isSameColumn(final Point point) {
        return position.isSameColumn(point);
    }

    protected String changeColorSymbol(final String symbol) {
        if (color.isBlack()) {
            return symbol.toUpperCase();
        }
        return symbol;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    protected List<Position> positions(final Map<Position, Piece> pieces,
        final List<Direction> directions, final int ableLength) {
        final List<Position> positions = new ArrayList<>();
        for (Direction direction : directions) {
            addMovableDirectionPositions(pieces, ableLength, positions, direction);
        }
        return positions;
    }

    private void addMovableDirectionPositions(final Map<Position, Piece> pieces,
        final int ableLength, List<Position> positions, final Direction direction) {
        boolean isStop = false;
        int distance = 1;
        while (!isStop && distance <= ableLength) {
            isStop = addMovablePosition(pieces, positions, direction, distance);
            distance++;
        }
    }

    protected boolean addMovablePosition(final Map<Position, Piece> pieces,
        final List<Position> positions,final Direction direction, final int distance) {
        int dx = direction.getXDegree() * distance;
        int dy = direction.getYDegree() * distance;
        if (!position.isAdd(dx, dy)) {
            return true;
        }
        Position movePosition = position.addedPosition(dx, dy);
        Piece piece = pieces.get(movePosition);
        if (!Objects.isNull(piece) && piece.isSameColor(color)) {
            return true;
        }
        positions.add(movePosition);
        return !Objects.isNull(piece);
    }

    protected void validateMove(final Position position, final Map<Position, Piece> pieces) {
        List<Position> positions = movablePositions(pieces);
        if (!positions.contains(position)) {
            throw new IllegalArgumentException(ERROR_CAN_NOT_MOVE);
        }
    }

    @Override
    public Position position() {
        return position;
    }
}
