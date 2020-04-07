package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.ChessGame;
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

public class WebChessController {
	private final Gson gson;
	private final MoveDao moveDao;
	private final GamesDao gamesDao;

	public WebChessController(MoveDao moveDao, GamesDao gamesDao) {
		this.gson = new GsonBuilder().create();
		this.moveDao = moveDao;
		this.gamesDao = gamesDao;
	}

	public void run() {
		Spark.staticFileLocation("/templates");
		getMapping(gson);
		postMapping(gson);
	}

	private void getMapping(Gson gson) {
		index();
		init();
		games(gson);
		createNewGame();
		board(gson);
		score(gson);
		turn(gson);
	}

	private void postMapping(Gson gson) {
		users(gson);
		move(gson);
	}

	private void move(Gson gson) {
		post("/move/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
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

	private void users(Gson gson) {
		post("/users", (req, res) -> {
			Map request = gson.fromJson(req.body(), Map.class);
			int gameId = gamesDao.createGame(String.valueOf(request.get("user1")),
				String.valueOf(request.get("user2")));
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", gameId);
			return gson.toJson(model);
		});
	}

	private void turn(Gson gson) {
		get("/turn/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
			TurnDto turnDto = new TurnDto(game.turn());

			HashMap<String, Object> model = new HashMap<>();
			model.put("turn", turnDto);
			return gson.toJson(model);
		});
	}

	private void score(Gson gson) {
		get("/score/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
			Status status = game.status();
			ScoreDto score = new ScoreDto(status.getBlackScore(), status.getWhiteScore());
			HashMap<String, Object> model = new HashMap<>();
			model.put("score", score);
			return gson.toJson(model);
		});
	}

	private void board(Gson gson) {
		get("/board/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
			UnitsDto units = new UnitsDto(game.board().getBoard());
			return gson.toJson(units);
		});
	}

	private void createNewGame() {
		get("/new_game/:id", (req, res) -> {
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", req.params(":id"));
			return render(model, "playingGame.html");
		});
	}

	private void games(Gson gson) {
		get("/games", (req, res) -> gson.toJson(gamesDao.everyGames()));
	}

	private void init() {
		get("/init", (req, res) -> render(new HashMap<>(), "userNames.html"));
	}

	private void index() {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

