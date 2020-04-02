package chess;

import chess.dao.FakeBoardDAO;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;
import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = generateBlankModel();
            return render(model, "index.html");
        });

        post("/", (req, res) -> {
            Board board = new Board(FakeBoardDAO.initialFakeBoardDAO());
            Map<String, Object> model = parseBoard(board);
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static Map<String, Object> generateBlankModel() {
        return Position.getAllPositions()
                .stream()
                .collect(toMap(Position::toString, position -> "blank"));
    }

    private static Map<String, Object> parseBoard(Board board) throws SQLException {
        Map<String, Object> output = new HashMap<>();

        for (Position position : Position.getAllPositions()) {
            Optional<Piece> piece = board.findPieceOn(position);
            output.put(position.toString(), parsePiece(piece));
        }

        return output;
    }

    private static String parsePiece(Optional<Piece> piece) {
        if (piece.isPresent()) {
            return piece.get().toString();
        }

        return "blank";
    }
}
