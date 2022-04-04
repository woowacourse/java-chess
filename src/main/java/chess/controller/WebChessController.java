package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.dto.GameDto;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

	private final ChessService chessService;

	public WebChessController() {
		this.chessService = new ChessService(new Ready());
	}

	public void run() {
		State state = new Ready();
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		post("/play", (req, res) -> {
			String requestCommand = req.queryParams("command");
			GameDto gameDto = chessService.playCommand(List.of(requestCommand));
			Map<String, Object> model = gameDto.getBoard();
			return render(model, "chess.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
