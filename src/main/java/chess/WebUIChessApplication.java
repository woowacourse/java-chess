package chess;

import chess.controller.ChessController;
import chess.controller.dao.ChessBoardDao;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.game.Command;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Gson gson = new Gson();
    private static final ChessController chessController = new ChessController();
    private static final ChessBoardDao chessBoardDao = new ChessBoardDao();

    public static void main(String[] args) {
        port(8081);
        staticFiles.location("templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/init", (req, res) -> {
            res.status(200);
//            ResponseDto responseDto = chessController.start(new RequestDto(Command.START));
            ResponseDto responseDto = chessBoardDao.loadGamePlaying();
            chessBoardDao.createInitGame(responseDto);
            return gson.toJson(responseDto);
        });

        get("/move", (req, res) -> {
            try {
                RequestDto requestDto = new RequestDto(Command.MOVE, req);
                ResponseDto responseDto = chessController.move(requestDto);
                res.status(200);
                chessBoardDao.pieceMove(responseDto);
                return gson.toJson(responseDto);
            } catch (IllegalArgumentException e) {
                res.status(400);
                return gson.toJson(e.getMessage());
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
