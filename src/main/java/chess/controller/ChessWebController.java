package chess.controller;

import chess.domain.game.NormalStatus;
import chess.domain.piece.Color;
import chess.domain.position.MovingPosition;
import chess.dto.DestinationPositionDto;
import chess.dto.MovablePositionsDto;
import chess.dto.MoveStatusDto;
import chess.service.ChessWebService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static chess.web.JsonTransformer.json;
import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {
	private ChessWebService chessWebService;

	public ChessWebController() {
		this.chessWebService = new ChessWebService();
	}

	public void route() {
		get("/", this::index);

		get("/new", this::startNewGame);

		get("/loading", this::loadGame);

		get("/board", (req, res) -> chessWebService.setBoard(), json());

		post("/board", this::postBoard);

		post("/source", this::getMovablePositions, json());

		post("/destination", this::move, json());
	}

	private String index(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		model.put("normalStatus", NormalStatus.YES.isNormalStatus());

		return render(model, "index.html");
	}

	private String startNewGame(Request req, Response res) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		model.put("normalStatus", NormalStatus.YES.isNormalStatus());

		chessWebService.clearHistory();

		return render(model, "chess.html");
	}

	private String loadGame(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		model.put("normalStatus", NormalStatus.YES.isNormalStatus());

		return render(model, "chess.html");
	}

	private String postBoard(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();

		try {
			MoveStatusDto moveStatusDto = chessWebService.move(new MovingPosition(req.queryParams("source"), req.queryParams("destination")));

			model.put("normalStatus", moveStatusDto.getNormalStatus());
			model.put("winner", moveStatusDto.getWinner());

			if (moveStatusDto.getWinner().isNone()) {
				return render(model, "chess.html");
			}
			return render(model, "result.html");
		} catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException | SQLException e) {
			model.put("normalStatus", NormalStatus.NO.isNormalStatus());
			model.put("exception", e.getMessage());
			model.put("winner", Color.NONE);
			return render(model, "chess.html");
		}
	}

	private Map<String, Object> getMovablePositions(Request req, Response res) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		try {
			MovablePositionsDto movablePositionsDto = chessWebService.findMovablePositions(req.queryParams("source"));

			model.put("movable", movablePositionsDto.getMovablePositionNames());
			model.put("position", movablePositionsDto.getPosition());
			model.put("normalStatus", NormalStatus.YES.isNormalStatus());

			return model;
		} catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException e) {
			model.put("normalStatus", NormalStatus.NO.isNormalStatus());
			model.put("exception", e.getMessage());

			return model;
		}
	}

	private Map<String, Object> move(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();

		DestinationPositionDto destinationPositionDto = chessWebService.chooseDestinationPosition(req.queryParams("destination"));

		model.put("normalStatus", destinationPositionDto.getNormalStatus().isNormalStatus());
		model.put("position", destinationPositionDto.getPosition());

		return model;
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
