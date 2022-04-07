package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.GameResult;
import chess.domain.GameTurn;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.dto.BoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    public void run() {
        staticFiles.location("/static");
        port(8080);

        Map<String, Object> model = new HashMap<>();
        ChessGame chessGame = new ChessGame();

        get("/", (request, response) -> render(model, "/ready.html"));

        get("/savegame", ((request, response) -> render(model, "/savegame.html")));

        get("/findgame", ((request, response) -> {
            model.put("gameRoom", "어디로 들어가려구??");
            return render(model, "/findgame.html");
        }));

        post("/findgame", (request, response) -> {
            String gameID = request.queryParams("gameID");
            try {
                ChessGameDao chessGameDao = new ChessGameDao();
                chessGameDao.getConnection();
                chessGameDao.loadAndStartGame(gameID, chessGame);

                BoardDto boardDto = new BoardDto(chessGame.getBoard());
                model.putAll(boardDto.getBoard());

                model.put("gameID", gameID);
                model.put("message", "누가 이기나 보자구~!");
                if (chessGame.isKingDie()) {
                    model.put("message", "킹 잡았다!! 게임 끝~!~!");
                    return render(model, "/finished.html");
                }
                return render(model, "/ingame.html");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                model.put("gameRoom", e.getMessage());
                return render(model, "/findgame.html");
            }
        });

        get("/ingame", (request, response) -> {
            String gameID = request.queryParams("gameID");

            ChessGameDao chessGameDao = new ChessGameDao();
            chessGameDao.getConnection();
            chessGameDao.save(gameID, chessGame);

            chessGame.startGame(new InitialBoardGenerator(), GameTurn.WHITE);
            chessGameDao.updateTurn(gameID, chessGame);

            PieceDao pieceDao = new PieceDao();
            pieceDao.getConnection();
            pieceDao.deleteAll(gameID);
            pieceDao.insertPieces(gameID);

            BoardDto boardDto = new BoardDto(chessGame.getBoard());
            model.putAll(boardDto.getBoard());

            model.put("gameID", gameID);
            model.put("message", "누가 이기나 보자구~!");

            return render(model, "/ingame.html");
        });

        post("/ingame", (request, response) -> {
            String gameID = request.queryParams("gameID");
            String source = request.queryParams("source");
            String target = request.queryParams("target");

            try {
                chessGame.move(new Square(source), new Square(target));
                System.out.println("!!");
                PieceDao pieceDao = new PieceDao();
                pieceDao.deleteByPosition(new Square(target));
                pieceDao.updatePosition(new Square(source), new Square(target));

                ChessGameDao chessGameDao = new ChessGameDao();
                chessGameDao.getConnection();
                chessGameDao.updateTurn(gameID, chessGame);

                BoardDto boardDto = new BoardDto(chessGame.getBoard());
                model.putAll(boardDto.getBoard());

                model.put("gameID", gameID);
                model.put("message", "누가 이기나 보자구~!");
            } catch (IllegalArgumentException e) {
                model.put("message", e.getMessage());
            }
            if (chessGame.isKingDie()) {
                model.put("message", "킹 잡았다!! 게임 끝~!~!");
                return render(model, "/finished.html");
            }
            return render(model, "/ingame.html");
        });

        get("/status", (request, response) -> {
            String gameID = request.queryParams("gameID");
            GameResult gameResult = new GameResult(chessGame.getBoard());
            model.put("gameID", gameID);
            model.put("whiteScore", gameResult.calculateScore(Color.WHITE));
            model.put("blackScore", gameResult.calculateScore(Color.BLACK));
            model.put("message", "수고하셨습니다 ^0^");
            return render(model, "/status.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
