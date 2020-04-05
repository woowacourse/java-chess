package chess;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.db.dao.BoardDao;
import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecesManager;
import chess.domain.piece.WhitePiecesFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static Game game;
	private static Map<String, Object> model = new HashMap<>();
	private static BoardDao boardDao;

	public static void main(String[] args) {
		initialize();
		start();
		running();
		end();
		status();
	}

	private static void initialize() {
		get("/", (req, res) -> {
			init();
			return render(model, "index.html");
		});
	}

	private static void start() {
		get("/start", (req, res) -> {
			model = new HashMap<>();
			loadBoard();
			putBoardTo();
			return render(model, "start.html");
		});
	}

	private static void running() {
		get("/move", (req, res) -> {
			model = new HashMap<>();
			movePiece(req.queryParams("source"), req.queryParams("target"));
			if (game.isKingDie()) {
				res.redirect("/end");
			}
			putBoardTo();
			return render(model, "start.html");
		});
	}

	private static void end() {
		get("/end", (req, res) -> {
			model = new HashMap<>();
			createNewBoard();
			return render(model, "end.html");
		});
	}

	private static void status() {
		get("/status", (req, res) -> {
			model = new HashMap<>();
			model.put("whiteStatus", String.valueOf(game.status()[0]));
			model.put("blackStatus", String.valueOf(game.status()[1]));
			return render(model, "status.html");
		});
	}

	private static void createNewBoard() throws SQLException {
		init();
		for (File file : File.values()) {
			for (Rank rank : Rank.values()) {
				String position = file.getFile() + rank.getRow();
				Piece piece = game.getBoard().getBoard().get(Position.of(position));
				boardDao.updateBoard(position, piece);
			}
		}
	}

	private static void loadBoard() throws SQLException {
		for (File file : File.values()) {
			for (Rank rank : Rank.values()) {
				String position = file.getFile() + rank.getRow();
				Piece piece = boardDao.findPieceBy(position);
				game.getBoard().getBoard().put(Position.of(position), piece);
			}
		}
	}

	private static void putBoardTo() {
		game.getBoard().getBoard()
			.forEach((key, value) -> {
				if (value == null) {
					model.put(key.getPosition(), "");
					return;
				}
				model.put(key.getPosition(), value.getSymbol());
			});
	}

	private static void init() {
		game = new Game(new PiecesManager(WhitePiecesFactory.create(), BlackPiecesFactory.create()),
			new Board());
		boardDao = new BoardDao();
	}

	private static void movePiece(String source, String target) {
		try {
			game.movePieceFromTo(Position.of(source), Position.of(target));
			Piece piece = game.getBoard().getBoard().get(Position.of(target));
			boardDao.updateBoard(source, null);
			boardDao.updateBoard(target, piece);
		} catch (IllegalArgumentException | UnsupportedOperationException | SQLException e) {
			model.put("error", e.getMessage());
		}
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
