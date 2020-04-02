package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.exception.InvalidTurnException;
import chess.domain.game.state.Ready;
import chess.domain.piece.Position;
import chess.domain.piece.exception.NotMovableException;
import chess.dto.BoardDto;
import chess.dto.ChessGameDto;
import chess.dto.ResponseDto;
import chess.dto.StatusDto;
import chess.dto.TurnDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static final Map<String, ChessGame> CHESS_GAMES = new LinkedHashMap<>();
	private static final Gson GSON = new GsonBuilder().create();
	private static int nextId = 1;

	public static void main(String[] args) {
		Spark.staticFileLocation("assets");

		get("/games", (req, res) -> GSON.toJson(new ResponseDto(ResponseDto.SUCCESS, CHESS_GAMES.keySet())));

		get("/", (req, res) -> render(new HashMap<>(), "index.html"));

		get("/game/:id", (req, res) -> {
			if (CHESS_GAMES.containsKey(req.params(":id"))) {
				Map<String, Object> model = new HashMap<>();
				model.put("id", req.params(":id"));
				return render(model, "game.html");
			}
			return "<script>location.replace('/')</script>";
		});

		notFound("<script>location.replace('/')</script>");

		get("/board/:id", (req, res) -> {
			ChessGame chessGame = CHESS_GAMES.get(req.params(":id"));
			return responseChessGame(chessGame);
		});

		post("/move/:id", (req, res) -> {
			ChessGame chessGame = CHESS_GAMES.get(req.params(":id"));
			Map<String, Double> data = GSON.fromJson(req.body(), Map.class);
			Position source = Position.of(data.get("sx").intValue(), data.get("sy").intValue());
			Position target = Position.of(data.get("tx").intValue(), data.get("ty").intValue());
			try {
				chessGame.move(source, target);
			} catch (NotMovableException | IllegalArgumentException e) {
				return GSON.toJson(new ResponseDto(ResponseDto.FAIL, "이동할 수 없는 위치입니다."));
			} catch (InvalidTurnException e) {
				return GSON.toJson(new ResponseDto(ResponseDto.FAIL, chessGame.turn().getColor() + "의 턴입니다."));
			}
			return responseChessGame(chessGame);
		});

		post("/restart/:id", (req, res) -> {
			CHESS_GAMES.remove(req.params(":id"));
			return GSON.toJson(new ResponseDto(ResponseDto.SUCCESS, null));
		});

		post("/create", (req, res) -> {
			ChessGame chessGame = new ChessGame(new Ready());
			chessGame.start();
			CHESS_GAMES.put(String.valueOf(nextId), chessGame);
			return GSON.toJson(new ResponseDto(ResponseDto.SUCCESS, nextId++));
		});
	}

	private static String responseChessGame(ChessGame chessGame) {
		return GSON.toJson(
				new ResponseDto(ResponseDto.SUCCESS,
						new ChessGameDto(new BoardDto(chessGame.board()), new TurnDto(chessGame.turn()),
								new StatusDto(chessGame.status().getWhiteScore(), chessGame.status().getBlackScore(),
										chessGame.status().getWinner()), chessGame.isFinished())));
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
