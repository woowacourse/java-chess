package chess.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import chess.piece.type.Bishop;
import chess.piece.type.King;
import chess.piece.type.Knight;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.piece.type.Queen;
import chess.piece.type.Rook;

public class ChessBoard {
	private static final boolean BLACK_TEAM = true;
	private static final boolean WHITE_TEAM = false;
	private final Map<Location, Piece> board;

	public ChessBoard() {
		this.board = new HashMap<>();
		putNoble(1, false);
		putPawns(2, false);

		putPawns(7, true);
		putNoble(8, true);

	}

	private void putNoble(int row, boolean black) {
		board.put(new Location(row, 'a'), new Rook(black));
		board.put(new Location(row, 'b'), new Knight(black));
		board.put(new Location(row, 'c'), new Bishop(black));
		board.put(new Location(row, 'd'), new Queen(black));
		board.put(new Location(row, 'e'), new King(black));
		board.put(new Location(row, 'f'), new Bishop(black));
		board.put(new Location(row, 'g'), new Knight(black));
		board.put(new Location(row, 'h'), new Rook(black));
	}

	private void putPawns(int row, boolean black) {
		for (int i = 0; i < 8; i++) {
			board.put(new Location(row, (char)(i + 'a')), new Pawn(black));
		}
	}

	// 팀별 위치, 체스 정보를 가져온다.
	public Map<Location, Piece> giveMyPiece(boolean black) {
		return board.keySet().stream()
			.filter(location -> board.get(location).isSameTeam(black))
			.collect(Collectors.toMap(location -> location, board::get));
	}

	public Map<Location, Piece> getBoard() {
		return board;
	}
}
