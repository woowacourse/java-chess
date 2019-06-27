package chess.domain;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import chess.domain.piece.*;

public enum ChessPiece {
	BLACK_PAWN(Player.BLACK, Type.PAWN, Pawn::valueOf, "♟"),
	WHITE_PAWN(Player.WHITE, Type.PAWN, Pawn::valueOf, "♙"),
	BLACK_ROOK(Player.BLACK, Type.ROOK, Rook::valueOf, "♜"),
	WHITE_ROOK(Player.WHITE, Type.ROOK, Rook::valueOf, "♖"),
	BLACK_KNIGHT(Player.BLACK, Type.KNIGHT, Knight::valueOf, "♞"),
	WHITE_KNIGHT(Player.WHITE, Type.KNIGHT, Knight::valueOf, "♘"),
	BLACK_BISHOP(Player.BLACK, Type.BISHOP, Bishop::valueOf, "♝"),
	WHITE_BISHOP(Player.WHITE, Type.BISHOP, Bishop::valueOf, "♗"),
	BLACK_QUEEN(Player.BLACK, Type.QUEEN, Queen::valueOf, "♛"),
	WHITE_QUEEN(Player.WHITE, Type.QUEEN, Queen::valueOf, "♕"),
	BLACK_KING(Player.BLACK, Type.KING, King::valueOf, "♚"),
	WHITE_KING(Player.WHITE, Type.KING, King::valueOf, "♔"),
	EMPTY(Player.EMPTY, Type.EMPTY, EmptyPiece::valueOf, "");

	private Player player;
	private Type type;
	private BiFunction<Player, Position, Piece> generator;
	private String image;

	ChessPiece(Player player, Type type, BiFunction<Player, Position, Piece> generator, String image) {
		this.player = player;
		this.type = type;
		this.generator = generator;
		this.image = image;
	}

	public static ChessBoard generateChessBoard(ChessInitialPosition chessPosition) {
		Map<ChessPiece, List<Position>> positions = chessPosition.getPositions();
		ChessBoard chessBoard = new ChessBoard();
		for (ChessPiece chessPiece : ChessPiece.values()) {
			if (chessPiece.equals(EMPTY)) {
				continue;
			}
			for (Position position : positions.get(chessPiece)) {
				chessBoard.addPiece(chessPiece.generator.apply(chessPiece.player, position));
			}
		}
		return chessBoard;
	}

	public static Piece generatePiece(Player player, Type type, Position position) {
		for (ChessPiece chessPiece : ChessPiece.values()) {
			if (isSameChessPiece(chessPiece, player, type)) {
				return chessPiece.generator.apply(player, position);
			}
		}
		throw new IllegalArgumentException("체스 말을 생성할 수 없습니다.");
	}

	public static String getPieceImage(Player player, Type type) {
		for (ChessPiece chessPiece : ChessPiece.values()) {
			if (isSameChessPiece(chessPiece, player, type)) {
				return chessPiece.image;
			}
		}
		throw new IllegalArgumentException("해당 이미지를 찾을 수 없습니다.");
	}

	private static boolean isSameChessPiece(ChessPiece chessPiece, Player player, Type type) {
		return (chessPiece.player.equals(player) && chessPiece.type.equals(type));
	}

	public String getImage() {
		return image;
	}

	public Type getType() {
		return type;
	}
}
