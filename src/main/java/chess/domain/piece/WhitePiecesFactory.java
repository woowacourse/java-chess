package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

import chess.db.WhitePieceMapper;
import chess.domain.board.File;
import chess.domain.board.Position;

public class WhitePiecesFactory {
	public static Pieces create() {
		Map<Position, Piece> pieces = new HashMap<>();

		initWhitePawn(pieces);
		initWhiteRook(pieces);
		initWhiteBishop(pieces);
		initWhiteKnight(pieces);
		initWhiteQueen(pieces);
		initWhiteKing(pieces);

		return new Pieces(pieces);
	}

	private static void initWhiteKing(Map<Position, Piece> pieces) {
		pieces.put(Position.of("e1"), WhitePieceMapper.mappingBy("♔", "Initial"));
	}

	private static void initWhiteQueen(Map<Position, Piece> pieces) {
		pieces.put(Position.of("d1"), WhitePieceMapper.mappingBy("♕", "Initial"));
	}

	private static void initWhiteKnight(Map<Position, Piece> pieces) {
		pieces.put(Position.of("b1"), WhitePieceMapper.mappingBy("♘", "Initial"));
		pieces.put(Position.of("g1"), WhitePieceMapper.mappingBy("♘", "Initial"));
	}

	private static void initWhiteBishop(Map<Position, Piece> pieces) {
		pieces.put(Position.of("c1"), WhitePieceMapper.mappingBy("♗", "Initial"));
		pieces.put(Position.of("f1"), WhitePieceMapper.mappingBy("♗", "Initial"));
	}

	private static void initWhiteRook(Map<Position, Piece> pieces) {
		pieces.put(Position.of("a1"), WhitePieceMapper.mappingBy("♖", "Initial"));
		pieces.put(Position.of("h1"), WhitePieceMapper.mappingBy("♖", "Initial"));
	}

	private static void initWhitePawn(Map<Position, Piece> pieces) {
		for (File file : File.values()) {
			pieces.put(Position.of(file.getFile() + "2"), WhitePieceMapper.mappingBy("♙", "Initial"));
		}
	}
}
