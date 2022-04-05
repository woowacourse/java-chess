package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.state.Ready;
import chess.dto.GameDto;
import chess.dto.StatusDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

	private final ChessService chessService;

	public WebChessController() {
		this.chessService = new ChessService(new Ready());
	}

	public void run() {
		Gson gson = new Gson();
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		post("/play", (req, res) -> {
			GameDto gameDto = chessService.playCommand(parseCommand(req));
			Map<String, Object> model = gameDto.getBoard();
			return render(model, "chess.html");
		});

		get("/status", (req, res) -> {
			StatusDto statusDto = chessService.status();
			return gson.toJson(statusDto);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private List<String> parseCommand(Request request) {
		String requestCommand = request.queryParams("command");
		return List.of(requestCommand.split(" "));
	}
}
