package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static Board board = new Board();

	public static void main(String[] args) {
		staticFileLocation("/public");

		get("/new", (req, res) -> {
			board = new Board();
			Map<String, Object> model = new HashMap<>();
			return render(model, "chess-before-start.html");
		});

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			Pieces pieces = board.getPieces();
			Map<Position, Piece> positionPieceMap = pieces.getPieces();
			Map<String, Piece> pieceMap = new HashMap<>();
			for (Position position : positionPieceMap.keySet()) {
				pieceMap.put(position.toString(), positionPieceMap.get(position));
			}
			model.put("map", pieceMap);
			double teamWhiteScore = board.calculateScoreByTeam(Team.WHITE);
			double teamBlackScore = board.calculateScoreByTeam(Team.BLACK);
			model.put("teamWhiteScore", teamWhiteScore);
			model.put("teamBlackScore", teamBlackScore);
			if (!board.isBothKingAlive())
				res.redirect("/result");
			return render(model, "chess-running.html");
		});

		get("/result", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			Pieces pieces = board.getPieces();
			Map<Position, Piece> positionPieceMap = pieces.getPieces();
			Map<String, Piece> pieceMap = new HashMap<>();
			for (Position position : positionPieceMap.keySet()) {
				pieceMap.put(position.toString(), positionPieceMap.get(position));
			}
			model.put("map", pieceMap);
			model.put("winner", board.getWinner().getName());
			return render(model, "chess-result.html");
		});

		post("/move", (req, res) -> {
			String source = req.queryParams("source");
			String destination = req.queryParams("destination");

			board.movePiece(new Position(source), new Position(destination));
			res.redirect("/");
			return null;
		});

		post("/initialize", (req, res) -> {
			board = new Board();
			res.redirect("/");
			return null;
		});
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
