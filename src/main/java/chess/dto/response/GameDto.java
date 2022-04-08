package chess.dto.response;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameDto {

	private static final String IMAGE_ROOT_FORMAT = "%s/%s-%s.svg";

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
						e -> toImageRoot(e.getValue())));
		return new GameDto(gameId, symbolBoard);
	}

	private static String toImageRoot(Piece piece) {
		return String.format(IMAGE_ROOT_FORMAT, piece.getTeam(), piece.getTeam(), piece.convertToString());
	}

	public int getGameId() {
		return gameId;
	}

	public Map<String, String> getBoard() {
		return new HashMap<>(board);
	}
}
