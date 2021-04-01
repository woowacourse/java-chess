package chess.controller;

import chess.domain.BoardDTO;
import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.service.BoardService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class ChessController {
    public static final BoardFactory boardFactory = new BoardFactory();
    public final BoardService boardService = new BoardService();

    public void run() {
        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "chess.html");
        });

        get("/boardInfo", (req, res) -> {
            BoardDTO dto = boardService.initiateBoard(chessGame);
            return new Gson().toJson(dto);
        });

        get("/initiateBoard", (req, res) -> {
            BoardDTO dto = boardService.initiateBoard(chessGame);
            return new Gson().toJson(dto);
        });


//        post("/move", (req, res) -> {
//            MoveInfoDTO moveInfoDTO = new Gson().fromJson(req.body(), MoveInfoDTO.class);
//
//        });
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}