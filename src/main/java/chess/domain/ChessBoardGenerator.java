package chess.domain;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import chess.domain.piece.*;

public enum ChessBoardGenerator {
	BLACK_PAWN(Player.BLACK, Pawn::valueOf),
	WHITE_PAWN(Player.WHITE, Pawn::valueOf),
	BLACK_ROOK(Player.BLACK, Rook::valueOf),
	WHITE_ROOK(Player.WHITE, Rook::valueOf),
	BLACK_KNIGHT(Player.BLACK, Knight::valueOf),
	WHITE_KNIGHT(Player.WHITE, Knight::valueOf),
	BLACK_BISHOP(Player.BLACK, Bishop::valueOf),
	WHITE_BISHOP(Player.WHITE, Bishop::valueOf),
	BLACK_QUEEN(Player.BLACK, Queen::valueOf),
	WHITE_QUEEN(Player.WHITE, Queen::valueOf),
	BLACK_KING(Player.BLACK, King::valueOf),
	WHITE_KING(Player.WHITE, King::valueOf);

	private Player player;
	private BiFunction<Player, Position, Piece> generator;

	ChessBoardGenerator(Player player, BiFunction<Player, Position, Piece> generator) {
		this.player = player;
		this.generator = generator;
	}

	public static ChessBoard generate(Map<ChessBoardGenerator, List<Position>> positions) {
		ChessBoard chessBoard = new ChessBoard();
		for (ChessBoardGenerator rule : ChessBoardGenerator.values()) {
			for (Position position : positions.get(rule)) {
				chessBoard.addPiece(rule.generator.apply(rule.player, position));
			}
		}
		return chessBoard;
	}
}
