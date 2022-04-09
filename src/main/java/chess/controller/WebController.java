package chess.controller;

import java.util.HashMap;
import java.util.Map;

import chess.Service;
import chess.domain.ChessGame;
import chess.domain.GameResult;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.dto.BoardDto;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private final Service service;

    public WebController() {
        this.service = new Service();
    }

    public Route ready() {
        return (request, response) -> render(new HashMap<>(), "/ready.html");
    }

    public Route askGameID() {
        return (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("gameRoom", "어디로 들어가려구??");
            return render(model, "/findgame.html");
        };
    }

    public Route findGame() {
        return (request, response) -> {
            String gameID = request.queryParams("gameID");
            Map<String, Object> model = addGameID(gameID);

            ChessGame chessGame = getSavedGame(gameID);
            return searchGame(model, chessGame);
        };
    }

    private String searchGame(Map<String, Object> model, ChessGame chessGame) {
        try {
            addBoardStatus(model, chessGame);
            model.put("message", "누가 이기나 보자구~!");
            String url = getUrl(model, chessGame);
            return render(model, url);
        } catch (IllegalArgumentException e) {
            model.put("gameRoom", e.getMessage());
            return render(model, "/findgame.html");
        }
    }

    public Route runGame() {
        return (request, response) -> {
            String gameID = request.queryParams("gameID");
            Map<String, Object> model = addGameID(gameID);

            ChessGame chessGame = service.loadGame(gameID);
            service.startGame(gameID, chessGame);
            service.loadPieces(gameID);
            addBoardStatus(model, chessGame);
            model.put("message", "누가 이기나 보자구~!");

            return render(model, "/ingame.html");
        };
    }

    public Route movePiece() {
        return (request, response) -> {
            String gameID = request.queryParams("gameID");
            Map<String, Object> model = addGameID(gameID);

            String source = request.queryParams("source");
            String target = request.queryParams("target");

            ChessGame chessGame = getSavedGame(gameID);

            try {
                chessGame.move(new Square(source), new Square(target));
                service.movePiece(gameID, source, target);
                service.updateTurn(gameID, chessGame);

                addBoardStatus(model, chessGame);
                model.put("message", "누가 이기나 보자구~!");
            } catch (IllegalArgumentException e) {
                addBoardStatus(model, chessGame);
                model.put("message", e.getMessage());
            }
            String url = getUrl(model, chessGame);
            return render(model, url);
        };
    }

    private String getUrl(Map<String, Object> model, ChessGame chessGame) {
        if (chessGame.isKingDie()) {
            model.put("message", "킹 잡았다!! 게임 끝~!~!");
            return "/finished.html";
        }
        return "/ingame.html";
    }

    private ChessGame getSavedGame(String gameID) {
        ChessGame chessGame = service.loadSavedChessGame(gameID, service.getTurn(gameID));
        chessGame.startGame();
        return chessGame;
    }

    public Route showResult() {
        return (request, response) -> {
            String gameID = request.queryParams("gameID");
            Map<String, Object> model = addGameID(gameID);

            GameResult gameResult = service.getGameResult(gameID);
            model.put("whiteScore", gameResult.calculateScore(Color.WHITE));
            model.put("blackScore", gameResult.calculateScore(Color.BLACK));
            model.put("message", "수고하셨습니다 ^0^");

            return render(model, "/status.html");
        };
    }

    private void addBoardStatus(Map<String, Object> model, ChessGame chessGame) {
        BoardDto boardDto = new BoardDto(chessGame.getBoard());
        model.putAll(boardDto.getBoard());
    }

    public Map<String, Object> addGameID(String gameID) {
        Map<String, Object> model = new HashMap<>();
        model.put("gameID", gameID);
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
