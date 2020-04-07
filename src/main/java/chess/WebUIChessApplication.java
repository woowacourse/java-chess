package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.dao.GamesDao;
import chess.dao.MoveDao;
import chess.domain.Status;
import chess.domain.position.Position;
import chess.dto.ScoreDto;
import chess.dto.TurnDto;
import chess.dto.UnitsDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		Spark.staticFileLocation("/templates");
		Gson gson = new GsonBuilder().create();

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			return render(new HashMap<>(), "userNames.html");
		});

		get("/games", (req, res) -> {
			GamesDao gamesDao = new GamesDao();
			return gson.toJson(gamesDao.everyGames());
		});

		get("/new_game/:id", (req, res) -> {
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", req.params(":id"));
			return render(model, "playingGame.html");
		});

		get("/board/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			MoveDao moveDao = new MoveDao();
			ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
			UnitsDto units = new UnitsDto(game.board().getBoard());
			return gson.toJson(units);
		});

		get("/score/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			MoveDao moveDao = new MoveDao();
			ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
			Status status = game.status();
			ScoreDto score = new ScoreDto(status.getBlackScore(), status.getWhiteScore());
			HashMap<String, Object> model = new HashMap<>();
			model.put("score", score);
			return gson.toJson(model);
		});

		get("/turn/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			MoveDao moveDao = new MoveDao();
			ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
			TurnDto turnDto = new TurnDto(game.turn());

			HashMap<String, Object> model = new HashMap<>();
			model.put("turn", turnDto);
			return gson.toJson(model);
		});


		post("/users", (req, res) -> {
			Map request = gson.fromJson(req.body(), Map.class);
			GamesDao gamesDao = new GamesDao();
			int gameId = gamesDao.createGame(String.valueOf(request.get("user1")), String.valueOf(request.get("user2")));
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", gameId);
			return gson.toJson(model);

		});

		post("/move/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			MoveDao moveDao = new MoveDao();
			ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));

			Map<String, String> map = gson.fromJson(req.body(), Map.class);
			game.move(Position.of(Integer.parseInt(map.get("sourceX")), Integer.parseInt(map.get("sourceY"))),
				Position.of(Integer.parseInt(map.get("targetX")), Integer.parseInt(map.get("targetY"))));

			UnitsDto units = new UnitsDto(game.board().getBoard());

			int sourceX = Integer.parseInt(map.get("sourceX")) + 96;
			int targetX = Integer.parseInt(map.get("targetX")) + 96;
			moveDao.save((char)(sourceX) + map.get("sourceY"), (char)(targetX) + map.get("targetY"), id);
			return gson.toJson(units);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
