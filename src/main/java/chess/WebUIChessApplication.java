package chess;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.position.Position;
import chess.domain.state.StateFactory;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Command command = CommandFactory.initialCommand("start");
            Round round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                    StateFactory.initialization(PieceFactory.blackPieces()), command);
            Map<Position, Piece> chessBoard = round.getBoard();

            Map<String, String> filteredChessBoard = new HashMap<>();
            for (Map.Entry<Position, Piece> chessBoardStatus : chessBoard.entrySet()) {
                if (chessBoardStatus.getValue() != null) {
                    filteredChessBoard.put(chessBoardStatus.getKey().toString(), chessBoardStatus.getValue().getPiece());
                }
            }
            
            String jsonFormatChessBoard = GSON.toJson(filteredChessBoard);

            Map<String, Object> model = new HashMap<>();
            model.put("jsonFormatChessBoard", jsonFormatChessBoard);
            return render(model, "chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
