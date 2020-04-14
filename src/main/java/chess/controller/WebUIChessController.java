package chess.controller;

import java.util.HashMap;
import java.util.Map;

import chess.dao.BoardDao;
import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessController {
    private BoardDao boardDao = new BoardDao();

    private static Map<String, Object> allocatePiecesOnMap(Board board) {
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

    public Route getNewChessGameRoute() {
        return (req, res) -> {
            boardDao.save(new Board());
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess-before-start.html");
        };
    }

    public Route getChessGameRoute() {
        return (req, res) -> {
            Board board = boardDao.find();
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
            Board board = boardDao.find();
            Map<String, Object> model = allocatePiecesOnMap(board);
            model.put("winner", board.getWinner().getName());
            return render(model, "chess-result.html");
        };
    }

    public Route getExceptionRoute() {
        return (req, res) -> {
            Board board = boardDao.find();
            Map<String, Object> model = allocatePiecesOnMap(board);
            return render(model, "chess-exception.html");
        };
    }

    public Route getMoveRoute() {
        return (req, res) -> {
            Board board = boardDao.find();
            String source = req.queryParams("source");
            String destination = req.queryParams("destination");
            try {
                board.movePiece(new Position(source), new Position(destination));
                Pieces pieces = board.getPieces();
                Piece destinationPiece = pieces.findByPosition(new Position(destination));
                if (destinationPiece == null) {
                    boardDao.editPiece(source, destination);
                }
                if (destinationPiece != null) {
                    boardDao.removePiece(destination);
                    boardDao.editPiece(source, destination);
                }
                res.redirect("/");
            } catch (Exception e) {
                res.redirect("/exception");
            }
            return null;
        };
    }

    public Route getInitializeRoute() {
        return (req, res) -> {
            boardDao.save(new Board());
            res.redirect("/");
            return null;
        };
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
