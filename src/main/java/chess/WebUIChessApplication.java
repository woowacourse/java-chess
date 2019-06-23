package chess;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.controller.ChessController;
import chess.controller.MainController;
import chess.dao.CommandDao;
import chess.dao.RoomDao;
import chess.exception.ExitException;
import chess.service.ChessService;
import chess.service.RoomService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

//TODO Squares DTO 고려
public class WebUIChessApplication {
	public static void main(String[] args) throws Exception {
		staticFiles.location("/static");

		DataSource dataSource = DataSource.getInstance();
		DbConnector dbConnector = new DbConnector(dataSource);
		TableCreator tableCreator = new TableCreator(dbConnector);
		tableCreator.create();

		CommandDao commandDao = CommandDao.from(dbConnector);
		RoomDao roomDao = RoomDao.from(dbConnector);

		ChessService chessService = new ChessService(commandDao, roomDao);
		RoomService roomService = new RoomService(roomDao);

		ChessController chessController = new ChessController(chessService, roomService);
		MainController mainController = new MainController(roomService);

		get("/", (req, res) -> render(mainController.processMain(), "main.html"));

		get("/chess/start", (req, res) ->
				render(chessController.initialize(req), "board.html")
		);

		get("/chess/show", (req, res) ->
				render(chessController.show(req), "board.html")
		);

		post("/chess/action", chessController::action);

		get("/end", (req, res) -> render(chessController.end(req), "end.html"));

		get("/chess/load", chessController::load);

		get("/chess/score", (req, res) ->
				render(chessController.score(req), "score.html")
		);

		exception(ExitException.class, (exception, req, res) -> {
			long roomId = Long.parseLong(req.queryParams("roomId"));
			res.redirect("/end?roomId=" + roomId);
		});

		exception(Exception.class, (exception, req, res) -> {
			String roomId = req.queryParams("roomId");
			res.redirect("/chess/show?roomId=" + roomId);
		});

	}

	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
