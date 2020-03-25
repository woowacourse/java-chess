package chess.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessBoard {
	private final List<Row> board;

	public ChessBoard(List<Row> board) {
		this.board = new ArrayList<>(board);
	}

	public List<Row> getBoard() {
		return Collections.unmodifiableList(board);
	}



}
