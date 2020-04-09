package chess.controller;

import chess.dto.MovablePositionsDto;
import chess.dto.MovableStatusDto;
import chess.dto.MoveStatusDto;
import chess.service.ChessWebService;
import chess.web.NormalStatus;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

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
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("normalStatus", NormalStatus.YES.isNormalStatus());

			return render(model, "index.html");
		});

		get("/new", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("normalStatus", NormalStatus.YES.isNormalStatus());

			chessWebService.clearHistory();

			return render(model, "chess.html");
		});

		get("/loading", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("normalStatus", NormalStatus.YES.isNormalStatus());

			return render(model, "chess.html");
		});

		get("/board", (req, res) -> chessWebService.setBoard(), json());

		post("/board", (req, res) -> {
			Map<String, Object> model = new HashMap<>();

			MoveStatusDto moveStatusDto = chessWebService.move(req.queryParams("start"), req.queryParams("end"));

			model.put("normalStatus", moveStatusDto.getNormalStatus().isNormalStatus());
			model.put("exception", moveStatusDto.getException());
			model.put("winner", moveStatusDto.getWinner());

			if (moveStatusDto.getWinner().isNone()) {
				return render(model, "chess.html");
			}

			return render(model, "result.html");
		});

		post("/start", (req, res) -> {
			Map<String, Object> model = new HashMap<>();

			MovablePositionsDto movablePositionsDto = chessWebService.chooseFirstPosition(req.queryParams("start"));

			model.put("movable", movablePositionsDto.getMovablePositionNames());
			model.put("position", movablePositionsDto.getPosition());
			model.put("normalStatus", movablePositionsDto.getNormalStatus().isNormalStatus());
			model.put("exception", movablePositionsDto.getException());

			return model;
		}, json());

		post("/end", (req, res) -> {
			Map<String, Object> model = new HashMap<>();

			MovableStatusDto movableStatusDto = chessWebService.chooseSecondPosition(req.queryParams("end"));

			model.put("normalStatus", movableStatusDto.getNormalStatus().isNormalStatus());
			model.put("position", movableStatusDto.getPosition());

			return model;
		}, json());
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
