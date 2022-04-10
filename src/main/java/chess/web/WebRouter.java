package chess.web;

import static spark.Spark.exception;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.controller.GameController;
import chess.controller.HomeController;
import chess.controller.ResultController;
import chess.controller.SearchController;
import chess.dto.ExceptionResponseDto;

public class WebRouter {

    private static final String INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE = "숫자를 입력하여야 합니다.";

    public void init() {
        setConfig();
        setRouteHandlers();
        setExceptionHandlers();
    }

    public void setConfig() {
        port(8080);
        staticFiles.location("/static");
    }

    private void setRouteHandlers() {
        new HomeController().initRouteHandler();
        new SearchController().initRouteHandler();
        new GameController().initRouteHandler();
        new ResultController().initRouteHandler();
    }

    private void setExceptionHandlers() {
        exception(Exception.class, (e, req, res) -> res.body(e.getMessage()));

        exception(NumberFormatException.class, (e, req, res) -> {
            String exceptionResponse = new ExceptionResponseDto(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE).toJson();
            res.body(exceptionResponse);
        });
    }
}
