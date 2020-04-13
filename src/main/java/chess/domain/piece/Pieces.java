package chess.domain.piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import chess.domain.Color;
import chess.domain.board.Position;

public class Pieces {
	public static final int KING_DIE_COUNT = 0;

	private Map<Position, Piece> pieces;

	public Pieces(Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public static Map<Position, Piece> initPieces() {
		Map<Position, Piece> pieces = new HashMap<>();

		PiecesFactory.createBlankPieces(pieces);
		PiecesFactory.createWhitePieces(Color.WHITE, pieces);
		PiecesFactory.createBlackPieces(Color.BLACK, pieces);

		return pieces;
	}

	public void move(Position sourcePosition, Position targetPosition) {
		Piece sourcePiece = pieces.get(sourcePosition);

		pieces.put(sourcePosition, Blank.getInstance());
		pieces.put(targetPosition, sourcePiece);
	}

	public boolean isSameColor(Position position, Color currentColor) {
		return pieces.get(position).isSameColor(currentColor);
	}

	public boolean isBlank(Position position) {
		return pieces.get(position).isBlank();
	}

	public Path movablePositions(Position sourcePosition) {
		Piece piece = pieces.get(sourcePosition);
		Path path = new Path(new ArrayList<>(), sourcePosition);

		return piece.findPathByRule(path, pieces);
	}

	public Map<Position, Piece> getPieces() {
		return pieces;
	}

	public boolean isKingDead(Color currentColor) {
		long kingLiveCount = pieces.keySet().stream()
			.map(position -> pieces.get(position))
			.filter(Piece::isKing)
			.filter(piece -> piece.isSameColor(currentColor))
			.count();

		return kingLiveCount == KING_DIE_COUNT;
	}

	public Piece getPieceByPosition(Position position) {
		return pieces.get(position);
	}

	public void addPiece(Position position, Piece piece) {
		pieces.put(position, piece);
	}
}
