package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.game.dao.GameDAO;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.service.GameService;
import chess.view.dto.PositionRequestDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(8080);
		staticFiles.location("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/chess/start", (req, res) -> {
			GameService service = new GameService(new GameDAO());
			service.startNewGame();
			return new Gson().toJson(service.getCurrentState());
		});

		get("/chess/end", (req, res) -> {
			GameService service = new GameService(new GameDAO());
			service.endGame();
			return new Gson().toJson(service.getCurrentState());
		});

		get("/chess/state", (req, res) -> {
			GameService service = new GameService(new GameDAO());
			return new Gson().toJson(service.getCurrentState());
		});

		get("/chess/pieces", (req, res) -> {
			GameService service = new GameService(new GameDAO());
			return new Gson().toJson(service.findAllPiecesOnBoard());
		});

		get("/chess/record", (req, res) ->
			new Gson().toJson(new GameService(new GameDAO()).calculateScore())
		);

		post("/chess/move", (req, res) -> {
			GameService service = new GameService(new GameDAO());
			PositionRequestDTO request = new Gson().fromJson(req.body(), PositionRequestDTO.class);
			service.move(Position.of(request.getFrom()), Position.of(request.getTo()));

			return new Gson().toJson(
				service.findChangedPiecesOnBoard(Position.of(request.getFrom()), Position.of(request.getTo())));
		});

		get("/chess/game/isnotfinish", (req, res) -> new GameService(new GameDAO()).isNotFinish());

		get("/chess/result/winner", (req, res) -> new GameService(new GameDAO()).getWinner());
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
