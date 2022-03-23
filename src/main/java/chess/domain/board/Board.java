package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Board {

	private final Map<Position, Piece> board;

	public Board() {
		this.board = new HashMap<>();
		initialBatchPiece();
	}

	private void initialBatchPiece() {
		for (Position position : Position.getPositions()) {
			board.put(position, new Blank());
		}

		List<Piece> blackSpecials = initSpecialBuilder(Team.Black);
		List<Piece> whiteSpecials = initSpecialBuilder(Team.White);
		for (int i = 0; i < 8; i++) {
			board.put(Position.of(8, i + 1), blackSpecials.get(i));
			board.put(Position.of(7, i + 1), new Pawn(Team.Black));

			board.put(Position.of(1, i + 1), whiteSpecials.get(i));
			board.put(Position.of(2, i + 1), new Pawn(Team.White));
		}
	}

	private List<Piece> initSpecialBuilder(Team team) {
		List<Piece> pieces = new ArrayList<>();
		pieces.add(new Rook(team));
		pieces.add(new Knight(team));
		pieces.add(new Bishop(team));
		pieces.add(new Queen(team));
		pieces.add(new King(team));
		pieces.add(new Bishop(team));
		pieces.add(new Knight(team));
		pieces.add(new Rook(team));
		return pieces;
	}

	public boolean isBlank(Position position) {
		return board.get(position).isBlank();
	}

	public Map<Position, Piece> getBoard() {
		return board;
	}
}
