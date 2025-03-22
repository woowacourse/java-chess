package chess.piece;

import chess.Position;

// NOTE: 살아있는 기물정보를 다룸
public class LivePiece {
    private final Piece piece;
    private Position position;

    public LivePiece(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public LivePiece(Position position, Piece piece) {
        this.piece = piece;
        this.position = position;
    }

}
