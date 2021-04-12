package chess;

import chess.controller.web.WebChessController;
import chess.controller.web.dto.ErrorDto;
import chess.controller.web.dto.PieceDto;
import chess.controller.web.dto.PositionDto;
import chess.dao.CommandDao;
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

        get("/start", (req, res) -> {
            chessController.init();
            commandDAO.deleteAll();
            return renderChessBoard(chessController, "");
        });

        post("/chess", (req, res) -> {
            String command = req.queryParams("command");
            try {
                chessController.action(command);
            } catch (IllegalArgumentException e) {
                return renderChessBoard(chessController, e.getMessage());
            }
            commandDAO.insert(command);
            return renderChessBoard(chessController, "");
        });

        get("/load", (req, res) -> {
            chessController.init();
            List<String> commands = commandDAO.selectAll();
            for (String command : commands) {
                chessController.action(command);
            }
            return renderChessBoard(chessController, "");
        });
    }

    private static String renderChessBoard(WebChessController chessController, String errorMessage) {
        Map<String, Object> model;
        model = makeBoardModel(chessController);
        model.put("error", new ErrorDto(errorMessage));
        return render(model, "chessboard.html");
    }

    private static Map<String, Object> makeBoardModel(WebChessController chessController) {
        Map<String, Object> model = new HashMap<>();
        Map<PositionDto, PieceDto> board = chessController.board()
                                                          .getMaps();
        for (PositionDto positionDTO : board.keySet()) {
            model.put(positionDTO.getKey(), board.get(positionDTO));
        }
        model.put("scores", chessController.score());
        model.put("turn", chessController.currentPlayer());
        if (chessController.isFinished()) {
            model.put("winner", chessController.currentPlayer());
        }
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
