package chess;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.dao.ChessDAO;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.dto.ChessDTO;
import chess.factory.BoardFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final ChessBoard chessBoard = BoardFactory.createBoard();
	private static final ChessDAO chessDao = new ChessDAO();

	public static void main(String[] args) {
		staticFileLocation("/templates");
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/init", (req, res) -> {
			return getBoardJson();
		});

		post("/move", (req, res) -> {
			try {
				Position startPosition = Position.of(req.queryParams("startPosition"));
				Position targetPosition = Position.of(req.queryParams("targetPosition"));
				chessBoard.move(startPosition, targetPosition);
				return getBoardJson();
			} catch (RuntimeException e) {
				return e.getMessage();
			}
		});

	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private static String getBoardJson() throws SQLException {
		ChessDTO chessDto = chessDao.find();
		if (Objects.isNull(chessDto)) {

		}
		Map<String, Object> model = new HashMap<>();
		for (ChessPiece chessPiece : chessBoard.findAll()) {
			Position position = chessPiece.getPosition();
			model.put(position.toString(), chessPiece);
		}
		return gson.toJson(model);
	}
}
