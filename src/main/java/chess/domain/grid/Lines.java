package chess.domain.grid;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Lines {
    private static final String ROW_REFERENCE = "87654321";
    private static final char MIN_X_POSITION = 'a';
    private static final int SAME_COLUMN_BOUND = 2;

    private List<Line> lines;

    public Lines(List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public Line line(final char y) {
        int index = ROW_REFERENCE.indexOf(y);
        return lines.get(index);
    }

    public double pawnCountInSameColumn(final boolean isBlack, final int i) {
        double result = 0;
        char x = (char) (MIN_X_POSITION + i);
        int pawnCountInSameColumn =
                (int) lines.stream()
                        .map(line -> line.findPiece(x))
                        .filter(piece -> (piece instanceof Pawn && piece.isBlack() == isBlack))
                        .count();
        if (pawnCountInSameColumn >= SAME_COLUMN_BOUND) {
            result += pawnCountInSameColumn;
        }
        return result;
    }

    public double totalScore(final boolean isBlack) {
        return lines.stream()
                .flatMap(line -> line.getPieces()
                        .stream()
                        .filter(piece -> !piece.isEmpty())
                        .filter(piece -> piece.isBlack() == isBlack)
                        .map(Piece::score))
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public Piece piece(final Position position) {
        char x = position.x();
        char y = position.y();
        Line line = line(y);
        return line.findPiece(x);
    }

    public void assign(final Position position, final Piece piece) {
        char x = position.x();
        char y = position.y();
        Line line = line(y);
        line.assignPiece(x, piece);
        piece(position).moveTo(position);
    }

    public List<Position> route(final Piece sourcePiece, final Direction direction, final int stepRange) {
        List<Position> positions = new ArrayList<>();
        Position sourcePosition = sourcePiece.position();

        for (int i = 1; i <= stepRange; i++) {
            Position movedPosition = sourcePosition.next(direction.getXDegree() * i, direction.getYDegree() * i);
            if (!movedPosition.isInGridRange()) {
                break;
            }
            positions.add(movedPosition);
            if (!piece(movedPosition).isEmpty()) {
                break;
            }
        }
        return positions;
    }
}
