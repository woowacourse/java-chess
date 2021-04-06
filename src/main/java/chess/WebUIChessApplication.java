package chess;

import chess.controller.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {

    public static void main(String[] args) {
        ChessControllerForUI chessController = new ChessControllerForUI();
        CommandDAO commandDAO = new CommandDAO();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                chessController.init();
                chessController.action(req.queryParams("start"));
            } catch (IllegalArgumentException e) {
                model.put("error", new ErrorDTO(e.getMessage()));
                return render(model, "index.html");
            }
            if (chessController.isFinished()) {
                return render(model, "end.html");
            }
            model = makeBoardModel(chessController);
            return render(model, "chessboard.html");
        });

        post("/chess", (req, res) -> {
            Map<String, Object> model;
            String command = req.queryParams("command");
            try {
                chessController.action(command);
            } catch (IllegalArgumentException e) {
                model = makeBoardModel(chessController);
                model.put("error", new ErrorDTO(e.getMessage()));
                return render(model, "chessboard.html");
            }

            model = makeBoardModel(chessController);
            return render(model, "chessboard.html");
        });

        get("/load", (req, res) -> {
            chessController.init();
            chessController.action("start");
            List<String> commands = commandDAO.selectAll();
            for (String command : commands) {
                chessController.action(command);
            }
            Map<String, Object> model = makeBoardModel(chessController);
            return render(model, "chessboard.html");
        });
    }

    private static Map<String, Object> makeBoardModel(ChessControllerForUI chessController) {
        Map<String, Object> model = new HashMap<>();
        Map<PositionDTO, PieceDTO> board = chessController.board()
                                                          .getMaps();
        ColorDTO currentPlayer = chessController.currentPlayer();
        for (PositionDTO positionDTO : board.keySet()) {
            model.put(positionDTO.getKey(), board.get(positionDTO));
        }
        model.put("scores", chessController.score());
        model.put("turn", currentPlayer);
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
