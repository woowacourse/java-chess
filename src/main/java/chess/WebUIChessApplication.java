package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;
import chess.domain.state.BoardRepository;
import chess.dto.UnitDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		Spark.staticFileLocation("/templates");
		Board board = BoardRepository.create();
		Gson gson = new GsonBuilder().create();
		Turn turn = new Turn(Team.WHITE);

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			return render(new HashMap<>(), "initialBoard.html");
		});

		get("/board", (req, res) -> {
			List<UnitDto> unitList = board.getBoard().values().stream()
				.map(piece -> new UnitDto(piece.getPosition().getColumn().getColumn(),
					piece.getPosition().getRow().getRow(), piece.getTeam().name(), piece.getSymbol()))
				.collect(Collectors.toList());
			return gson.toJson(unitList);
		});

		post("/move", (req, res) -> {
			Map<String, String> map = gson.fromJson(req.body(), Map.class);
			board.move(Position.of(Integer.parseInt(map.get("sourceX")), Integer.parseInt(map.get("sourceY"))),
				Position.of(Integer.parseInt(map.get("targetX")), Integer.parseInt(map.get("targetY"))), turn);
			List<UnitDto> unitList = board.getBoard().values().stream()
				.map(piece -> new UnitDto(piece.getPosition().getColumn().getColumn(),
					piece.getPosition().getRow().getRow(), piece.getTeam().name(), piece.getSymbol()))
				.collect(Collectors.toList());
			return gson.toJson(unitList);
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
