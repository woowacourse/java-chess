package chess.domain.piece.pieces;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public class PiecesFactory {
	public static Pieces of() {
		List<Piece> pieces = new ArrayList<>();

		for(PieceInitializer pieceInitializer : PieceInitializer.values()) {
			pieces.add(new Piece(pieceInitializer));
		}

		return new Pieces(pieces);
	}
}
