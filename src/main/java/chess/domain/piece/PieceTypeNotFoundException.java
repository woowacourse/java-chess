package chess.domain.piece;

public class PieceTypeNotFoundException extends RuntimeException {

    public PieceTypeNotFoundException(final char value) {
        super(String.format("%c인 PieceType을 찾을 수 없습니다.", value));
    }
}
