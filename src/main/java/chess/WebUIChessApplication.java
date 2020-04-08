package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.dao.BoardDao;
import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static BoardDao boardDao = new BoardDao();

    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/new", (req, res) -> {
            boardDao.save(new Board());
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess-before-start.html");
        });

        get("/", (req, res) -> {
            Board board = boardDao.find();
            Map<String, Object> model = new HashMap<>();
            Pieces pieces = board.getPieces();
            Map<Position, Piece> positionPieceMap = pieces.getPieces();
            Map<String, Piece> pieceMap = new HashMap<>();
            for (Position position : positionPieceMap.keySet()) {
                pieceMap.put(position.toString(), positionPieceMap.get(position));
            }
            model.put("map", pieceMap);
            double teamWhiteScore = board.calculateScoreByTeam(Team.WHITE);
            double teamBlackScore = board.calculateScoreByTeam(Team.BLACK);
            model.put("teamWhiteScore", teamWhiteScore);
            model.put("teamBlackScore", teamBlackScore);
            if (!board.isBothKingAlive())
                res.redirect("/result");
            return render(model, "chess-running.html");
        });

        get("/result", (req, res) -> {
            Board board = boardDao.find();
            Map<String, Object> model = new HashMap<>();
            Pieces pieces = board.getPieces();
            Map<Position, Piece> positionPieceMap = pieces.getPieces();
            Map<String, Piece> pieceMap = new HashMap<>();
            for (Position position : positionPieceMap.keySet()) {
                pieceMap.put(position.toString(), positionPieceMap.get(position));
            }
            model.put("map", pieceMap);
            model.put("winner", board.getWinner().getName());
            return render(model, "chess-result.html");
        });

        get("/exception", (req, res) -> {
            Board board = boardDao.find();
            Map<String, Object> model = new HashMap<>();
            Pieces pieces = board.getPieces();
            Map<Position, Piece> positionPieceMap = pieces.getPieces();
            Map<String, Piece> pieceMap = new HashMap<>();
            for (Position position : positionPieceMap.keySet()) {
                pieceMap.put(position.toString(), positionPieceMap.get(position));
            }
            model.put("map", pieceMap);
            return render(model, "chess-exception.html");
        });

        post("/move", (req, res) -> {
            Board board = boardDao.find();
            Map<String, Object> model = new HashMap<>();
            String source = req.queryParams("source");
            String destination = req.queryParams("destination");
            try {
                board.movePiece(new Position(source), new Position(destination));
                boardDao.save(board);
                res.redirect("/");
            } catch (Exception e) {
                res.redirect("/exception");
            }
            return null;
        });

        post("/initialize", (req, res) -> {
            boardDao.save(new Board());
            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
