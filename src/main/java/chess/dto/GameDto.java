package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameDto {

	private final int gameId;
	private final Map<String, String> board;

	private GameDto(final int gameId, final Map<String, String> board) {
		this.gameId = gameId;
		this.board = new HashMap<>(board);
	}

	public static GameDto of(int gameId, Board board) {
		Map<String, String> symbolBoard = board.getBoard().entrySet().stream()
				.filter(e -> !e.getValue().isBlank())
				.collect(Collectors.toMap(
						e -> e.getKey().convertPositionToString(),
						e -> toString(e.getValue())));
		return new GameDto(gameId, symbolBoard);
	}

	private static String toString(Piece piece) {
		return piece.getTeam() + "/" + piece.getTeam() + "-" + piece.convertToString() + ".svg";
	}

	public Map<String, Object> getBoard() {
		return new HashMap<>(board);
	}
}
