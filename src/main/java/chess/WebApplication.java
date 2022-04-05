package chess;

import chess.domain.command.Command;
import chess.domain.command.CommandConverter;
import chess.web.BoardDTO;
import chess.web.MyState;
import chess.web.Request;
import java.util.NoSuchElementException;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        port(4568);
        staticFiles.location("/static");
        MyState myState = MyState.generate();
        BoardDTO dto = BoardDTO.buildModel();
        get("/", (req, res) -> {
            dto.update(myState.getBoard());
            return new ModelAndView(dto.getData(), "index.html");
        }, new HandlebarsTemplateEngine());

        post("/ready", (req, res) -> {
            try{
                myState.run(CommandConverter.convertCommand(Request.from(req.body()).getCommand()));
                dto.update(myState.getBoard());
                if (myState.isRunning()) {
                    return new ModelAndView(dto.getData(), "white.html");
                }
                if (myState.isFinished()) {
                    dto.update(myState.getBoard());
                    res.redirect("/finished");
                    return null;
                }
                return null;
            }
            catch (IllegalArgumentException | IllegalStateException | NoSuchElementException exception) {
                dto.updateWithMessage(myState.getBoard(), exception.getMessage());
                return new ModelAndView(dto.getData(), "ready_exception.html");
            }
        }, new HandlebarsTemplateEngine());

        post("/move", (req, res) -> {
            try{
                if (dto.getData().containsKey("score")) {
                    dto.update(myState.getBoard());
                    if (myState.isWhite()) {
                        return new ModelAndView(dto.getData(), "white.html");
                    }
                    return new ModelAndView(dto.getData(), "black.html");
                }
                Command command = CommandConverter.convertCommand(Request.from(req.body()).getCommand());
                myState.run(command);
                if (myState.isRunning()) {
                    if (command.isStatus()) {
                        dto.updateWithScore(myState.getBoard(), myState.generateScore());
                        return new ModelAndView(dto.getData(), "status.html");
                    }
                    dto.update(myState.getBoard());
                    if (!myState.isWhite()) {
                        return new ModelAndView(dto.getData(), "black.html");
                    }
                    return new ModelAndView(dto.getData(), "white.html");
                }
                if (myState.isFinished()) {
                    dto.update(myState.getBoard());
                    res.redirect("/finished");
                    return null;
                }
                return null;
            }
            catch (IllegalArgumentException | IllegalStateException | NoSuchElementException exception) {
                dto.updateWithMessage(myState.getBoard(), exception.getMessage());
                if (myState.isWhite()) {
                    return new ModelAndView(dto.getData(), "white_exception.html");
                }
                return new ModelAndView(dto.getData(), "black_exception.html");
            }

        }, new HandlebarsTemplateEngine());

        post("/backward", (req, res) -> {
            res.redirect("/");
            return null;
        });

        post("/backwardWhite", (req, res) -> {
            return new ModelAndView(dto.getData(), "white.html");
        }, new HandlebarsTemplateEngine());

        post("/backwardBlack", (req, res) -> {
            return new ModelAndView(dto.getData(), "black.html");
        }, new HandlebarsTemplateEngine());

        get("/finished", (req, res) -> {
            return new ModelAndView(dto.getData(), "finished.html");
        }, new HandlebarsTemplateEngine());
    }
}
