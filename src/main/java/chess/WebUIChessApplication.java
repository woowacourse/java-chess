package chess;

import chess.domains.board.Board;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.service.WebService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Board board = new Board();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Piece> pieces = board.showBoard();
            List<String> pieceCodes = convertView(pieces);
            model.put("pieces", pieceCodes);
            model.put("turn", printTurn(WebService.turn(board)));
            model.put("white_score", WebService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", WebService.calculateScore(board, PieceColor.BLACK));
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            WebService.move(board, source, target);
            List<Piece> pieces = board.showBoard();
            model.put("turn", printTurn(WebService.turn(board)));
            List<String> pieceCodes = convertView(pieces);
            model.put("pieces", pieceCodes);
            model.put("white_score", WebService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", WebService.calculateScore(board, PieceColor.BLACK));
            return render(model, "index.html");
        });
    }

    private static List<String> convertView(List<Piece> pieces) {
        List<String> pieceCodes = new ArrayList<>();
        for
        (Piece piece : pieces) {
            switch (piece.name()) {
                case "r":
                    pieceCodes.add("♖");
                    break;
                case "n":
                    pieceCodes.add("♘");
                    break;
                case "b":
                    pieceCodes.add("♗");
                    break;
                case "k":
                    pieceCodes.add("♔");
                    break;
                case "q":
                    pieceCodes.add("♕");
                    break;
                case "p":
                    pieceCodes.add("♙");
                    break;
                case "R":
                    pieceCodes.add("♜");
                    break;
                case "N":
                    pieceCodes.add("♞");
                    break;
                case "B":
                    pieceCodes.add("♝");
                    break;
                case "K":
                    pieceCodes.add("♚");
                    break;
                case "Q":
                    pieceCodes.add("♛");
                    break;
                case "P":
                    pieceCodes.add("♟");
                    break;
                case ".":
                    pieceCodes.add("");
                    break;
            }
        }
        return pieceCodes;
    }

    private static String printTurn(String turn) {
        return turn + "의 순서입니다.";
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
