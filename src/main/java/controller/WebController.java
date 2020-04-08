package controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.GameManager;
import chess.result.GameStatistic;
import service.ChessService;
import spark.Request;

public class WebController {
	private final ChessService chessService = new ChessService();

	public Object winner(Request request) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		long roomID = Long.parseLong(request.params("roomID"));
		return chessService.findWinner(model, roomID);
	}

	public List<GameStatistic> status(Request request) throws SQLException {
		long roomID = Long.parseLong(request.params("roomID"));
		return chessService.createStatistics(roomID);
	}

	public GameManager resume(Request request) throws SQLException {
		long roomID = Long.parseLong(request.params("roomID"));
		return chessService.resume(roomID);
	}

	public GameManager start(Request request) throws SQLException {
		Long roomID = Long.valueOf(request.params("roomID"));
		return chessService.start(roomID);
	}

	public Object move(Request request) throws SQLException {
		Long roomID = Long.valueOf(request.params("roomID"));
		String now = request.queryParams("now");
		String destination = request.queryParams("destination");
		return chessService.move(roomID, now, destination);
	}

	public Map<String, Object> findRoom() throws SQLException {
		return chessService.findRoom();
	}

	public String createRoom() throws SQLException {
		return chessService.createRoom();
	}

	public boolean deleteRoom(Request request) throws SQLException {
		String roomID = request.params("roomID");
		return chessService.deleteRoom(roomID);
	}

	public Map<String, Object> detailRoom(Request req) {
		Map<String, Object> model = new HashMap<>();
		Long roomID = Long.valueOf(req.params("roomID"));
		model.put("roomID", roomID);
		return model;
	}
}
