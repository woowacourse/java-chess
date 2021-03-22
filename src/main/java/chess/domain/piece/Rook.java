package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.piece.info.Score;
import chess.domain.position.Cross;
import chess.domain.position.Position;

public class Rook extends Piece {
    public Rook(Position position, Color color) {
        super(position, Name.ROOK, color, new Score(5));
    }

    @Override
    public void move(Position target, Pieces pieces) {
        Cross rookCross = Cross.findCrossByTwoPosition(this.position, target);
        rookCross.hasPieceInPath(this.position, target, pieces);
        this.position = target;
    }
}
