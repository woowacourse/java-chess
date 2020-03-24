package chess.piece;

import java.util.List;

// 레파지토리 네이밍 고민
public interface PieceRepository {
	List<Piece> create();
}
