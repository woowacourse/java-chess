package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.GameResult;
import chess.domain.GameTurn;
import chess.domain.board.SavedBoardGenerator;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.dto.BoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    public void run() {
        staticFiles.location("/static");
        port(8080);

        ChessGameDao chessGameDao = new ChessGameDao();
        PieceDao pieceDao = new PieceDao();

        get("/", (request, response) -> render(new HashMap<>(), "/ready.html"));

        get("/findgame", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("gameRoom", "어디로 들어가려구??");
            return render(model, "/findgame.html");
        }));

        post("/findgame", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String gameID = request.queryParams("gameID");
            GameTurn gameTurn = GameTurn.find(chessGameDao.findTurnByID(gameID));
            ChessGame chessGame = new ChessGame(new SavedBoardGenerator(pieceDao.findByGameID(gameID)), gameTurn);
            chessGame.startGame();
            try {
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
            GameTurn gameTurn = GameTurn.find(chessGameDao.findTurnByID(gameID));
            ChessGame chessGame = new ChessGame(new SavedBoardGenerator(pieceDao.findByGameID(gameID)), gameTurn);
            chessGame.startGame();

            chessGameDao.save(gameID, chessGame);
            chessGameDao.updateTurn(gameID, chessGame);

            pieceDao.deleteAll(gameID);
            pieceDao.insertPieces(gameID);

            BoardDto boardDto = new BoardDto(chessGame.getBoard());
            Map<String, Object> model = new HashMap<>(boardDto.getBoard());

            model.put("gameID", gameID);
            model.put("message", "누가 이기나 보자구~!");

            return render(model, "/ingame.html");
        });

        post("/ingame", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String gameID = request.queryParams("gameID");
            String source = request.queryParams("source");
            String target = request.queryParams("target");

            GameTurn gameTurn = GameTurn.find(chessGameDao.findTurnByID(gameID));
            ChessGame chessGame = new ChessGame(new SavedBoardGenerator(pieceDao.findByGameID(gameID)), gameTurn);
            chessGame.startGame();

            try {
                chessGame.move(new Square(source), new Square(target));
                pieceDao.deleteByPosition(new Square(target));
                pieceDao.updatePosition(new Square(source), new Square(target));

                chessGameDao.updateTurn(gameID, chessGame);

                BoardDto boardDto = new BoardDto(chessGame.getBoard());
                model.putAll(boardDto.getBoard());
                model.put("gameID", gameID);
                model.put("message", "누가 이기나 보자구~!");
            } catch (IllegalArgumentException e) {
                BoardDto boardDto = new BoardDto(chessGame.getBoard());
                model.putAll(boardDto.getBoard());
                model.put("message", e.getMessage());
            }
            if (chessGame.isKingDie()) {
                model.put("message", "킹 잡았다!! 게임 끝~!~!");
                return render(model, "/finished.html");
            }
            return render(model, "/ingame.html");
        });

        get("/status", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String gameID = request.queryParams("gameID");

            GameTurn gameTurn = GameTurn.find(chessGameDao.findTurnByID(gameID));
            ChessGame chessGame = new ChessGame(new SavedBoardGenerator(pieceDao.findByGameID(gameID)), gameTurn);
            chessGame.startGame();

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
