package chess.board;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.gamestate.GameState;
import chess.piece.King;
import chess.piece.Piece;
import chess.team.Team;

public class ChessBoard {
	private final Map<Location, Piece> board;

	public ChessBoard(Map<Location, Piece> pieces) {
		Objects.requireNonNull(pieces, "피스 정보가 없습니다.");
		this.board = pieces;
	}

	public boolean canMove(Location now, Location destination) {
		Piece piece = board.get(now);
		checkSameTeam(piece, destination);

		return piece.canMove(now, destination)
			&& !piece.hasObstacle(board, now, destination);
	}

	private void checkSameTeam(Piece piece, Location destination) {
		boolean sameTeam = false;
		if (board.containsKey(destination)) {
			Piece other = board.get(destination);
			sameTeam = piece.isSameTeam(other);
		}
		if (sameTeam) {
			throw new IllegalArgumentException("같은 팀으로 이동했습니다.");
		}
	}

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

	public boolean isTurn(Location now, GameState gameState) {
		Piece startingPiece = board.get(now);
		return gameState.isTurn(startingPiece);
	}

	public Map<Location, Piece> getBoard() {
		return board;
	}
}
