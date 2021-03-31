package chess;

import static chess.web.view.RenderView.renderHtml;
import static spark.Spark.get;
import static spark.Spark.staticFiles;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.game.ChessGame;
import chess.domain.gamestate.Ready;
import chess.exception.ChessException;
import chess.web.dto.BoardDto;
import java.util.Map;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessGame chessGame = new ChessGame(new Ready(Board.createGamingBoard()));
        chessGame.start();

        get("/chess", (req, res) -> {
            Map<String, Object> model = new BoardDto(chessGame.board()).getResult();
            return renderHtml(model, "/index.html");
        });

        get("/chess/move", (req, res) -> {
            String target = req.queryParams("target");
            String source = req.queryParams("source");

            Map<String, Object> model = movePiece(chessGame, target, source);

            return renderHtml(model, "/index.html");
        });
    }

    private static Map<String, Object> movePiece(ChessGame chessGame, String target,
            String source) {
        try {
            chessGame.execute(Command.from("move " + target + " " + source));

            // todo 네 마음속에 저장🌟
            return new BoardDto(chessGame.board()).getResult();
        } catch (ChessException e) {
            Map<String, Object> model = new BoardDto(chessGame.board()).getResult();
            model.put("error", String.format("<script>alert(\"%s\")</script>", e));
            return model;
        }
    }
}
