package chess.board;

import java.util.Map;
import java.util.stream.Collectors;

import chess.piece.type.King;
import chess.piece.type.Piece;
import chess.team.Team;

public class ChessBoard {
	private final Map<Location, Piece> board;

	public ChessBoard(Map<Location, Piece> pieces) {
		this.board = pieces;
	}

	//. ....nq.  4
	// .....p.p  3
	// .....pp.  2
	// ....rk..  1
	// abcdefgh
	// private void putTest(Team team) {
	// 	board.put(new Location(1, 'e'), new Rook(team));
	// 	board.put(new Location(1, 'f'), new King(team));
	// 	board.put(new Location(2, 'f'), new Pawn(team));
	// 	board.put(new Location(2, 'g'), new Pawn(team));
	// 	board.put(new Location(3, 'f'), new Pawn(team));
	// 	board.put(new Location(3, 'h'), new Pawn(team));
	// 	board.put(new Location(4, 'f'), new Knight(team));
	// 	board.put(new Location(4, 'g'), new Queen(team));
	// }

	public boolean canMove(Location now, Location destination) {
		Piece piece = board.get(now);
		boolean isNotSameTeam = isNotSameTeam(destination, piece);

		return isNotSameTeam
			&& piece.canMove(now, destination)
			&& !piece.hasObstacle(board, now, destination);
	}

	private boolean isNotSameTeam(Location destination, Piece piece) {
		boolean isNotSameTeam = true;
		if (board.containsKey(destination)) {
			isNotSameTeam = !piece.isSameTeam(board.get(destination));
		}
		return isNotSameTeam;
	}

	// 팀별 위치, 체스 정보를 가져온다.
	public Map<Location, Piece> giveMyPiece(Team team) {
		return board.keySet().stream()
			.filter(location -> board.get(location).isSameTeam(team))
			.collect(Collectors.toMap(location -> location, board::get));
	}

	public void move(Location now, Location destination) {
		if (board.containsKey(destination)) {
			board.remove(destination);
		}
		Piece piece = board.remove(now);
		board.put(destination, piece);
	}

	public boolean hasTwoKings() {
		long kingCount = board.values().stream()
			.filter(piece -> piece instanceof King)
			.count();
		return kingCount == 2;
	}

	public Map<Location, Piece> getBoard() {
		return board;
	}
}
