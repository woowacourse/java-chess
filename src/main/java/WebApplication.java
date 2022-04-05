import chess.domain.state.BoardInitialize;
import chess.uitils.ViewUtil;
import chess.web.ChessController;
import org.apache.log4j.BasicConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("templates");
        BasicConfigurator.configure();
        ChessController chessController = new ChessController();
        get("/board", (req, res) -> ViewUtil.render(chessController.getInitialBoard(), "/contents/chessBoard.html"));

        post("/move", (req, res) -> {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(req.body());
            return chessController.move(jsonObject.get("source").toString(), jsonObject.get("destination").toString());
        });

        get("/status", (req, res) -> new JSONObject(chessController.getStatus()));
        post("/reset", (req, res) -> ViewUtil.render(chessController.resetBoard(), "/contents/chessBoard.html"));

        exception(Exception.class, (exception, request, response) -> {
            response.status(403);
            response.body(exception.getMessage());
        });
    }
}
