package chess;

import chess.domain.Player;
import chess.domain.piece.Type;

public enum PieceImage {
	BLACK_PAWN(Player.BLACK, Type.PAWN, "♟"),
	BLACK_BISHOP(Player.BLACK, Type.BISHOP, "♝"),
	BLACK_KING(Player.BLACK, Type.KING, "♚"),
	BLACK_KNIGHT(Player.BLACK, Type.KNIGHT, "♞"),
	BLACK_QUEEN(Player.BLACK, Type.QUEEN, "♛"),
	BLACK_ROOK(Player.BLACK, Type.ROOK, "♜"),
	WHITE_PAWN(Player.WHITE, Type.PAWN, "♙"),
	WHITE_BISHOP(Player.WHITE, Type.BISHOP, "♗"),
	WHITE_KING(Player.WHITE, Type.KING, "♔"),
	WHITE_KNIGHT(Player.WHITE, Type.KNIGHT, "♘"),
	WHITE_QUEEN(Player.WHITE, Type.QUEEN, "♕"),
	WHITE_ROOK(Player.WHITE, Type.ROOK, "♖"),
	EMPTY(Player.EMPTY, Type.EMPTY, "");

	private Player player;
	private Type type;
	private String image;

	PieceImage(Player player, Type type, String image) {
		this.player = player;
		this.type = type;
		this.image = image;
	}

	public static PieceImage getPieceImage(Player player, Type type) {
		for(PieceImage pieceImage : PieceImage.values()) {
			if(pieceImage.player.equals(player) && (pieceImage.type.equals(type))) {
				return pieceImage;
			}
		}
		throw new IllegalArgumentException("해당 이미지를 찾을 수 없습니다.");
	}

	public String getImage() {
		return image;
	}
}
