package chess.controller;

import static chess.util.HandlebarsUtil.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Color;
import chess.service.GameService;
import spark.Route;

public class GameController {
	public static final String BASIC_URL = "/game";
	public static final String MOVE_URL = BASIC_URL + "/move";
	public static final String STATUS_URL = BASIC_URL + "/status";
	public static final String END_URL = BASIC_URL + "/end";
	public static final String INIT_URL = BASIC_URL + "/init";
	public static final String LOAD_URL = BASIC_URL + "/load";
	public static Route movePiece = ((request, response) -> {
		Map<String, Object> model = new HashMap<>();
		GameService gameService = GameService.getInstance();
		int roomId = Integer.parseInt(request.queryParams("roomId"));
		String sourcePosition = request.queryParams("sourcePosition");
		String targetPosition = request.queryParams("targetPosition");
		gameService.movePiece(roomId, sourcePosition, targetPosition);

		model.put("pieces", gameService.getPiecesResponseDTO(roomId).getPieces());
		model.put("roomId", roomId);

		return render(model, "game.html");
	});
	public static Route showStatus = ((request, response) -> {
		Map<String, Object> model = new HashMap<>();
		GameService gameService = GameService.getInstance();
		int roomId = Integer.parseInt(request.queryParams("roomId"));

		double whiteScore = gameService.getScore(roomId, Color.WHITE);
		double blackScore = gameService.getScore(roomId, Color.BLACK);

		model.put("whiteScore", whiteScore);
		model.put("blackScore", blackScore);

		return render(model, "result.html");
	});
	public static Route endGame = (request, response) -> {
		Map<String, Object> model = new HashMap<>();

		return render(model, "result.html");
	};
	public static Route initGame = (request, response) -> {
		Map<String, Object> model = new HashMap<>();
		GameService gameService = GameService.getInstance();
		int roomId = Integer.parseInt(request.queryParams("roomId"));
		gameService.initialize(roomId);

		model.put("pieces", gameService.getPiecesResponseDTO(roomId).getPieces());

		model.put("roomId", roomId);
		// TODO : 얘가 왜 되는지 정확히 모르겠다. form 형태로 줬을 때만 가능함. 전체를 리로드하기 때문에 그런듯?
		return render(model, "game.html");
	};
	public static Route loadGame = (request, response) -> {
		Map<String, Object> model = new HashMap<>();
		GameService gameService = GameService.getInstance();
		int roomId = Integer.parseInt(request.queryParams("roomId"));

		model.put("pieces", gameService.getPiecesResponseDTO(roomId).getPieces());

		model.put("roomId", roomId);
		return render(model, "game.html");
	};
}
