package chess.service;

import chess.dao.HistoryDao;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.domain.position.PositionFactory;
import chess.dto.*;
import chess.web.MovingPosition;
import chess.web.NormalStatus;

import java.sql.SQLException;
import java.util.List;

public class ChessWebService {
	public void clearHistory() throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		historyDao.clear();
	}

	public ChessGameDto setBoard() throws SQLException {
		ChessGame chessGame = new ChessGame();

		load(chessGame);

		return new ChessGameDto(new BoardDto(chessGame.getPieces()), chessGame.getTurn(), chessGame.calculateScore(), NormalStatus.YES.isNormalStatus());
	}

	private void load(ChessGame chessGame) throws SQLException {
		List<MovingPosition> histories = selectAllHistory();

		for (MovingPosition movingPosition : histories) {
			chessGame.move(PositionFactory.of(movingPosition.getStart()), PositionFactory.of(movingPosition.getEnd()));
		}
	}

	private List<MovingPosition> selectAllHistory() throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		return historyDao.selectAll();
	}

	public MoveStatusDto move(String start, String end) {
		try { // TODO: 2020/04/09 이거 꼭 필요?

			if (start.equals(end)) {
				return new MoveStatusDto(NormalStatus.YES);
			}

			ChessGame chessGame = new ChessGame();

			load(chessGame);
			chessGame.move(PositionFactory.of(start), PositionFactory.of(end));

			if (chessGame.isKingDead()) {
				MoveStatusDto moveStatusDto = new MoveStatusDto(NormalStatus.YES, chessGame.getAliveKingColor());
				clearHistory();
				return moveStatusDto;
			}

			insertHistory(start, end);

			return new MoveStatusDto(NormalStatus.YES);
		} catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException | SQLException e) {
			return new MoveStatusDto(NormalStatus.NO, e.getMessage(), Color.NONE);
		}
	}


	private void insertHistory(String start, String end) throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		historyDao.insert(start, end);
	}

	public MovablePositionsDto chooseFirstPosition(String position) {
		try {
			ChessGame chessGame = new ChessGame();
			load(chessGame);

			List<String> movablePositionNames = chessGame.findMovablePositionNames(position);

			return new MovablePositionsDto(movablePositionNames, position, NormalStatus.YES);
		} catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException | SQLException e) {
			return new MovablePositionsDto(e.getMessage());
		}
	}

	public MovableStatusDto chooseSecondPosition(String position) {
		return new MovableStatusDto(NormalStatus.YES, position);
	}
}
