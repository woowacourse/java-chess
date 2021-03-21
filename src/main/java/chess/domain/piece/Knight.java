package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Name;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('b', '8'),
            Position.of('g', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('b', '1'),
            Position.of('g', '1'));

    public Knight(Position position, Color color) {
        super(position, Name.KNIGHT, color, Score.TOW_FIVE);
    }

    public Knight(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
    }


    public static List<Knight> initialKnights() {
        List<Knight> blackKnights = INITIAL_BLACK_POSITIONS.stream()
                .map(position -> new Knight(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Knight> whiteKnights = INITIAL_WHITE_POSITIONS.stream()
                .map(position -> new Knight(position, Color.WHITE))
                .collect(Collectors.toList());
        blackKnights.addAll(whiteKnights);
        return blackKnights;
    }

    @Override
    public void move(Position target, Pieces pieces) {
        checkMoveRule(target);
        this.position = target;
    }

    @Override
    public void checkMoveRule(Position target) {
        if (!((Math.abs(this.position.xDistance(target)) == 2 && Math.abs(this.position.yDistance(target)) == 1) ||
                (Math.abs(this.position.xDistance(target)) == 1 && Math.abs(this.position.yDistance(target)) == 2))) {
            throw new IllegalArgumentException("[ERROR] 나이트의 이동 규칙에 어긋났습니다.");
        }
    }
}
