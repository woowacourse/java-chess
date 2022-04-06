package chess;

import chess.domain.board.ChessBoard;
import chess.domain.board.factory.BoardFactory;
import chess.domain.board.factory.RegularBoardFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.dto.response.BoardResponse;
import chess.turndecider.AlternatingGameFlow;
import chess.turndecider.GameFlow;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebApplication {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/static");

        BoardFactory boardFactory = RegularBoardFactory.getInstance();
        GameFlow gameFlow = new AlternatingGameFlow();
        ChessBoard chessBoard = new ChessBoard(boardFactory.create(), gameFlow);

        get("/", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        Map<Position, Piece> initBoard = RegularBoardFactory.getInstance().create();
        BoardResponse initBoardResponse = BoardResponse.from(initBoard);

        get("/board", "application/json", (req, res) -> initBoardResponse, gson::toJson);

        post("/move", (req, res) -> {
            Position from = Position.of(req.queryParams("from"));
            Position to = Position.of(req.queryParams("to"));
            chessBoard.movePiece(from, to);

            Map<Position, Piece> movedBoard = chessBoard.getBoard();
            return BoardResponse.from(movedBoard);

        }, gson::toJson);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
