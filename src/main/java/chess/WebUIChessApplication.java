package chess;

import chess.utils.DBUtil;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
        DataSource dataSource = DBUtil.getDataSource();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
