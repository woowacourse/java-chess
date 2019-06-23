package chess.controller;

import chess.domain.Game;
import chess.domain.Piece;
import chess.domain.Square;
import chess.service.ChessService;
import chess.service.RoomService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessController {
	private final ChessService chessService;
	private final RoomService roomService;

	public ChessController(final ChessService chessService, final RoomService roomService) {
		this.chessService = chessService;
		this.roomService = roomService;
	}

	public Map<String, Object> initialize(Request req) {
		Map<String, Object> model = new HashMap<>();

		Game game = chessService.initGame();
		roomService.openRoom();
		long roomId = roomService.latestId();

		req.session().attribute("game", game);
		List<Square> squares = chessService.getSquares(game);

		model.put("currentColor", game.currentColor());
		model.put("board", squares);
		model.put("roomId", roomId);
		return model;
	}

	public Map<String, Object> show(final Request req) {
		Map<String, Object> model = new HashMap<>();
		Game game = req.session().attribute("game");

		long roomId = Long.parseLong(req.queryParams("roomId"));
		List<Square> squares = chessService.getSquares(game);

		model.put("board", squares);
		model.put("currentColor", game.currentColor());
		model.put("message", req.queryParams("message"));
		model.put("roomId", roomId);

		return model;
	}

	public Object action(Request req, Response res) {
		Game game = req.session().attribute("game");

		String origin = req.queryParams("origin");
		String target = req.queryParams("target");
		long roomId = Long.parseLong(req.queryParams("roomId"));

		chessService.action(game, origin, target, roomId);
		req.session().attribute("game", game);

		res.redirect("/chess/show?roomId=" + roomId);
		return null;
	}

	public Map<String, Object> end(final Request req) {
		Map<String, Object> model = new HashMap<>();
		long roomId = Long.parseLong(req.queryParams("roomId"));

		Game game = req.session().attribute("game");
		String winner = game.currentColor();

		roomService.updateStatus(roomId, winner);

		model.put("winner", winner);
		model.put("whiteScore", game.scoreOfColor(Piece.Color.WHITE));
		model.put("blackScore", game.scoreOfColor(Piece.Color.BLACK));
		return model;
	}

	public Object load(final Request req, final Response res) {
		long roomId = Long.parseLong(req.queryParams("roomId"));
		Game game = chessService.load(roomId);

		req.session().attribute("game", game);
		res.redirect("/chess/show?roomId=" + roomId);
		return null;
	}

	public Map<String, Object> score(final Request req) {
		Map<String, Object> model = new HashMap<>();
		Game game = req.session().attribute("game");

		model.put("whiteScore", game.scoreOfColor(Piece.Color.WHITE));
		model.put("blackScore", game.scoreOfColor(Piece.Color.BLACK));
		model.put("roomId", req.queryParams("roomId"));
		return model;
	}
}
