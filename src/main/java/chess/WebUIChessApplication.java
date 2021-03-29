package chess;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import chess.domain.piece.TeamType;
import chess.dto.BoardDTO;
import chess.dto.RequestDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    private static TeamType TEAM_TYPE = TeamType.WHITE;

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/static");
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get("/chessboard", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Gson().toJsonTree(BoardDTO.from(chessBoard, TEAM_TYPE)));
        });
        post("/chessboard/move", (req, res) -> {
            RequestDTO requestDTO = new Gson().fromJson(req.body(), RequestDTO.class);
            res.type("application/json");
            Coordinate current = Coordinate.from(requestDTO.getCurrent());
            Coordinate destination = Coordinate.from(requestDTO.getDestination());
            TeamType teamType = TeamType.valueOf(requestDTO.getTeamType());
            chessBoard.move(current, destination, teamType);
            TEAM_TYPE = teamType.findOppositeTeam();
            BoardDTO boardDTO = BoardDTO.from(chessBoard, TEAM_TYPE);
            return new Gson().toJson(new Gson().toJsonTree(boardDTO));
        });
        exception(RuntimeException.class, (e, req, res) -> {
            res.status(500);
            res.type("application/json");
            res.body(e.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
