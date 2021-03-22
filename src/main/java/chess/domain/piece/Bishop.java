package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Score;
import chess.domain.position.Diagonal;
import chess.domain.Name;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    public Bishop(Position position, Color color) {
        super(position, Name.BISHOP, color, new Score(3));
    }

    public Bishop(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
    }

    @Override
    public void move(Position target, Pieces pieces) {
        checkMoveRule(target);
        Diagonal bishopDiagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
        bishopDiagonal.hasPieceInPath(this.position, target, pieces);
        this.position = target;
    }

    @Override
    public void checkMoveRule(Position target) {
        if (!this.position.isDiagonal(target)) {
            throw new IllegalArgumentException("[ERROR] 비숍 이동 규칙에 어긋납니다.");
        }
    }
}
