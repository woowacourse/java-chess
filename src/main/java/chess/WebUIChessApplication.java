package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.piece.Position;
import chess.dto.BoardDto;
import chess.dto.ChessGameDto;
import chess.dto.ScoreDto;
import chess.dto.StatusDto;
import chess.dto.TurnDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		Spark.staticFileLocation("templates");
		Map<String, ChessGame> chessGames = new HashMap<>();
		Gson gson = new GsonBuilder().create();

		get("/game/:id", (req, res) -> {
			ChessGame chessGame = new ChessGame(new Ready());
			chessGame.start();
			chessGames.putIfAbsent(req.params(":id"), chessGame);
			Map<String, Object> model = new HashMap<>();
			model.put("id", req.params(":id"));
			return render(model, "index.html");
		});

		get("/board/:id", (req, res) -> {
			ChessGame chessGame = chessGames.get(req.params(":id"));
			List<String> pieces = chessGame.board()
					.getBoard()
					.values()
					.stream()
					.map(piece -> piece.isBlack() ? piece.symbol().toUpperCase() : piece.symbol())
					.collect(Collectors.toList());
			return gson.toJson(new ChessGameDto(new BoardDto(pieces), new TurnDto(chessGame.turn()),
					new StatusDto(new ScoreDto(chessGame.status().getWhiteScore()),
							new ScoreDto(chessGame.status().getBlackScore())), chessGame.isFinished()));
		});

		post("/move/:id", (req, res) -> {
			ChessGame chessGame = chessGames.get(req.params(":id"));
			Map<String, Double> data = gson.fromJson(req.body(), Map.class);
			Position source = Position.of(data.get("sx").intValue(), data.get("sy").intValue());
			Position target = Position.of(data.get("tx").intValue(), data.get("ty").intValue());
			chessGame.move(source, target);
			List<String> pieces = chessGame.board()
					.getBoard()
					.values()
					.stream()
					.map(piece -> piece.isBlack() ? piece.symbol().toUpperCase() : piece.symbol())
					.collect(Collectors.toList());
			return gson.toJson(new ChessGameDto(new BoardDto(pieces), new TurnDto(chessGame.turn()),
					new StatusDto(new ScoreDto(chessGame.status().getWhiteScore()),
							new ScoreDto(chessGame.status().getBlackScore())), chessGame.isFinished()));
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
