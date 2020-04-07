package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.service.GameService;
import chess.view.dto.GameDTO;
import chess.view.dto.PositionRequestDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		Game game = new Game(new Ready(new Board()));
		port(8080);
		staticFiles.location("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/chess/start", (req, res) -> {
			GameService service = new GameService(game);
			service.startNewGame();
			return new Gson().toJson(GameDTO.of(game.getTurn(), game.getStateType()));
		});

		get("/chess/end", (req, res) -> {
			game.end();
			return new Gson().toJson(GameDTO.of(game.getTurn(), game.getStateType()));
		});

		get("/chess/state", (req, res) -> new Gson().toJson(GameDTO.of(game.getTurn(), game.getStateType())));

		get("/chess/pieces", (req, res) -> {
			GameService service = new GameService(game);
			return new Gson().toJson(service.findAllPiecesOnBoard());
		});

		get("/chess/record", (req, res) ->
			new Gson().toJson(new GameService(game).calculateScore())
		);

		post("/chess/move", (req, res) -> {
			GameService service = new GameService(game);
			PositionRequestDTO request = new Gson().fromJson(req.body(), PositionRequestDTO.class);
			service.move(Position.of(request.getFrom()), Position.of(request.getTo()));

			return new Gson().toJson(
				service.findChangedPiecesOnBoard(Position.of(request.getFrom()), Position.of(request.getTo())));
		});

		get("/chess/game/isnotfinish", (req, res) -> game.isNotEnd());

		get("/chess/result/winner", (req, res) -> game.findWinner().name().toLowerCase());
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
