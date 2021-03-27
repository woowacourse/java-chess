package chess;

import static spark.Spark.after;
import static spark.Spark.get;

import chess.controller.ChessController;
import chess.domain.ChessGameImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import spark.Filter;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(ChessGameImpl.initialGame());
        ObjectMapper objectMapper = new ObjectMapper();

        after((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET");
            res.header("Access-Control-Allow-Methods", "POST");
        });

        get("/pieces", (req, res) -> objectMapper.writeValueAsString(chessController.startGame()));
    }
}
