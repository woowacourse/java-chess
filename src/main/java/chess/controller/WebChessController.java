package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.MoveOnCommand;
import chess.domain.command.StartOnCommand;
import chess.domain.dto.PiecesDto;
import chess.domain.dto.requestDto.MoveRequestDto;
import chess.view.OutputView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private final ChessGame chessGame;

    public WebChessController() {
        this.chessGame = new ChessGame();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        staticFiles.location("/static");
        Command startOnCommand = new StartOnCommand();
        String[] temp = new String[0];
        startOnCommand.execute(chessGame, temp);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "board.html");
        });

        get("/data", (req, res) -> {
            PiecesDto piecesDto = new PiecesDto(chessGame.getPiecesByAllPosition());
            String piecesDtoJson = gson.toJson(piecesDto);
            return piecesDtoJson;
        });

        post("/move", (req, res) -> {
            try {
                MoveRequestDto moveRequestDto = gson.fromJson(req.body(), MoveRequestDto.class);
                Command moveOnCommand = new MoveOnCommand();
                String[] sourceTarget = new String[]{"move", moveRequestDto.getSource(), moveRequestDto.getTarget()};
                moveOnCommand.execute(chessGame, sourceTarget);
                PiecesDto piecesDto = new PiecesDto(chessGame.getPiecesByAllPosition());
                String piecesDtoJson = gson.toJson(piecesDto);
                res.body(piecesDtoJson);
                OutputView.printChessBoard(chessGame.getPiecesByAllPosition());
                return piecesDtoJson;
            } catch (Exception e) {
                res.status(402);
                System.out.println(e.getMessage());
                res.body(e.getMessage());
                return res;
            }
        });
    }
}
