package chess;

import static spark.Spark.*;
import static spark.Spark.get;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.state.WhiteTurn;
import chess.dto.RankDTO;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    private static ChessGame chessGame = new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard())));
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Board board = chessGame.getBoard();
            List<RankDTO> ranks = new ArrayList<>();
            for (int i = 8; i > 0; i--) {
                ranks.add(RankDTO.toDTO(board.getRank(i - 1), i));
            }
            model.put("turn", chessGame.getTurn());
            model.put("ranks", ranks);
            return render(model, "play.html");
        });

        post("/command", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String command = req.queryParams("command");
            executeCommand(command);
            if (chessGame.isEnd()) {
                return render(model, "result.html");
            }
            res.redirect("/play");
            return "";
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void executeCommand(String command) {
        List<String> input = List.of(command.split(" "));
        if (Command.inGameCommand(input.get(0)) == Command.END) {
            chessGame.terminate();
        }
        if (Command.inGameCommand(input.get(0)) == Command.MOVE && input.size() == 3) {
            chessGame.move(input.get(1), input.get(2));
        }
    }
}
