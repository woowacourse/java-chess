package chess;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.board.Board;
import domain.board.BoardFactory;
import domain.board.InvalidTurnException;
import domain.piece.position.InvalidPositionException;
import domain.piece.team.Team;
import service.WebService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");
        WebService webService = new WebService();
        Board board = BoardFactory.create();
        List<ErrorMessage> errorMessage = new ArrayList<>();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blackTeamScore", 0);
            model.put("whiteTeamScore", 0);
            model.put("turn", "게임 시작을 눌러주세요!");
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            List<String> pieces = board.showAllPieces();
            Map<String, Object> model = new HashMap<>();
            model.put("pieces", pieces);
            model.put("blackTeamScore", board.calculateTeamScore(Team.BLACK));
            model.put("whiteTeamScore", board.calculateTeamScore(Team.WHITE));
            model.put("turn", webService.getTurn() + "팀의 차례입니다!");
            model.put("errorMessage", errorMessage);

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String source = req.queryParams("source");
            String target = req.queryParams("target");

            try {
                webService.move(board, source, target);
            } catch (InvalidTurnException | InvalidPositionException e) {
                errorMessage.add(new ErrorMessage(e.getMessage()));
            }

            if (board.isKingDead()) {
                model.put("winner", webService.findWinner(board));
                return render(model, "result.html");
            }

            List<String> pieces = board.showAllPieces();

            model.put("pieces", pieces);
            model.put("blackTeamScore", board.calculateTeamScore(Team.BLACK));
            model.put("whiteTeamScore", board.calculateTeamScore(Team.WHITE));
            model.put("turn", webService.getTurn());
            model.put("errorMessage", errorMessage);

            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
