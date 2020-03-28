package chess.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import chess.Score.Score;
import chess.piece.type.Bishop;
import chess.piece.type.King;
import chess.piece.type.Knight;
import chess.piece.type.Pawn;
import chess.piece.type.Piece;
import chess.piece.type.Queen;
import chess.piece.type.Rook;
import chess.team.Team;

public class ChessBoard {
	private final Map<Location, Piece> board;

	public ChessBoard() {
		this.board = new HashMap<>();
		putNoble(1, Team.WHITE);
		putPawns(2, Team.WHITE);

		putPawns(7, Team.BLACK);
		putNoble(8, Team.BLACK);
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

		System.out.println("같은 팀 : " + isNotSameTeam);
		System.out.println("전략 : " + piece.canMove(now, destination));
		System.out.println("장애물 : " + !piece.hasObstacle(board, now, destination));

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

	private void putNoble(int row, Team team) {
		board.put(new Location(row, 'a'), new Rook(team));
		board.put(new Location(row, 'b'), new Knight(team));
		board.put(new Location(row, 'c'), new Bishop(team));
		board.put(new Location(row, 'd'), new Queen(team));
		board.put(new Location(row, 'e'), new King(team));
		board.put(new Location(row, 'f'), new Bishop(team));
		board.put(new Location(row, 'g'), new Knight(team));
		board.put(new Location(row, 'h'), new Rook(team));
	}

	private void putPawns(int row, Team team) {
		for (int i = 0; i < 8; i++) {
			board.put(new Location(row, (char)(i + 'a')), new Pawn(team));
		}
	}

	// 팀별 위치, 체스 정보를 가져온다.
	public Map<Location, Piece> giveMyPiece(Team team) {
		return board.keySet().stream()
			.filter(location -> board.get(location).isSameTeam(team))
			.collect(Collectors.toMap(location -> location, board::get));
	}

	public Map<Location, Piece> getBoard() {
		return board;
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

	public Score calculateScore(Team team) {
		// 2개이상의 폰이 있는 라인을 들고있으면 좋지않을까?
		Map<Location, Piece> map = giveMyPiece(team);

		Map<Piece, Boolean> collect = map.keySet().stream()
			.collect(Collectors.toMap(map::get,
				location -> map.keySet().stream()
					.filter(targetLocation -> targetLocation.isSameCol(location))
					.filter(targetLocation -> map.get(targetLocation) instanceof Pawn)
					.count() >= 2)
			);

		return new Score(collect);
	}
}
