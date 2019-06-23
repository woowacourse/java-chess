package chess;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.controller.ChessController;
import chess.controller.ErrorController;
import chess.controller.MainController;
import chess.dao.CommandDao;
import chess.dao.RoomDao;
import chess.exception.DiedKingException;
import chess.exception.PositionIllegalArgumentException;
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

        ChessService chessService = new ChessService(commandDao);
        RoomService roomService = new RoomService(roomDao);

        ChessController chessController = new ChessController(chessService, roomService);
        MainController mainController = new MainController(roomService);
        ErrorController errorController = new ErrorController();

        get("/", mainController::main);

        get("/chess/start", chessController::initialize);

        get("/chess", chessController::show);

        post("/chess", chessController::action);

        get("/end", chessController::end);

        get("/chess/score", chessController::score);

        get("/chess/load", chessController::load);

        get("/error", errorController::exception);

        exception(DiedKingException.class, (exception, req, res) -> {
            String roomId = req.queryParams("roomId");
            res.redirect("/end?roomId=" + roomId);
        });

        exception(PositionIllegalArgumentException.class, (exception, req, res) -> {
            String roomId = req.queryParams("roomId");
            String message = encode(exception.getMessage());
            res.redirect("/chess?roomId=" + roomId + "&message=" + message);
        });

        exception(Exception.class, (exception, req, res) -> {
            String message = encode(exception.getMessage());
            res.redirect("/eeror?message=" + message);
        });

    }

    private static String encode(final String message2) {
        String message = null;
        try {
            message = URLEncoder.encode(message2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
