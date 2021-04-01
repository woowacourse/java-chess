package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.Chess;
import chess.domain.board.BoardDTO;
import chess.domain.position.MovePosition;
import chess.domain.position.MovePositionDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebUIChessApplication.class);

    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");

        Map<Integer, Chess> chessMap = new HashMap<>();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/new", (req, res) -> {
            Chess chess = Chess.createWithEmptyBoard().start();
            chessMap.put(0, chess);
            res.redirect("/rooms/0");
            return "OK";
        });

        get("/rooms/:roomId", (req, res) -> {
            int roomId = Integer.parseInt(req.params(":roomId"));
            Chess chess = chessMap.get(roomId);
            Set<Map.Entry<String, String>> board = BoardDTO.from(chess).getPieceDTOs();
            Map<String, Object> model = new HashMap<>();
            model.put("board", board);
            return render(model, "chess.html");
        });

        post("/rooms/:roomId/move", (req, res) -> {
            int roomId = Integer.parseInt(req.params(":roomId"));
            Chess chess = chessMap.get(roomId);

            MovePositionDTO movePositionDTO = GSON.fromJson(req.body(), MovePositionDTO.class);
            MovePosition movePosition = new MovePosition(movePositionDTO.getSource(), movePositionDTO.getTarget());
            chess = chess.move(movePosition);
            chessMap.put(roomId, chess);

            if (chess.isKindDead()) {
                return GSON.toJson("king-dead");
            }
            return GSON.toJson(movePosition);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
