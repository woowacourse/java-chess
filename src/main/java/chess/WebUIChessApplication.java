package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.dao.ChessGameDao;
import chess.dao.GamesDao;
import chess.dao.MoveDao;
import chess.domain.Status;
import chess.domain.position.Position;
import chess.dto.ScoreDto;
import chess.dto.TurnDto;
import chess.dto.UnitDto;
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

		get("/games", (req, res) ->{
			GamesDao gamesDao = new GamesDao();
			return render(gamesDao.everyGames(), "gameList.html");
		});

		get("/new_game/:id", (req, res) -> {
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", req.params(":id"));
			return render(model, "playingGame.html");
		});

		post("/users", (req, res) -> {
			Map map = gson.fromJson(req.body(), Map.class);
			GamesDao gamesDao = new GamesDao();
			int gameId = gamesDao.createGame(String.valueOf(map.get("user1")), String.valueOf(map.get("user2")));
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", gameId);
			return gson.toJson(model);
		});

		get("/board/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGameDao gameDao = new ChessGameDao();
			ChessGame game = gameDao.findById(id);
			List<UnitDto> unitList = game.board().getBoard().values().stream()
				.map(piece -> new UnitDto(
					piece.getPosition().getColumn().intValue(),
					piece.getPosition().getRow().intValue(),
					piece.getTeam().name(),
					piece.getSymbol()))
				.collect(Collectors.toList());
			return gson.toJson(unitList);
		});

		get("/score/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGameDao gameDao = new ChessGameDao();
			ChessGame game = gameDao.findById(id);
			Status status = game.status();
			ScoreDto score = new ScoreDto(status.getBlackScore(), status.getWhiteScore());
			HashMap<String, Object> model = new HashMap<>();
			model.put("score", score);
			return gson.toJson(model);
		});

		get("/turn/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGameDao gameDao = new ChessGameDao();
			ChessGame game = gameDao.findById(id);
			TurnDto turnDto = new TurnDto(game.turn());
			HashMap<String, Object> model = new HashMap<>();
			model.put("turn", turnDto);
			return gson.toJson(model);
		});

		post("/move/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGameDao gameDao = new ChessGameDao();
			ChessGame game = gameDao.findById(id);

			Map<String, String> map = gson.fromJson(req.body(), Map.class);
			game.move(Position.of(Integer.parseInt(map.get("sourceX")), Integer.parseInt(map.get("sourceY"))),
				Position.of(Integer.parseInt(map.get("targetX")), Integer.parseInt(map.get("targetY"))));

			List<UnitDto> unitList = game.board().getBoard().values().stream()
				.map(piece -> new UnitDto(piece.getPosition().getColumn().intValue(),
					piece.getPosition().getRow().intValue(), piece.getTeam().name(), piece.getSymbol()))
				.collect(Collectors.toList());
			MoveDao moveDao = new MoveDao();

			int sourceX = Integer.parseInt(map.get("sourceX")) + 96;
			int targetX = Integer.parseInt(map.get("targetX")) + 96;
			moveDao.save((char)(sourceX) + map.get("sourceY"), (char)(targetX) + map.get("targetY"), id);
			return gson.toJson(unitList);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
