package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Name;
import chess.domain.Score;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(Position position, Color color) {
        super(position, Name.KNIGHT, color, new Score(2.5));
    }

    public Knight(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
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
