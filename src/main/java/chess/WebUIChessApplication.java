package chess;

import chess.dto.ChessGameDTO;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        Spark.staticFileLocation("/public");

        get("/chessgame", WebUIChessApplication::chessGame);
    }

    private static String chessGame(Request request, Response response) throws SQLException {
        Map<String, Object> model = new HashMap<>();

        int id = ChessGameService.getInstance().getId(request.queryParams("id"));
        ChessGameDTO chessGameDTO = ChessGameService.getInstance().getGame(id);

        request.session().attribute("gameId", id);
        model.put("boardJson", new Gson().toJson(chessGameDTO.getBoard().getBoard()));
        model.put("turn", chessGameDTO.getTurn());
        return render(model, "chessgame.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
