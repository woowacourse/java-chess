package chess.domain.piece.pieces;

import chess.domain.board.Board;
import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public class PiecesFactory {
	public static Pieces of() {
		List<Piece> pieces = new ArrayList<>();
		pieces.add(new Rook(Board.of("a1"), "r", Color.WHITE));
		pieces.add(new Rook(Board.of("h1"), "r", Color.WHITE));

		pieces.add(new Knight(Board.of("b1"), "n", Color.WHITE));
		pieces.add(new Knight(Board.of("g1"), "n", Color.WHITE));

		pieces.add(new Bishop(Board.of("c1"), "b", Color.WHITE));
		pieces.add(new Bishop(Board.of("f1"), "b", Color.WHITE));

		pieces.add(new King(Board.of("d1"), "k", Color.WHITE));

		pieces.add(new Queen(Board.of("e1"), "q", Color.WHITE));

		pieces.add(new Pawn(Board.of("a2"), "p", Color.WHITE));
		pieces.add(new Pawn(Board.of("b2"), "p", Color.WHITE));
		pieces.add(new Pawn(Board.of("c2"), "p", Color.WHITE));
		pieces.add(new Pawn(Board.of("d2"), "p", Color.WHITE));
		pieces.add(new Pawn(Board.of("e2"), "p", Color.WHITE));
		pieces.add(new Pawn(Board.of("f2"), "p", Color.WHITE));
		pieces.add(new Pawn(Board.of("g2"), "p", Color.WHITE));
		pieces.add(new Pawn(Board.of("h2"), "p", Color.WHITE));


		pieces.add(new Rook(Board.of("a8"), "R", Color.BLACK));
		pieces.add(new Rook(Board.of("h8"), "R", Color.BLACK));

		pieces.add(new Knight(Board.of("b8"), "N", Color.BLACK));
		pieces.add(new Knight(Board.of("g8"), "N", Color.BLACK));

		pieces.add(new Bishop(Board.of("c8"), "B", Color.BLACK));
		pieces.add(new Bishop(Board.of("f8"), "B", Color.BLACK));

		pieces.add(new King(Board.of("d8"), "K", Color.BLACK));

		pieces.add(new Queen(Board.of("e8"), "Q", Color.BLACK));

		pieces.add(new Pawn(Board.of("a7"), "P", Color.BLACK));
		pieces.add(new Pawn(Board.of("b7"), "P", Color.BLACK));
		pieces.add(new Pawn(Board.of("c7"), "P", Color.BLACK));
		pieces.add(new Pawn(Board.of("d7"), "P", Color.BLACK));
		pieces.add(new Pawn(Board.of("e7"), "P", Color.BLACK));
		pieces.add(new Pawn(Board.of("f7"), "P", Color.BLACK));
		pieces.add(new Pawn(Board.of("g7"), "P", Color.BLACK));
		pieces.add(new Pawn(Board.of("h7"), "P", Color.BLACK));

		return new Pieces(pieces);
	}
}
