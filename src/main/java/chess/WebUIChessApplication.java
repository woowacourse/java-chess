package chess;

import chess.domain.board.Board;
import chess.domain.command.*;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;
import chess.view.dto.BoardDto;
import chess.view.dto.ScoreDtos;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static Board board;
    private static ChessGame chessGame;
    private static Commands commands;

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        get("/", (req, res) -> render(Collections.EMPTY_MAP, "index.html"));

        get("/pieces", (request, response) -> {
            if (chessGame.isFinished()) {
                throw new IllegalArgumentException("게임이 이미 종료되었습니다!");
            }

            String input = request.queryParams("command");
            Command command = commands.getIfPresent(input);
            command.handle(input);
            return new BoardDto(chessGame.isFinished(), board);
        }, MAPPER::writeValueAsString);

        post("/chessgames", (request, response) -> {
            if (chessGame != null && !chessGame.isFinished()) {
                throw new IllegalStateException("이미 게임이 진행중입니다.");
            }

            board = new Board(PieceFactory.createPieces());
            chessGame = new ChessGame(board);
            commands = initCommands(chessGame);
            Command command = commands.getIfPresent("start");
            command.handle("start");

            return new BoardDto(chessGame.isFinished(), board);
        }, MAPPER::writeValueAsString);

        delete("/chessgames", (request, response) -> {
            if (chessGame.isFinished()) {
                throw new IllegalArgumentException("게임이 이미 종료되었습니다!");
            }

            Command command = commands.getIfPresent("end");
            command.handle("end");
            return new BoardDto(chessGame.isFinished(), board);
        }, MAPPER::writeValueAsString);

        get("/scores", (request, response) -> {
            if (chessGame.isFinished()) {
                throw new IllegalArgumentException("게임이 이미 종료되었습니다!");
            }

            Command command = commands.getIfPresent("status");
            command.handle("status");
            StatusCommand statusCommand = (StatusCommand) command;
            return new ScoreDtos(statusCommand);
        }, MAPPER::writeValueAsString);
    }

    private static Commands initCommands(final ChessGame chessGame) {
        Map<String, Command> commandGroup = new HashMap<>();
        commandGroup.put("start", new StartCommand(chessGame));
        commandGroup.put("move", new MoveCommand(chessGame));
        commandGroup.put("end", new EndCommand(chessGame));
        commandGroup.put("status", new StatusCommand(chessGame));
        Commands commands = new Commands(commandGroup);

        return commands;
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
