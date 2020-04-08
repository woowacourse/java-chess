package chess.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.dao.ChessDAO;
import chess.domain.Result;
import chess.domain.Status;
import chess.domain.Team;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.dto.ChessDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;

public class ChessService {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private final ChessDAO chessDAO;
	private ChessBoard chessBoard;

	public ChessService() throws SQLException {
		this.chessDAO = new ChessDAO();
		chessBoard = chessDAO.find();
	}

	public String move(Request req) throws SQLException {
		try {
			Position startPosition = Position.of(req.queryParams("startPosition"));
			Position targetPosition = Position.of(req.queryParams("targetPosition"));
			chessBoard.move(startPosition, targetPosition);
			chessDAO.update(new ChessDTO(chessBoard));
			return getBoardJson();
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}

	public String getBoardJson() {
		Map<String, Object> model = new HashMap<>();
		for (ChessPiece chessPiece : chessBoard.findAll()) {
			Position position = chessPiece.getPosition();
			model.put(position.toString(), chessPiece.getName());
		}
		return GSON.toJson(model);
	}

	public String isEnd() {
		Map<String, Object> model = new HashMap<>();
		if (chessBoard.isLiveBothKing()) {
			model.put("isEnd", false);
			return GSON.toJson(model);
		}
		model.put("isEnd", true);
		if (chessBoard.isLiveKing(Team.BLACK)) {
			model.put("message", "BLACK팀 승리!");
			return GSON.toJson(model);
		}
		model.put("message", "WHITE팀 승리!");
		return GSON.toJson(model);
	}

	public String restart() throws SQLException {
		chessDAO.remove(new ChessDTO(chessBoard));
		chessBoard = chessDAO.find();
		return getBoardJson();
	}

	public String status() {
		Status status = chessBoard.createStatus();
		Result result = status.getResult();
		Map<String, Object> model = new HashMap<>();
		model.put("blackTeamScore", result.getBlackTeamScore());
		model.put("whiteTeamScore", result.getWhiteTeamScore());
		return GSON.toJson(model);
	}
}
