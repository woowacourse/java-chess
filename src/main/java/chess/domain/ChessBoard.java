package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import chess.domain.chesspiece.ChessPiece;

public class ChessBoard {
	private List<ChessPiece> blackChessPieces;
	private List<ChessPiece> whiteChessPieces;

	public ChessBoard(List<ChessPiece> blackChessPieces, List<ChessPiece> whiteChessPieces) {
		this.blackChessPieces = blackChessPieces;
		this.whiteChessPieces = whiteChessPieces;
	}


	public boolean checkPosition(Position position) {
		List<ChessPiece> allPieces = new ArrayList<>(blackChessPieces);
		allPieces.addAll(whiteChessPieces);

		return allPieces.stream()
			.anyMatch(chessPiece -> chessPiece.equalPosition(position));
	}

	public ChessPiece findByPosition(Position position) {
		for (ChessPiece blackChessPiece : blackChessPieces) {
			if (blackChessPiece.equalPosition(position)) {
				return blackChessPiece;
			}
		}
		for (ChessPiece whiteChessPiece : whiteChessPieces) {
			if (whiteChessPiece.equalPosition(position)) {
				return whiteChessPiece;
			}
		}
		throw new NoSuchElementException();
	}

}
