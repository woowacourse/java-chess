package chess.piece;

import java.util.List;

// TODO : 체스통, 레파지토리 네이밍 고민
public interface PieceRepository {
	PieceSet create(boolean isBlack);
}
