package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Color;

public class PieceMapper {
	private static PieceMapper PIECE_MAPPER = new PieceMapper();
	private Map<String, Piece> pieceDBMapper;
	private Map<String, String> pieceViewMapper;

	// TODO : 매우 비효율적인 방법인듯? 특히나 두 번이나 Mapper를 만드는 건...
	public PieceMapper() {
		this.pieceDBMapper = new HashMap<>();

		pieceDBMapper.put("p", new Pawn(Color.WHITE, "p"));
		pieceDBMapper.put("P", new Pawn(Color.BLACK, "P"));
		pieceDBMapper.put("b", new Bishop(Color.WHITE, "b"));
		pieceDBMapper.put("B", new Bishop(Color.BLACK, "B"));
		pieceDBMapper.put("n", new Knight(Color.WHITE, "n"));
		pieceDBMapper.put("N", new Knight(Color.BLACK, "N"));
		pieceDBMapper.put("k", new King(Color.WHITE, "k"));
		pieceDBMapper.put("K", new King(Color.BLACK, "K"));
		pieceDBMapper.put("q", new Queen(Color.WHITE, "q"));
		pieceDBMapper.put("Q", new Queen(Color.BLACK, "Q"));
		pieceDBMapper.put("r", new Rook(Color.WHITE, "r"));
		pieceDBMapper.put("R", new Rook(Color.BLACK, "R"));
		pieceDBMapper.put("blank", Blank.getInstance());

		this.pieceViewMapper = new HashMap<>();

		pieceViewMapper.put("p", "pawn_white.png");
		pieceViewMapper.put("P", "pawn_black.png");
		pieceViewMapper.put("b", "bishop_white.png");
		pieceViewMapper.put("B", "bishop_black.png");
		pieceViewMapper.put("n", "knight_white.png");
		pieceViewMapper.put("N", "knight_black.png");
		pieceViewMapper.put("k", "king_white.png");
		pieceViewMapper.put("K", "king_black.png");
		pieceViewMapper.put("q", "queen_white.png");
		pieceViewMapper.put("Q", "queen_black.png");
		pieceViewMapper.put("r", "rook_white.png");
		pieceViewMapper.put("R", "rook_black.png");
		pieceViewMapper.put("blank", "blank.png");
	}

	public static PieceMapper getInstance() {
		return PIECE_MAPPER;
	}

	public Piece findDBPiece(String pieceSymbol) {
		return pieceDBMapper.get(pieceSymbol);
	}

	public String findViewPiece(String pieceSymbol) {
		return pieceViewMapper.get(pieceSymbol);
	}

}
