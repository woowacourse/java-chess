package chess.controller;

import static spark.Spark.*;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.dto.BoardDTO;
import chess.domain.piece.Team;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController implements ChessController {
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
	public void playTurn(String input) {

	}
}
