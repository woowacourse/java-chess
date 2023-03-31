package chess.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Player {
    private static final Integer MINIMUM_SAME_FILE_COUNT = 1;
    private static final Integer MINIMUM_SAME_FILE_WEIGHT = 1;

    private final Color color;
    private final Pieces pieces;

    private Player(final Color color, final Pieces pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public static Player fromWhitePlayer(final Pieces pieces) {
        return new Player(Color.WHITE, pieces);
    }

    public static Player fromBlackPlayer(final Pieces pieces) {
        return new Player(Color.BLACK, pieces);
    }

    public Pieces getPieces() {
        return pieces;
    }

    public Color getColor() {
        return color;
    }

    public boolean hasPositionPiece(final Position findPosition) {
        return pieces.hasPosition(findPosition);
    }

    public Piece movePiece(final List<Position> allPosition, final Position findPosition, final Position targetPosition) {
        if (pieces.hasPosition(targetPosition)) {
            throw new IllegalArgumentException("상대 기물 위치로만 이동할 수 있습니다.");
        }

        Piece findPiece = pieces.findPiece(findPosition);
        findPiece.move(allPosition, targetPosition, color);
        return findPiece;
    }

    public Optional<Piece> removePiece(final Position removalPosition) {
        return pieces.remove(removalPosition);
    }

    public Score getTotalScore() {
        long pawnSameFileCount = countPawnPerFile().values().stream()
                .filter(value -> value > MINIMUM_SAME_FILE_COUNT)
                .count();

        Score subtrahend = Score.from(pawnSameFileCount * MINIMUM_SAME_FILE_WEIGHT);
        Score total = Score.from(pieces.getPieces().stream().mapToDouble(Piece::getScore).sum());
        return total.subtract(subtrahend);
    }

    private Map<Character, Long> countPawnPerFile() {
        return pieces.getPieces().stream()
                .filter(piece -> piece.isSameShape(Shape.PAWN))
                .collect(groupingBy(piece -> piece.getPosition().getFileValue(), counting()));
    }

    public String getColorName() {
        return color.name();
    }

    @Override
    public String toString() {
        return "Player{" +
                "color='" + color + '\'' +
                ", pieces=" + pieces +
                '}';
    }

}
