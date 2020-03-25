package chess.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import chess.piece.Piece;
import chess.piece.PieceSet;

public class ChessBoard {
	private static final boolean BLACK_TEAM = true;
	private static final boolean WHITE_TEAM = false;
	private final Map<Location, Piece> board;

	public ChessBoard() {
		this.board = new HashMap<>();
		board.putAll(new PieceSet(BLACK_TEAM).getPieceSet());
		board.putAll(new PieceSet(WHITE_TEAM).getPieceSet());
	}

	// 팀별 위치, 체스 정보를 가져온다.

	public Map<Location, Piece> giveMyPiece(boolean black) {
		return board.keySet().stream()
			.filter(location -> board.get(location).is(black))
			.collect(Collectors.toMap(location -> location, board::get));
	}

	public Map<Location, Piece> getBoard() {
		return board;
	}
}
