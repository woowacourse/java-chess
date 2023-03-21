package chess.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.InitialPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class BoardMaker {

	public static Map<Position, Piece> setPieces() {
		Map<Position, Piece> board = new HashMap<>();
		List<Piece> whitePieces = new ArrayList<>(
			List.of(new Rook(Team.WHITE), new Knight(Team.WHITE), new Bishop(Team.WHITE), new Queen(Team.WHITE),
				new King(Team.WHITE), new Bishop(Team.WHITE), new Knight(Team.WHITE), new Rook(Team.WHITE))
		);
		List<Piece> blackPieces = new ArrayList<>(
			List.of(new Rook(Team.BLACK), new Knight(Team.BLACK), new Bishop(Team.BLACK), new Queen(Team.BLACK),
				new King(Team.BLACK), new Bishop(Team.BLACK), new Knight(Team.BLACK), new Rook(Team.BLACK))
		);

		for (int i = 0; i < 8; i++) {
			board.put(new Position(i, 0), whitePieces.get(i));
		}
		for (int i = 0; i < 8; i++) {
			board.put(new Position(i, 1), new InitialPawn(Team.WHITE));
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 2; j < 6; j++) {
				board.put(new Position(i, j), new Empty());
			}
		}
		for (int i = 0; i < 8; i++) {
			board.put(new Position(i, 6), new InitialPawn(Team.BLACK));
		}
		for (int i = 7; i >= 0; i--) {
			board.put(new Position(i, 7), blackPieces.get(i));
		}
		return board;
	}
}
