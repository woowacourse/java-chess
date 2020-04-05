package chess;

import chess.view.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    private static Connection conn = DBConnector.getConnection();
    private static BoardDAO boardDAO = new BoardDAO(conn);
    //private static Board board = boardDAO.loadBoard();

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            //model.put("board", WebOutputView.printBoard());
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}