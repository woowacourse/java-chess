package chess.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.dao.ChessDAO;
import chess.domain.Team;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.dto.ChessDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;

public class ChessService {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private final ChessDAO chessDAO;
	private final ChessBoard chessBoard;

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
		return gson.toJson(model);
	}

	public String isEnd() {
		if (chessBoard.isLiveBothKing()) {
			return "notEnd";
		}
		if (chessBoard.isLiveKing(Team.BLACK)) {
			return "BLACK팀 승리!";
		}
		return "WHITE팀 승리!";
	}
}
