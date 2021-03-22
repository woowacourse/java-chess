package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.piece.info.Score;
import chess.domain.position.Diagonal;
import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(Position position, Color color) {
        super(position, Name.BISHOP, color, new Score(3));
    }

    @Override
    public void move(Position target, Pieces pieces) {
        Diagonal bishopDiagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
        bishopDiagonal.hasPieceInPath(this.position, target, pieces);
        this.position = target;
    }
}
