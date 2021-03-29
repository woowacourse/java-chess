package chess;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.piece.TeamType;
import chess.dto.BoardDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    private static TeamType currentTeamType = TeamType.BLACK;

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/static");
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get("/main", (req, res) -> {
            res.type("application/json");
            currentTeamType = currentTeamType.findOppositeTeam();
            BoardDTO boardDTO = BoardDTO.from(chessBoard, currentTeamType);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "hi", new Gson().toJsonTree(boardDTO)));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
