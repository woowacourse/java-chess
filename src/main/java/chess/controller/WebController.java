package chess.controller;

import chess.domain.dao.CommandDatabase;
import chess.domain.dto.CommandDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final ChessService chessService;
    private CommandDatabase commandDatabase;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
        this.commandDatabase = new CommandDatabase();
    }

    public WebController(ChessService chessService, CommandDatabase commandDatabase) {
        this.chessService = chessService;
        this.commandDatabase = commandDatabase;
    }

    public void play() {
        get("/play", (req, res) -> {
            chessService.init();
            return render(chessService.startResponse(), "chessStart.html");
        });

        get("/play/new", (req, res) -> {
            chessService.play();
            return render(chessService.initResponse(), "chessGame.html");
        });

        post("/play/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            String command = makeMoveCmd(req.queryParams("source"), req.queryParams("target"));
            try {
                chessService.move(command);
                commandDatabase.insert(new CommandDto(command));
                return GSON.toJson(chessService.moveResponse());
            } catch (IllegalArgumentException e) {
                res.status(400);
                return e.getMessage();
            }
        });

        get("/play/end", (req, res) -> {
            chessService.end();
            return render(chessService.endResponse(), "chessGame.html");
        });

        get("/play/continue", (req, res) -> {
            if (commandDatabase.isEmpty()) {
                // 만약 historyDB가 연결되어있지 않다면 새로운 게임을 하도록 넘겨주어야함.
                return render(chessService.initResponse(), "chessGame.html");
            }
            // historyDB가 연결되어있다면 이어하기를 가능하도록 해야함.
            chessService.continueLastGame(commandDatabase);
            return render(chessService.continueResponse(), "chessGame.html");
        });

        post("/play/save", (req, res) -> {
            String name = req.queryParams("name");
            try {
                chessService.flush(name, commandDatabase);
                return GSON.toJson(chessService.continueResponse());
            } catch (IllegalArgumentException e) {
                res.status(400);
                return e.getMessage();
            }
        });
    }

    private String makeMoveCmd(String source, String target) {
        return String.join(" ", "move", source, target);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
