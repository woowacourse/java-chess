package chess.controller;

import chess.domain.piece.Color;
import chess.dto.DestinationPositionDto;
import chess.dto.MovablePositionsDto;
import chess.dto.MoveStatusDto;
import chess.service.ChessWebService;
import chess.web.NormalStatus;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static chess.JsonTransformer.json;
import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {
	private ChessWebService chessWebService;

	public ChessWebController() {
		this.chessWebService = new ChessWebService();
	}

	public void route() {
		get("/", (req, res) -> start());

		get("/new", (req, res) -> startNewGame());

		get("/loading", (req, res) -> loadGame());

		get("/board", (req, res) -> chessWebService.setBoard(), json());

		post("/board", (req, res) -> postBoard(req));

		post("/start", (req, res) -> getMovablePositions(req), json());

		post("/end", (req, res) -> move(req), json());
	}

	private String start() {
		Map<String, Object> model = new HashMap<>();
		model.put("normalStatus", NormalStatus.YES.isNormalStatus());

		return render(model, "index.html");
	}

	private String startNewGame() throws SQLException {
		Map<String, Object> model = new HashMap<>();
		model.put("normalStatus", NormalStatus.YES.isNormalStatus());

		chessWebService.clearHistory();

		return render(model, "chess.html");
	}

	private String loadGame() {
		Map<String, Object> model = new HashMap<>();
		model.put("normalStatus", NormalStatus.YES.isNormalStatus());

		return render(model, "chess.html");
	}

	private String postBoard(Request req) {
		Map<String, Object> model = new HashMap<>();

		try {
			MoveStatusDto moveStatusDto = chessWebService.move(req.queryParams("start"), req.queryParams("end"));

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

	private Map<String, Object> getMovablePositions(Request req) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		try {
			MovablePositionsDto movablePositionsDto = chessWebService.findMovablePositions(req.queryParams("start"));

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

	private Map<String, Object> move(Request req) {
		Map<String, Object> model = new HashMap<>();

		DestinationPositionDto destinationPositionDto = chessWebService.chooseDestinationPosition(req.queryParams("end"));

		model.put("normalStatus", destinationPositionDto.getNormalStatus().isNormalStatus());
		model.put("position", destinationPositionDto.getPosition());

		return model;
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
