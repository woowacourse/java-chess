package domain;

import java.util.List;
import java.util.Optional;

public class PiecesOnChessBoard {
	private final List<PieceWrapper> pieces;

	public PiecesOnChessBoard(List<Piece> pieces) {
		this.pieces = pieces.stream().map(PieceWrapper::new).toList();
	}

	public Optional<Team> whichTeam(Position position) {
		Optional<PieceWrapper> pieceOnPosition = pieces.stream().filter(piece -> piece.isOn(position))
			.findFirst();
		return pieceOnPosition.map(PieceWrapper::getTeam);
	}
}
