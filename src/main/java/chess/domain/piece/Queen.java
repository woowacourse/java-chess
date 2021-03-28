package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.piece.info.Score;
import chess.domain.position.Cross;
import chess.domain.position.Diagonal;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Queen extends Piece {
    public Queen(Position position, Color color) {
        super(position, Name.QUEEN, color, new Score(9));
    }

    private void moveDiagonal(Position target, Pieces pieces) {
        Diagonal queenDiagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
        queenDiagonal.hasPieceInPath(this.position, target, pieces);
    }

    private void moveCross(Position target, Pieces pieces) {
        Cross queenCross = Cross.findCrossByTwoPosition(this.position, target);
        queenCross.hasPieceInPath(this.position, target, pieces);
    }

    @Override
    public void checkMovable(Piece targetPiece, Direction direction) {
        if (!(Direction.crossDirection().contains(direction) ||
                Direction.diagonalDirection().contains(direction))) {
            throw new IllegalArgumentException("[ERROR] 올바른 방향이 아닙니다.");
        }
    }
}
