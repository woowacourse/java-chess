package chess;

import chess.controller.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {
    public static void main(String[] args) {
        ChessControllerForUI chessController = new ChessControllerForUI();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/", (req,res) -> {
            Map<String, Object> model = new HashMap<>();

            if ("start".equals(req.queryParams("start"))) {
                chessController.init();
                chessController.action("start");

                model = makeBoardModel(chessController);
                return render(model, "chessboard.html");
            }
            return render(model, "index.html");
        });

        post("/chess", (req, res) -> {
            String command = req.queryParams("command");
            chessController.action(command);

            Map<String, Object> model = makeBoardModel(chessController);
            return render(model, "chessboard.html");
        });
    }

    private static Map<String, Object> makeBoardModel(ChessControllerForUI chessController) {
        Map<String, Object> model = new HashMap<>();
        Map<PositionDTO, PieceDTO> board = chessController.board().getBoard();
        ColorDTO currentPlayer = chessController.currentPlayer();
        for (PositionDTO positionDTO : board.keySet()) {
            model.put(positionDTO.getKey(), board.get(positionDTO));
        }
        model.put("team", currentPlayer);
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
    
}
