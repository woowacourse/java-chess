package chess.domain;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import chess.domain.chesspiece.ChessPiece;
import chess.factory.BoardFactory;

public class ChessBoard {
	private List<Row> board;

	public ChessBoard(List<Row> board) {
		this.board = board;
	}

	public List<Row> getBoard() {
		return board;
	}

}
