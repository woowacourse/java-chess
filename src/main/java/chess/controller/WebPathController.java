package chess.controller;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessGame;
import chess.domain.GameResult;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.dto.BoardDto;
import chess.service.DBService;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebPathController {
    private static final String PATH_READY = "/ready.html";
    private static final String PATH_FIND_GAME = "/findgame.html";
    private static final String PATH_IN_GAME = "/ingame.html";
    private static final String PATH_FINISHED = "/finished.html";
    private static final String PATH_STATUS = "/status.html";

    private final DBService DBService;

    public WebPathController() {
        this.DBService = new DBService();
    }

    public Route ready() {
        return (request, response) -> render(new HashMap<>(), PATH_READY);
    }

    public Route askGameID() {
        return (request, response) -> render(Map.of("message", "어디로 들어가려구??"), PATH_FIND_GAME);
    }

    public Route findGame() {
        return (request, response) -> {
            String gameID = request.queryParams("gameID");
            Map<String, Object> model = addGameID(gameID);

            return searchGame(model, gameID);
        };
    }

    private String searchGame(Map<String, Object> model, String gameID) {
        try {
            ChessGame chessGame = getSavedGame(gameID);
            addBoardStatus(model, chessGame);

            String path = getPath(model, chessGame);
            return render(model, path);
        } catch (IllegalArgumentException e) {
            model.put("message", e.getMessage());
            return render(model, PATH_FIND_GAME);
        }
    }

    public Route runGame() {
        return (request, response) -> {
            String gameID = request.queryParams("gameID");
            Map<String, Object> model = addGameID(gameID);

            ChessGame chessGame = DBService.loadGame(gameID);
            DBService.startGame(gameID, chessGame);
            DBService.loadPieces(gameID);

            addBoardStatus(model, chessGame);

            return render(model, PATH_IN_GAME);
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
                DBService.movePiece(gameID, source, target);
                DBService.updateTurn(gameID, chessGame);

                addBoardStatus(model, chessGame);
            } catch (IllegalArgumentException e) {
                addBoardStatus(model, chessGame);
                model.put("message", e.getMessage());
            }
            String path = getPath(model, chessGame);
            return render(model, path);
        };
    }

    private String getPath(Map<String, Object> model, ChessGame chessGame) {
        if (chessGame.isKingDie()) {
            model.put("message", "킹 잡았다!! 게임 끝~!~!");
            return PATH_FINISHED;
        }
        return PATH_IN_GAME;
    }

    private ChessGame getSavedGame(String gameID) {
        ChessGame chessGame = DBService.loadSavedChessGame(gameID, DBService.getTurn(gameID));
        return chessGame;
    }

    public Route showResult() {
        return (request, response) -> {
            String gameID = request.queryParams("gameID");
            Map<String, Object> model = addGameID(gameID);

            GameResult gameResult = DBService.getGameResult(gameID);
            model.put("whiteScore", gameResult.calculateScore(Color.WHITE));
            model.put("blackScore", gameResult.calculateScore(Color.BLACK));
            model.put("message", "수고하셨습니다 ^0^");

            return render(model, PATH_STATUS);
        };
    }

    private void addBoardStatus(Map<String, Object> model, ChessGame chessGame) {
        BoardDto boardDto = new BoardDto(chessGame.getBoard());
        model.putAll(boardDto.getBoard());
        model.put("message", "누가 이기나 보자구~!");
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
