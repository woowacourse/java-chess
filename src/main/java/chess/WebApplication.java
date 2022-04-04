package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;
import static spark.Spark.staticFiles;

import chess.domain.board.position.Position;
import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import chess.dto.BoardDto;
import chess.dto.PieceDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        ChessGame chessGame = new ChessGame(new InitBoardStrategy());
        Map<String, Object> model = new HashMap<>();

//        get("/", (req, res) -> {
//
//            List<PieceDto> pieces = new BoardDto(chessGame.getBoard())
//                    .getBoardWeb();
//            Map<Team,Double> scores = chessGame.getScoreOfTeams();
//            model.put("pieces", pieces);
//            model.put("whiteScore", scores.get(Team.WHITE));
//            model.put("blackScore", scores.get(Team.BLACK));
//            return render(model, "chessGame.html");
//        });
//        post("/command", (req, res) -> {
//
//            List<PieceDto> pieces = new BoardDto(chessGame.getBoard())
//                    .getBoardWeb();
//            Map<Team,Double> scores = chessGame.getScoreOfTeams();
//            model.put("pieces", pieces);
//            model.put("whiteScore", scores.get(Team.WHITE));
//            model.put("blackScore", scores.get(Team.BLACK));
//            return render(model, "chessGame.html");
//        });


    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

//♝♞♟♜♛♚
//♗♘♙♖♕♔
