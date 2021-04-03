package chess;

import chess.controller.ChessController;
import chess.controller.WebChessController;
import chess.domain.board.Board;
import chess.domain.command.*;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.PiecesDto;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Board board = new Board(PieceFactory.createPieces());
        ChessGame chessGame = new ChessGame(board);

        Gson gson = new Gson();

        staticFiles.location("/static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            if(!chessGame.isReady()){
                return gson.toJson(new ChessBoardDto("false", new PiecesDto(board.getPieces()), "이미 게임이 실행되었습니다."));
           }
            chessGame.start();
            return gson.toJson(new ChessBoardDto("true", new PiecesDto(board.getPieces()), ""));
        });

        post("/move", (req, res) -> {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(req.body());

            try{
                chessGame.move(Position.changePosition(jsonObject.get("source").getAsString().split("")),
                        Position.changePosition(jsonObject.get("target").getAsString().split("")));
            }catch(Exception e){
                return gson.toJson(new ChessBoardDto("false", new PiecesDto(chessGame.getBoard().getPieces()), "이동할 수 없습니다."));
            }

            return gson.toJson(new ChessBoardDto("true", new PiecesDto(chessGame.getBoard().getPieces()),""));
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
