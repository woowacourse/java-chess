package chess.domain.piece;

import chess.domain.Cross;
import chess.domain.Diagonal;

public abstract class Move extends Piece {
    public Move(Position position, String name, Color color) {
        super(position, name, color);
    }

    public Move(Position position, String name, Color color, Score score) {
        super(position, name, color, score);
    }

    public void moveCross(Position target, CurrentPieces currentPieces) {
        Cross cross = Cross.findCrossByTwoPosition(this.position, target);
        cross.hasPieceInPath(this.position, target, currentPieces);
        Piece targetPiece = currentPieces.findByPosition(target);
        validateSameColor(targetPiece);
        currentPieces.removePieceIfNotEmpty(targetPiece);
    }

    public void moveDiagonal(Position target, CurrentPieces currentPieces) {
        Diagonal diagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
        diagonal.hasPieceInPath(this.position, target, currentPieces);
        Piece targetPiece = currentPieces.findByPosition(target);
        validateSameColor(targetPiece);
        currentPieces.removePieceIfNotEmpty(targetPiece);
    }

}
