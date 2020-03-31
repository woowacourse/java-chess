package chess.domain.piece.pieces;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.TestPieceFactory;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestPiecesFactory {
	public static Pieces of(List<Position> positions) {
		List<Piece> pieces = new ArrayList<>();

		for (Position position : positions) {
			pieces.add(TestPieceFactory.createRook(position, Color.WHITE));
		}
		return new Pieces(pieces);
	}

	public static Pieces of(List<Position> positions, Color color) {
		List<Piece> pieces = new ArrayList<>();

		for (Position position : positions) {
			pieces.add(TestPieceFactory.createRook(position, color));
		}
		return new Pieces(pieces);
	}

	public static Pieces createBy(List<Piece> inputPieces) {
		List<Piece> pieces = new ArrayList<>(inputPieces);
		return new Pieces(pieces);
	}

	public static Pieces createOnlyWhite() {
		List<Piece> pieces = Arrays.stream(PieceInitializer.values())
				.filter(pieceInitializer -> pieceInitializer.getColor().isWhite())
				.map(Piece::new)
				.collect(Collectors.toList());
	
		return new Pieces(pieces);
	}
}
