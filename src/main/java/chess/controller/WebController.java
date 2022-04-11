package chess.controller;

import static chess.view.webview.Converter.convertToWebViewPiece;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.GameStateDaoImpl;
import chess.dao.PieceDaoImpl;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.service.ChessGameService;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    public void run() {
        ChessGameService chessGameService = new ChessGameService(
                PieceDaoImpl.getInstance(), GameStateDaoImpl.getInstance());
        ready(chessGameService);
        start(chessGameService);
        move(chessGameService);
        status(chessGameService);
        end(chessGameService);
    }

    private void ready(final ChessGameService chessGameService) {
        get("/", (req, res) -> {
            final Map<Position, Piece> pieces = chessGameService.getPieces();
            return render(convertToWebViewPiece(pieces));
        });
    }

    private void start(final ChessGameService chessGameService) {
        get("/start", (req, res) -> {
            final Map<Position, Piece> pieces = chessGameService.start();
            return render(convertToWebViewPiece(pieces));
        });
    }

    private void move(final ChessGameService chessGameService) {
        post("/move", ((req, res) -> {
            final Map<Position, Piece> pieces = chessGameService
                    .move(req.queryParams("source"), req.queryParams("target"));
            return render(convertToWebViewPiece(pieces));
        }));
    }

    private void status(final ChessGameService chessGameService) {
        final JsonTransformer jsonTransformer = new JsonTransformer();
        get("/status", ((req, res) ->
                jsonTransformer.render(chessGameService.getScore())));
    }

    private void end(final ChessGameService chessGameService) {
        get("/end", ((req, res) -> {
            final Map<Position, Piece> pieces = chessGameService.end();
            return render(convertToWebViewPiece(pieces));
        }));
    }

    private String render(final Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }
}
