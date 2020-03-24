package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
	private static PieceFactory instance = new PieceFactory();

	private List<Piece> pieces = new ArrayList<>();

	private PieceFactory() {

	}

	public static PieceFactory getInstance() {
		return instance;
	}

	public List<Piece> getPieces() {
		return pieces;
	}
}
