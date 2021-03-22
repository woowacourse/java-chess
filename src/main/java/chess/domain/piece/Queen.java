package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Score;
import chess.domain.position.Cross;
import chess.domain.position.Diagonal;
import chess.domain.Name;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Position position, Color color) {
        super(position, Name.QUEEN, color, new Score(9));
    }

    public Queen(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
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
        if (!this.position.isCross(target) && !this.position.isDiagonal(target)) {
            throw new IllegalArgumentException("[ERROR] 퀸 이동 규칙에 어긋납니다.");
        }
    }
}
