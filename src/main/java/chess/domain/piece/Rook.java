package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Cross;
import chess.domain.Name;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('a', '8'),
            Position.of('h', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('a', '1'),
            Position.of('h', '1'));

    public Rook(Position position, Color color) {
        super(position, Name.ROOK, color, new Score(5));
    }

    public Rook(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
    }

    public static List<Rook> initialRooks() {
        List<Rook> blackRooks = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Rook(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Rook> whiteRooks = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Rook(position, Color.WHITE))
                .collect(Collectors.toList());
        blackRooks.addAll(whiteRooks);
        return blackRooks;
    }

    @Override
    public void move(Position target, Pieces pieces) {
        checkMoveRule(target);
        Cross rookCross = Cross.findCrossByTwoPosition(this.position, target);
        rookCross.hasPieceInPath(this.position, target, pieces);
        this.position = target;
    }

    @Override
    public void checkMoveRule(Position target) {
        if (!this.position.isCross(target)) {
            throw new IllegalArgumentException("[ERROR] 룩 이동 규칙에 어긋납니다.");
        }
    }
}
