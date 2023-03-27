package chess.domain.piece;

public enum PieceType {

    PAWN(Score.from(1)),
    ROOK(Score.from(5)),
    KNIGHT(Score.from(2.5)),
    BISHOP(Score.from(3)),
    QUEEN(Score.from(9)),
    KING(Score.from(0));

    final Score score;

    PieceType(final Score score){
        this.score = score;
    }
}
