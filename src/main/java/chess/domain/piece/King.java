package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Score;
import chess.domain.position.Cross;
import chess.domain.position.Diagonal;
import chess.domain.Name;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.Score.ZERO;

public class King extends Piece {
    private static final Position INITIAL_BLACK_POSITION = Position.of('e', '8');
    private static final Position INITIAL_WHITE_POSITION = Position.of('e', '1');

    public King(Position position, Color color) {
        super(position, Name.KING, color, ZERO);
    }

    public King(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
    }

    public static List<King> initialKings() {
        List<King> kings = new ArrayList();
        kings.add(new King(INITIAL_BLACK_POSITION, Color.BLACK));
        kings.add(new King(INITIAL_WHITE_POSITION, Color.WHITE));
        return kings;
    }

    @Override
    public void move(Position target, Pieces pieces) {
        checkMoveRule(target);
        if (this.position.isCross(target)) {
            Cross queenCross = Cross.findCrossByTwoPosition(this.position, target);
            queenCross.hasPieceInPath(this.position, target, pieces);
        }
        if (this.position.isDiagonal(target)) {
            Diagonal queenDiagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
            queenDiagonal.hasPieceInPath(this.position, target, pieces);
        }
        this.position = target;
    }

    @Override
    public void checkMoveRule(Position target) {
        if (!this.position.isCross(target) && !this.position.isDiagonal(target) ||
                !(this.position.xDistance(target) == 1 || this.position.yDistance(target) == 1)) {
            throw new IllegalArgumentException("[ERROR] 퀸 이동 규칙에 어긋납니다.");
        }
    }
}
