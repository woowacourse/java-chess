package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public abstract class UnchangeablePiece extends Piece {

    protected UnchangeablePiece(PieceType pieceType, Position position, Player player) {
        super(pieceType, position, player);
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }
}
