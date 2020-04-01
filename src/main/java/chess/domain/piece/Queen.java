package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {
    public Queen(PieceType pieceType, Team team) {
        super(pieceType, team);
    }

    @Override
    public boolean movable(Position source, Position target) {
        return new Rook(this.pieceType, this.team).movable(source, target)
                && new Bishop(this.pieceType, this.team).movable(source, target);
    }
}
