package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.board.ChessBoard;
import chess.domain.command.Command;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.state.Ready;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.dto.ChessBoardResponse;
import web.dto.MoveReqeust;

public class WebApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");

        JsonTransformer jsonTransformer = new JsonTransformer();
        ChessGame chessGame = new ChessGame(new Ready(ChessBoard.createChessBoard()));
        chessGame.start();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/board", (req, res) -> {
            ChessBoardResponse chessBoardResponse = new ChessBoardResponse(chessGame);
            return chessBoardResponse.getResult();
        }, jsonTransformer);

        post("/move", (req, res) -> {
            MoveReqeust moveReqeust = jsonTransformer.getGson()
                .fromJson(req.body(), MoveReqeust.class);
            chessGame.execute(Command.from("move " + moveReqeust.from() + " " + moveReqeust.to()));
            return null;
        }, jsonTransformer);


        post("/score", (req, res) -> {
            return Score.from(chessGame.chessBoard());
        }, jsonTransformer);

        post("/turn", (req, res) -> {
            return chessGame.currentTurn();
        }, jsonTransformer);
    }



    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
