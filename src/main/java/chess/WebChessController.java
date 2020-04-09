package chess;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.dto.ChessPositionDTO;
import chess.service.Service;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    private static final String MOVE_SUCCESS = "";

    private ChessBoard chessBoard;
    private Service service;

    public WebChessController() {
        staticFileLocation("templates");
        service = new Service();
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/chessStart", (req, res) -> {
            Map<String, Object> model;

            service.initialChessBoard();
            chessBoard = service.createInitialChessBoard();
            model = service.settingChessBoard(chessBoard);

            return render(model, "contents/chess.html");
        });

        get("/chess", (req, res) -> {
            Map<String, Object> model;

            chessBoard = service.createContinuousChessBoard();
            model = service.settingChessBoard(chessBoard);

            return render(model, "contents/chess.html");
        });


        post("/move", (req, res) -> {
             Map<String, Object> model = new HashMap<>();
            ChessPositionDTO chessPositionDTO =
                    new ChessPositionDTO(req.queryParams("source"), req.queryParams("target"));

            try {
                service.moveChessBoard(chessBoard, chessPositionDTO);
                chessBoard.playerTurnChange();
                service.updatePlayerTurn(chessBoard);
                if (chessBoard.isCaughtKing()) {
                    return chessBoard.getPlayerColor().getColor() + "이 승리했습니다!";
                }
                return MOVE_SUCCESS;
            } catch (Exception e) {
                res.status(403);
                return e.getMessage();
            }

        });

        post("/status", (req, res) -> {
            PieceColor playerTurn = chessBoard.getPlayerColor();
            res.body(String.format("%s점수: %.1f", playerTurn.getColor(), chessBoard.calculateScore()));
            return res.body();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
