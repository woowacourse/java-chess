package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.command.Ready;
import chess.domain.piece.PieceFactory;
import chess.domain.player.ChessGame;
import chess.domain.state.StateFactory;
import chess.service.ChessService;
import chess.service.dto.TilesDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class ChessWebController {
    private final ChessService chessService;

    public ChessWebController(final ChessService chessService) {
        this.chessService = chessService;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }


    public void run() {
        get("/", (req, res) -> {
            TilesDto tilesDto = new TilesDto(chessService.emptyBoard());
            Map<String, Object> model = new HashMap<>();
            model.put("tilesDto", tilesDto);
            return render(model, "board.html");
        });

        get("/start", (req, res) -> {
            ChessBoard chessBoard = ChessBoardFactory.initializeBoard();

            ChessGame chessGame = new ChessGame(StateFactory.initialization(PieceFactory.whitePieces()),
                    StateFactory.initialization(PieceFactory.blackPieces()), new Ready());

            TilesDto tilesDto = new TilesDto(chessGame.getBoard());
            Map<String, Object> model = new HashMap<>();
            model.put("tilesDto", tilesDto);
            return render(model, "board.html");
        });
    }
}
