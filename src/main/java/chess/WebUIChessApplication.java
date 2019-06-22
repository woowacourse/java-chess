package chess;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.controller.ChessController;
import chess.controller.MainController;
import chess.dao.CommandDao;
import chess.dao.RoomDao;
import chess.exception.ExitException;
import chess.exception.PositionException;
import chess.service.ChessService;
import chess.service.RoomService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import static spark.Spark.*;

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

        get("/", mainController::main);

        get("/chess/start", chessController::initialize);

        get("/chess", chessController::show);

        post("/chess", chessController::action);

        get("/end", chessController::end);

        get("/chess/score", chessController::score);

        get("/chess/load", chessController::load);

        exception(ExitException.class, (exception, req, res) -> {
            String roomId = req.queryParams("roomId");
            res.redirect("/end?roomId=" + roomId);
        });

        exception(PositionException.class, (exception, req, res) -> {
            String message = null;
            String roomId = req.queryParams("roomId");
            try {
                message = URLEncoder.encode(exception.getMessage(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            res.redirect("/chess?roomId=" + roomId + "&message=" + message);
        });
/*

        exception(Exception.class, (exception, req, res) -> {
            String message = null;
            String roomId = req.queryParams("roomId");
            try {
                message = URLEncoder.encode(exception.getMessage(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            res.redirect("/chess?roomId=" + roomId + "&message=" + message);
        });
*/

    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
