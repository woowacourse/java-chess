package chess.web.dao;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.chessGame.ChessCommand;

public class InMemoryChessHistoryDao implements ChessHistoryDao {

	private static final Map<Long, PathHistory> chessHistory = new HashMap<>();

	@Override
	public boolean isChessHistoryTableExist() {
		return true;
	}

	@Override
	public void createChessHistory() {
	}

	@Override
	public List<ChessCommand> findAll() {
		return chessHistory.values().stream()
			.map(PathHistory::generateMoveCommand)
			.collect(toList());
	}

	@Override
	public void addHistory(final String sourcePosition, final String targetPosition) {
		chessHistory.put((long)(chessHistory.size() + 1), new PathHistory(sourcePosition, targetPosition));
	}

	@Override
	public void deleteAll() {
		chessHistory.clear();
	}

	private static class PathHistory {

		private static final String MOVE_COMMAND = "move";

		private final String start;
		private final String end;

		private PathHistory(final String start, final String end) {
			this.start = start;
			this.end = end;
		}

		private ChessCommand generateMoveCommand() {
			return ChessCommand.of(Arrays.asList(
				MOVE_COMMAND,
				start,
				end));
		}

	}

}
