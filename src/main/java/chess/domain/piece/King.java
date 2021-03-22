package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Name;
import chess.domain.position.Cross;
import chess.domain.position.Diagonal;
import chess.domain.position.Position;

import static chess.domain.Score.ZERO;

public class King extends Piece {
    public King(Position position, Color color) {
        super(position, Name.KING, color, ZERO);
    }

    @Override
    public void move(Position target, Pieces pieces) {
        checkMoveRule(target);
        try {
            moveCross(target, pieces);
        } catch (Exception e) {
            moveDiagonal(target, pieces);
        }
        this.position = target;
    }

    private void moveDiagonal(Position target, Pieces pieces) {
        Diagonal queenDiagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
        queenDiagonal.hasPieceInPath(this.position, target, pieces);
    }

    private void moveCross(Position target, Pieces pieces) {
        Cross queenCross = Cross.findCrossByTwoPosition(this.position, target);
        queenCross.hasPieceInPath(this.position, target, pieces);
    }

    public void checkMoveRule(Position target) {
        if (!(this.position.xDistance(target) == 1 || this.position.yDistance(target) == 1)) {
            throw new IllegalArgumentException("[ERROR] 킹 이동 규칙에 어긋납니다.");
        }
    }
}
