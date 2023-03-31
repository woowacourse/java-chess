package domain.piece;

import java.util.function.Function;

public enum PieceMaker {
    KING(King::new),
    QUEEN(Queen::new),
    ROOK(Rook::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    PAWN(color -> {
        if (color == Color.BLACK) {
            return new BlackPawn();
        }
        return new WhitePawn();
    });

    private final Function<Color, Piece> pieceMaker;

    PieceMaker(Function<Color, Piece> pieceMaker) {
        this.pieceMaker = pieceMaker;
    }

    public static PieceMaker from(PieceName pieceName) {
        try {
            return valueOf(pieceName.name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 해당하는 말의 이름이 존재하지 않습니다.");
        }
    }

    public Piece make(Color color) {
        return this.pieceMaker.apply(color);
    }
}
