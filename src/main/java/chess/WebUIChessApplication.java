package chess;

import chess.controller.web.dao.CommandDao;
import chess.controller.web.WebChessController;
import chess.controller.web.dto.ColorDto;
import chess.controller.web.dto.ErrorDto;
import chess.controller.web.dto.PieceDto;
import chess.controller.web.dto.PositionDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {

    public static void main(String[] args) {
        WebChessController chessController = new WebChessController();
        CommandDao commandDAO = new CommandDao();

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
                model.put("error", new ErrorDto("error" + e.getMessage()));
                return render(model, "index.html");
            }
            if (chessController.isFinished()) {
                return render(model, "end.html");
            }
            commandDAO.deleteAll();
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
                model.put("error", new ErrorDto(e.getMessage()));
                return render(model, "chessboard.html");
            }

            model = makeBoardModel(chessController);
            model.put("error", new ErrorDto(""));
            commandDAO.insert(command);
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

    private static Map<String, Object> makeBoardModel(WebChessController chessController) {
        Map<String, Object> model = new HashMap<>();
        Map<PositionDto, PieceDto> board = chessController.board()
                                                          .getMaps();
        ColorDto currentPlayer = chessController.currentPlayer();
        for (PositionDto positionDTO : board.keySet()) {
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
