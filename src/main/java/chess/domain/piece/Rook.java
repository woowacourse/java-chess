package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Name;
import chess.domain.Score;
import chess.domain.position.Cross;
import chess.domain.position.Position;

public class Rook extends Piece {
    public Rook(Position position, Color color) {
        super(position, Name.ROOK, color, new Score(5));
    }

    public Rook(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
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
