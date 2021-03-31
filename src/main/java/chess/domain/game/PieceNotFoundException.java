package chess.domain.game;

public class PieceNotFoundException extends ChessException {
    public PieceNotFoundException() {
        super("해당 위치에 체스 말이 없습니다.");
    }
}
