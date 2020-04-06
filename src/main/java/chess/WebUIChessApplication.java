package chess;

import chess.view.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Connection conn = DBConnector.getConnection();
    private static BoardDAO boardDAO = new BoardDAO(conn);
    private static Board board;

    static {
        try {
            board = boardDAO.loadBoard();
            System.out.println("로딩중..");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        staticFiles.location("/public");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", WebOutputView.printBoard(board));
            return render(model, "index.html");
        });

        post("/", (req, res) -> {
            String source = req.queryParams("source");
            String destination = req.queryParams("destination");
            System.out.println(String.format("받은 경로 : %s -> %s", source, destination));
            //TODO: 말 이동 로직
            Map<String, Object> model = new HashMap<>();
            model.put("board", WebOutputView.printBoard(board));
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}