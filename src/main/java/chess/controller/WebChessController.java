package chess.controller;

import static spark.Spark.*;

import java.sql.SQLException;
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
import spark.Request;
import spark.Response;
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

	public void route() {
		Spark.staticFileLocation("/templates");
		get("/", this::index);
		get("/init", this::init);
		get("/new_game/:id", this::createNewGame);
		get("/games", this::games);
		get("/turn/:id", this::turn);
		get("/board/:id", this::board);
		get("/score/:id", this::score);
		post("/move/:id", this::move);
		post("/users", this::users);
	}

	private String index(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		return render(model, "index.html");
	}

	private String init(Request request, Response response) {
		return render(new HashMap<>(), "userNames.html");
	}

	private String createNewGame(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();
		model.put("id", request.params(":id"));
		return render(model, "playingGame.html");
	}

	private String games(Request request, Response response) throws SQLException {
		return gson.toJson(gamesDao.everyGames());
	}

	private String turn(Request request, Response response) throws SQLException {
		int id = Integer.parseInt(request.params(":id"));
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
		TurnDto turnDto = new TurnDto(game.turn());

		HashMap<String, Object> model = new HashMap<>();
		model.put("turn", turnDto);
		return gson.toJson(model);
	}

	private String board(Request request, Response response) throws SQLException {
		int id = Integer.parseInt(request.params(":id"));
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
		UnitsDto units = new UnitsDto(game.board().getBoard());
		return gson.toJson(units);
	}

	private String score(Request request, Response response) throws SQLException {
		int id = Integer.parseInt(request.params(":id"));
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
		Status status = game.status();
		ScoreDto score = new ScoreDto(status.getBlackScore(), status.getWhiteScore());
		HashMap<String, Object> model = new HashMap<>();
		model.put("score", score);
		return gson.toJson(model);
	}

	private String move(Request request, Response response) throws SQLException {
		int id = Integer.parseInt(request.params(":id"));
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));

		Map<String, String> map = gson.fromJson(request.body(), Map.class);
		game.move(Position.of(Integer.parseInt(map.get("sourceX")), Integer.parseInt(map.get("sourceY"))),
			Position.of(Integer.parseInt(map.get("targetX")), Integer.parseInt(map.get("targetY"))));

		UnitsDto units = new UnitsDto(game.board().getBoard());

		int sourceX = Integer.parseInt(map.get("sourceX")) + 96;
		int targetX = Integer.parseInt(map.get("targetX")) + 96;
		moveDao.save((char)(sourceX) + map.get("sourceY"), (char)(targetX) + map.get("targetY"), id);
		return gson.toJson(units);
	}

	private String users(Request request, Response response) throws SQLException {
		Map req = gson.fromJson(request.body(), Map.class);
		int gameId = gamesDao.createGame(String.valueOf(req.get("user1")),
			String.valueOf(req.get("user2")));
		HashMap<String, Object> model = new HashMap<>();
		model.put("id", gameId);
		return gson.toJson(model);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

