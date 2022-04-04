package chess.web.controller;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import chess.web.dto.PieceDto;
import chess.web.dto.PiecesDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class BoardController {

    private final Board board;

    public BoardController(Board board) {
        this.board = board;
    }

    public Object printCurrentBoard(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        List<PieceDto> pieces = PiecesDto.of(board)
                .getValue();
        model.put("pieces", pieces);
        model.put("turnColor", board.getCurrentTurnTeamColor());
        return render(model, "board.html");
    }

    public String move(Request request, Response response) {
        chess.web.controller.Request requestBody = chess.web.controller.Request.of(request.body());
        Position from = Position.from(requestBody.get("from"));
        Position to = Position.from(requestBody.get("to"));
        board.movePiece(from, to);
        response.redirect("/chess");
        return "";
    }

    public String status(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("whiteTotalScore", board.getTotalPoint(TeamColor.WHITE));
        model.put("blackTotalScore", board.getTotalPoint(TeamColor.BLACK));
        return render(model, "status.html");
    }

    public String end(Request request, Response response) {
        Spark.stop();
        return "end";
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
