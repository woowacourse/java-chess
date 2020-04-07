package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDao;
import chess.domain.piece.position.Position;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessController {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final ChessBoard chessBoard = new ChessBoard();
    private static final PieceDao pieceDao = new PieceDao();
    private static final String NONE_PIECE_NAME = "";
    private static final HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();

    private static String findPieceType(final Position position) {
        Optional<Piece> piece = chessBoard.findPieceByPosition(position);
        return piece.map(Piece::getPieceName).orElse(NONE_PIECE_NAME);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/init", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            pieceDao.deleteAll();
            List<Piece> pieces = chessBoard.getPieces();
            for (Piece piece : pieces) {
                pieceDao.addPiece(piece);

                model.put(piece.getPosition().toString(), piece.getPieceName());
            }

            return gson.toJson(model);
        });

        get("/continue", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Map<String, Object>> pieces = pieceDao.readPieces();
            for (Map pieceMap : pieces) {
                String xPosition = String.valueOf(pieceMap.get("xPosition"));
                String yPosition = String.valueOf(pieceMap.get("yPosition"));
                model.put(xPosition + yPosition, pieceMap.get("name"));
            }
            return gson.toJson(model);
        });


        post("/isMovable", (req, res) -> {
            String source = req.queryParams("sourcePosition");
            String target = req.queryParams("targetPosition");

            Position sourcePosition = Position.of(source);
            Position targetPosition = Position.of(target);

            String sourcePieceType = findPieceType(sourcePosition);
            String targetPieceType = findPieceType(targetPosition);
            boolean isAttack = chessBoard.isPieceInPosition(targetPosition);

            Map<String, Object> model = new HashMap<>();
            model.put("source", source);
            model.put("target", target);
            model.put("sourcePieceType", sourcePieceType);
            model.put("isAttack", isAttack);
            model.put("targetPieceType", targetPieceType);

            chessBoard.movePiece(sourcePosition, targetPosition);

            if (isAttack) {
                pieceDao.deletePiece(targetPosition);
            }
            pieceDao.updatePiece(sourcePosition, targetPosition);

            return gson.toJson(model);
        });
    }
}
