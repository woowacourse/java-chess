package chess.controller;

import static spark.Spark.*;

import java.util.Map;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.dto.BoardDTO;
import chess.domain.piece.Team;
import chess.domain.position.MoveInfo;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController implements ChessController {
	private static final Gson GSON = new Gson();

	private final ChessService service;
	private final Board board;
	private Team team;

	public WebChessController(ChessService service, Board board, Team team) {
		this.service = service;
		this.board = board;
		this.team = team;
	}

	@Override
	public void start() {
		get("/", this::renderBoard);
	}

	private String renderBoard(Request request, Response response) {
		return render(BoardDTO.of(board).getBoard(), "index.html");
	}

	private String render(Map<String, String> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	@Override
	public void playTurn() {
		post("/api/move", this::updateBoard);
		get("/status", this::toStatus);
	}

	private String toStatus(Request request, Response response) {
		return render(StatusDTO.of(Status.of(board)).getStatus(), "status.html");
	}

	private String updateBoard(Request request, Response response) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(request.body());

		String from = element.getAsJsonObject().get("from").getAsString();
		String to = element.getAsJsonObject().get("to").getAsString();

		try {
			if (board.isEnd()) {
				throw new IllegalArgumentException("게임 끝");
			}
			service.move(MoveInfo.of(from, to), team);
		} catch (Exception error) {
			response.status(500);
			return GSON.toJson(error.getMessage());
		}

		team = team.next();
		return GSON.toJson(from + " " + to);
	}
}
