package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.state.WhiteTurn;
import chess.dto.RankDTO;
import chess.repository.GameRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private final GameRepository gameRepository;

    public WebController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void run() {
        gameRepository.save(new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard()))));
        ChessGame chessGame = gameRepository.findById(1L).get();
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
            executeCommand(chessGame, command);
            if (chessGame.isEnd()) {
                res.redirect("/result");
            }
            res.redirect("/play");
            return "";
        });

        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("winner", chessGame.getWinnerName());
            model.put("whiteScore", chessGame.getWhiteScore());
            model.put("blackScore", chessGame.getBlackScore());
            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void executeCommand(ChessGame chessGame, String command) {
        List<String> input = List.of(command.split(" "));
        if (Command.inGameCommand(input.get(0)) == Command.END) {
            chessGame.terminate();
        }
        if (Command.inGameCommand(input.get(0)) == Command.MOVE && input.size() == 3) {
            chessGame.move(input.get(1), input.get(2));
        }
    }
}
