package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.Status;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;
import chess.domain.state.BoardRepository;
import chess.domain.state.Playing;
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
		ChessGame game = new ChessGame(new Playing(BoardRepository.create(), new Turn(Team.WHITE)));
		Gson gson = new GsonBuilder().create();

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			return render(new HashMap<>(), "initialBoard.html");
		});

		get("/board", (req, res) -> {
			List<UnitDto> unitList = game.board().getBoard().values().stream()
				.map(piece -> new UnitDto(piece.getPosition().getColumn().intValue(),
					piece.getPosition().getRow().intValue(), piece.getTeam().name(), piece.getSymbol()))
				.collect(Collectors.toList());
			return gson.toJson(unitList);
		});

		get("/score", (req, res) -> {
			Status status = game.status();
			ScoreDto score = new ScoreDto(status.getBlackScore(), status.getWhiteScore());
			return gson.toJson(score);
		});

		get("/turn", (req, res) -> {
			TurnDto turnDto = new TurnDto(game.turn());
			return gson.toJson(turnDto);
		});

		post("/move", (req, res) -> {
			Map<String, String> map = gson.fromJson(req.body(), Map.class);
			game.move(Position.of(Integer.parseInt(map.get("sourceX")), Integer.parseInt(map.get("sourceY"))),
				Position.of(Integer.parseInt(map.get("targetX")), Integer.parseInt(map.get("targetY"))));
			List<UnitDto> unitList = game.board().getBoard().values().stream()
				.map(piece -> new UnitDto(piece.getPosition().getColumn().intValue(),
					piece.getPosition().getRow().intValue(), piece.getTeam().name(), piece.getSymbol()))
				.collect(Collectors.toList());
			return gson.toJson(unitList);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
