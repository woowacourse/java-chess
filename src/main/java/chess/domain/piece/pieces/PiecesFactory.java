package chess.domain.piece.pieces;

import chess.domain.position.PositionFactory;
import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public class PiecesFactory {
	public static Pieces of() {
		List<Piece> pieces = new ArrayList<>();
		pieces.add(new Rook(PositionFactory.of("a1"), "r", Color.WHITE));
		pieces.add(new Rook(PositionFactory.of("h1"), "r", Color.WHITE));
		pieces.add(new Knight(PositionFactory.of("b1"), "n", Color.WHITE));
		pieces.add(new Knight(PositionFactory.of("g1"), "n", Color.WHITE));
		pieces.add(new Bishop(PositionFactory.of("c1"), "b", Color.WHITE));
		pieces.add(new Bishop(PositionFactory.of("f1"), "b", Color.WHITE));
		pieces.add(new King(PositionFactory.of("d1"), "k", Color.WHITE));
		pieces.add(new Queen(PositionFactory.of("e1"), "q", Color.WHITE));
		pieces.add(new Pawn(PositionFactory.of("a2"), "p", Color.WHITE));
		pieces.add(new Pawn(PositionFactory.of("b2"), "p", Color.WHITE));
		pieces.add(new Pawn(PositionFactory.of("c2"), "p", Color.WHITE));
		pieces.add(new Pawn(PositionFactory.of("d2"), "p", Color.WHITE));
		pieces.add(new Pawn(PositionFactory.of("e2"), "p", Color.WHITE));
		pieces.add(new Pawn(PositionFactory.of("f2"), "p", Color.WHITE));
		pieces.add(new Pawn(PositionFactory.of("g2"), "p", Color.WHITE));
		pieces.add(new Pawn(PositionFactory.of("h2"), "p", Color.WHITE));

		pieces.add(new Rook(PositionFactory.of("a8"), "R", Color.BLACK));
		pieces.add(new Rook(PositionFactory.of("h8"), "R", Color.BLACK));
		pieces.add(new Knight(PositionFactory.of("b8"), "N", Color.BLACK));
		pieces.add(new Knight(PositionFactory.of("g8"), "N", Color.BLACK));
		pieces.add(new Bishop(PositionFactory.of("c8"), "B", Color.BLACK));
		pieces.add(new Bishop(PositionFactory.of("f8"), "B", Color.BLACK));
		pieces.add(new King(PositionFactory.of("d8"), "K", Color.BLACK));
		pieces.add(new Queen(PositionFactory.of("e8"), "Q", Color.BLACK));
		pieces.add(new Pawn(PositionFactory.of("a7"), "P", Color.BLACK));
		pieces.add(new Pawn(PositionFactory.of("b7"), "P", Color.BLACK));
		pieces.add(new Pawn(PositionFactory.of("c7"), "P", Color.BLACK));
		pieces.add(new Pawn(PositionFactory.of("d7"), "P", Color.BLACK));
		pieces.add(new Pawn(PositionFactory.of("e7"), "P", Color.BLACK));
		pieces.add(new Pawn(PositionFactory.of("f7"), "P", Color.BLACK));
		pieces.add(new Pawn(PositionFactory.of("g7"), "P", Color.BLACK));
		pieces.add(new Pawn(PositionFactory.of("h7"), "P", Color.BLACK));

		return new Pieces(pieces);
	}
}
