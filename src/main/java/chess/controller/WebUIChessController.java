package chess.controller;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessController {
    private final ChessService service;

    public WebUIChessController(ChessService service) {
        this.service = service;
    }

    public Route getNewChessGameRoute() {
        return (req, res) -> {
            service.initBoard();
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess-before-start.html");
        };
    }

    public Route getChessGameRoute() {
        return (req, res) -> {
            Board board = service.getSavedBoard();
            Map<String, Object> model = allocatePiecesOnMap(board);
            model.put("teamWhiteScore", board.calculateScoreByTeam(Team.WHITE));
            model.put("teamBlackScore", board.calculateScoreByTeam(Team.BLACK));
            if (!board.isBothKingAlive()) {
                res.redirect("/result");
            }
            return render(model, "chess-running.html");
        };
    }

    public Route getResultRoute() {
        return (req, res) -> {
            Board board = service.getSavedBoard();
            Map<String, Object> model = allocatePiecesOnMap(board);
            model.put("winner", board.getWinner().getName());
            return render(model, "chess-result.html");
        };
    }

    public Route getExceptionRoute() {
        return (req, res) -> {
            Board board = service.getSavedBoard();
            Map<String, Object> model = allocatePiecesOnMap(board);
            return render(model, "chess-exception.html");
        };
    }

    public Route postMoveRoute() {
        return (req, res) -> {
            Board board = service.getSavedBoard();
            String source = req.queryParams("source");
            String destination = req.queryParams("destination");
            try {
                service.processMoveInput(board, source, destination);
                res.redirect("/");
            } catch (Exception e) {
                res.redirect("/exception");
            }
            return null;
        };
    }

    public Route postInitializeRoute() {
        return (req, res) -> {
            service.initBoard();
            res.redirect("/");
            return null;
        };
    }

    private Map<String, Object> allocatePiecesOnMap(Board board) {
        Map<String, Object> model = new HashMap<>();
        Pieces pieces = board.getPieces();
        Map<Position, Piece> positionPieceMap = pieces.getPieces();
        Map<String, Piece> pieceMap = new HashMap<>();
        for (Position position : positionPieceMap.keySet()) {
            pieceMap.put(position.toString(), positionPieceMap.get(position));
        }
        model.put("map", pieceMap);
        return model;
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
