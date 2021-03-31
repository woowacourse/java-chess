import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import dto.request.ChessGameRequestDto;
import dto.request.PieceRequestDto;
import java.util.HashMap;
import java.util.Map;
import service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson GSON = new Gson();
    private static final ChessGameService chessGameService = new ChessGameService();

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/static");
        getMainPage();
    }

    private static void getMainPage() {
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}