package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Move {
    private static final Score SCORE = new Score(5);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '8'),
            Position.of('h', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '1'),
            Position.of('h', '1'));

    public Rook(Position position, String name, Color color) {
        super(position, name, color);
    }

    public Rook(Position position, String name, Color color, Score score) {
        super(position, name, color, score);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        moveCross(target, currentPieces);
        this.position = target;
    }

    @Override
    public void moveDiagonal(Position target, CurrentPieces currentPieces) {
        throw new IllegalArgumentException("[ERROR] 룩 이동 규칙에 어긋납니다.");
    }

    public static List<Rook> generate() {
        List<Rook> blackRooks = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Rook(position, "R", Color.BLACK, SCORE))
                .collect(Collectors.toList());
        List<Rook> whiteRooks = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Rook(position, "r", Color.WHITE, SCORE))
                .collect(Collectors.toList());
        blackRooks.addAll(whiteRooks);
        return blackRooks;
    }
}
