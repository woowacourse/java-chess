package chess.domain.chesspieces;

import chess.domain.position.Position;

public class Empty extends ChessPiece {
    public Empty() {
        super(".");
    }

    @Override
    public void move(Position source, Position target) {

    }
}
