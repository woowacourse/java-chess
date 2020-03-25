package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import chess.domain.position.Position;

public class BoardFactory {
	private static final Map<String, Piece> initialBoard;

	static {
		initialBoard = new LinkedHashMap<>();
		initialBoard.put("a1", new Rook(Position.of("a1")));
		initialBoard.put("b1", new Knight(Position.of("b1")));
		initialBoard.put("c1", new Bishop(Position.of("c1")));
		initialBoard.put("d1", new Queen(Position.of("d1")));
		initialBoard.put("e1", new King(Position.of("e1")));
		initialBoard.put("f1", new Bishop(Position.of("f1")));
		initialBoard.put("g1", new Knight(Position.of("g1")));
		initialBoard.put("h1", new Rook(Position.of("h1")));

		initialBoard.put("a2", new Pawn(Position.of("a2")));
		initialBoard.put("b2", new Pawn(Position.of("b2")));
		initialBoard.put("c2", new Pawn(Position.of("c2")));
		initialBoard.put("d2", new Pawn(Position.of("d2")));
		initialBoard.put("e2", new Pawn(Position.of("e2")));
		initialBoard.put("f2", new Pawn(Position.of("f2")));
		initialBoard.put("g2", new Pawn(Position.of("g2")));
		initialBoard.put("h2", new Pawn(Position.of("h2")));
	}

	public static Board create() {
		return new Board(initialBoard);
	}
}
