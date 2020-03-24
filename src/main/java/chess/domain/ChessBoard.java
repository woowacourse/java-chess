package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import chess.domain.chesspiece.ChessPiece;

public class ChessBoard {
	private List<ChessPiece> blackChessPieces;
	private List<ChessPiece> whiteChessPieces;

	public ChessBoard(List<ChessPiece> blackChessPieces, List<ChessPiece> whiteChessPieces) {
		this.blackChessPieces = blackChessPieces;
		this.whiteChessPieces = whiteChessPieces;
	}


	public boolean checkPosition(Position position) {
		return Stream.concat(blackChessPieces.stream(), whiteChessPieces.stream())
			.anyMatch(chessPiece -> chessPiece.equalPosition(position));
	}

	public ChessPiece findByPosition(Position position) {
		return Stream.concat(blackChessPieces.stream(), whiteChessPieces.stream())
			.filter(chessPiece -> chessPiece.equalPosition(position))
			.findFirst()
			.orElseThrow(() -> new NoSuchElementException());
	}

}
