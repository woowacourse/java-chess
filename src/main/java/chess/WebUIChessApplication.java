package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.dao.ChessGameDao;
import chess.dao.GamesDto;
import chess.dao.MoveDto;
import chess.dao.RepositoryConnector;
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
			return render(new HashMap<>(), "initialBoard.html");
		});

		get("/new_game/:id", (req, res) -> {
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", req.params(":id"));
			return render(model, "playingGame.html");
		});

		post("/users", (req, res) -> {
			Map map = gson.fromJson(req.body(), Map.class);
			GamesDto gamesDto = new GamesDto(new RepositoryConnector());
			int gameId = gamesDto.createGame(String.valueOf(map.get("user1")), String.valueOf(map.get("user2")));
			HashMap<String, Object> model = new HashMap<>();
			model.put("id", gameId);
			return gson.toJson(model);
		});

		get("/board/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGameDao gameDao = new ChessGameDao(new RepositoryConnector());
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
			ChessGameDao gameDao = new ChessGameDao(new RepositoryConnector());
			ChessGame game = gameDao.findById(id);
			Status status = game.status();
			ScoreDto score = new ScoreDto(status.getBlackScore(), status.getWhiteScore());
			return gson.toJson(score);
		});

		get("/turn/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGameDao gameDao = new ChessGameDao(new RepositoryConnector());
			ChessGame game = gameDao.findById(id);
			TurnDto turnDto = new TurnDto(game.turn());
			return gson.toJson(turnDto);
		});

		post("/move/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			ChessGameDao gameDao = new ChessGameDao(new RepositoryConnector());
			ChessGame game = gameDao.findById(id);

			Map<String, String> map = gson.fromJson(req.body(), Map.class);
			game.move(Position.of(Integer.parseInt(map.get("sourceX")), Integer.parseInt(map.get("sourceY"))),
				Position.of(Integer.parseInt(map.get("targetX")), Integer.parseInt(map.get("targetY"))));

			List<UnitDto> unitList = game.board().getBoard().values().stream()
				.map(piece -> new UnitDto(piece.getPosition().getColumn().intValue(),
					piece.getPosition().getRow().intValue(), piece.getTeam().name(), piece.getSymbol()))
				.collect(Collectors.toList());
			MoveDto moveDto = new MoveDto(new RepositoryConnector());
			int sourceX = Integer.parseInt(map.get("sourceX")) + 96;
			int targetX = Integer.parseInt(map.get("targetX")) + 96;
			String sourceY = (char)(sourceX) + map.get("sourceY");
			String targetY = (char)(targetX) + map.get("targetY");
			System.out.println(sourceY);
			System.out.println(targetY);
			moveDto.save(sourceY, targetY, id);
			return gson.toJson(unitList);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
