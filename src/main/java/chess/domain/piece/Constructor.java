package chess.domain.piece;

public interface Constructor {
    Piece create(Color color, char x, char y);
}
