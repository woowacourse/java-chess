package chess;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.Controller.ChessController;
import chess.Controller.command.ParsedCommand;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.piece.Piece;
import chess.util.JsonParser;
import chess.util.ViewUtil;
import java.util.HashMap;
import java.util.Map;

public class WebApplication {
    public static String STATUS = "dev";

    public static void main(String[] args) {
        port(8080);

        if (STATUS.equals("dev")) {
            String projectDirectory = System.getProperty("user.dir");
            String staticDirectory = "/src/main/resources/static";
            externalStaticFileLocation(projectDirectory + staticDirectory);
        } else {
            staticFileLocation("/static");
        }

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return ViewUtil.render(model, "/index.html");
        });

        get("/game/board-status", (req, res) -> {
            final Map<Position, Piece> pieces = (new CreateCompleteBoardStrategy()).createPieces();
            final ChessController chess = new ChessController(new Board(pieces));
            final PiecesDto piecesDto = chess.getPieces();
            return JsonParser.makePiecesToJsonArray(piecesDto);
        });

        get("/game/command/:command", (req, res) -> {
            final Map<Position, Piece> pieces = (new CreateCompleteBoardStrategy()).createPieces();
            final ChessController chess = new ChessController(new Board(pieces));
            final String command = req.params(":command");
            if (command.equals("start") || command.equals("move")) {
                final PiecesDto piecesDto = chess.doActionAboutPieces(new ParsedCommand(command));
                return JsonParser.makePiecesToJsonArray(piecesDto);
            }
            final ScoreDto scoreDto = chess.doActionAboutScore(new ParsedCommand(command));
            return scoreDto;
        });
    }


}

