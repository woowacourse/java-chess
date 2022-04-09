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
            Map<String, Object> model = new HashMap<>();
            String gameID = request.queryParams("gameID");
            ChessGame chessGame = service.getChessGame(gameID, service.getTurn(gameID));
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
        };
    }

    public Route runGame() {
        return (request, response) -> {
            String gameID = request.queryParams("gameID");
            ChessGame chessGame = service.loadGame(gameID);

            service.startGame(gameID, chessGame);
            service.loadPieces(gameID);

            BoardDto boardDto = new BoardDto(chessGame.getBoard());
            Map<String, Object> model = new HashMap<>(boardDto.getBoard());

            model.put("gameID", gameID);
            model.put("message", "누가 이기나 보자구~!");

            return render(model, "/ingame.html");
        };
    }

    public Route movePiece() {
        return (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String gameID = request.queryParams("gameID");
            String source = request.queryParams("source");
            String target = request.queryParams("target");

            ChessGame chessGame = service.getChessGame(gameID, service.getTurn(gameID));
            chessGame.startGame();

            try {
                chessGame.move(new Square(source), new Square(target));
                service.movePiece(gameID, source, target);
                service.updateTurn(gameID, chessGame);

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
        };
    }

    public Route showResult() {
        return (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String gameID = request.queryParams("gameID");

            GameResult gameResult = service.getGameResult(gameID);

            model.put("gameID", gameID);
            model.put("whiteScore", gameResult.calculateScore(Color.WHITE));
            model.put("blackScore", gameResult.calculateScore(Color.BLACK));
            model.put("message", "수고하셨습니다 ^0^");
            return render(model, "/status.html");
        };
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
