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
	private static final Map<String, Piece> lowerBoard;
	private static final Map<String, Piece> upperBoard;

	static {
		lowerBoard = new LinkedHashMap<>();
		upperBoard = new LinkedHashMap<>();

		lowerBoard.put("a1", new Rook(Position.of("a1")));
		lowerBoard.put("b1", new Knight(Position.of("b1")));
		lowerBoard.put("c1", new Bishop(Position.of("c1")));
		lowerBoard.put("d1", new Queen(Position.of("d1")));
		lowerBoard.put("e1", new King(Position.of("e1")));
		lowerBoard.put("f1", new Bishop(Position.of("f1")));
		lowerBoard.put("g1", new Knight(Position.of("g1")));
		lowerBoard.put("h1", new Rook(Position.of("h1")));

		upperBoard.put("a1", new Rook(Position.of("a1")));
		upperBoard.put("b1", new Knight(Position.of("b1")));
		upperBoard.put("c1", new Bishop(Position.of("c1")));
		upperBoard.put("d1", new King(Position.of("d1")));
		upperBoard.put("e1", new Queen(Position.of("e1")));
		upperBoard.put("f1", new Bishop(Position.of("f1")));
		upperBoard.put("g1", new Knight(Position.of("g1")));
		upperBoard.put("h1", new Rook(Position.of("h1")));

		for (char c = 'a'; c <= 'h'; c++) {
			String key = String.valueOf(c) + 2;
			lowerBoard.put(key, new Pawn(Position.of(key)));
			upperBoard.put(key, new Pawn(Position.of(key)));
		}
	}

	public static Boards create() {
		return Boards.of(lowerBoard, upperBoard);
	}
}
