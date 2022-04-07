package chess;

import static spark.Spark.exception;
import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import chess.Controller.web.WebChessController;
import chess.util.path.Web;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class WebApplication {

    public static String STATUS = "dev";

    public static void main(String[] args) {
        port(8080);

        if (STATUS.equals("dev")) {
            String projectDirectory = System.getProperty("user.dir");
            String staticDirectory = "/src/main/resources/static";
            externalStaticFileLocation(projectDirectory + staticDirectory);
        } else {
            staticFileLocation("/static");
        }

        get(Web.MAIN_PAGE, WebChessController.renderMainPage);

        get(Web.USER_HISTORY, WebChessController.findUserHistory);

        put(Web.COMMAND_ACTION, WebChessController.runCommand);

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            final Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.status(400);
            response.body(JSONObject.toJSONString(error));
        });

        exception(Exception.class, (exception, request, response) -> {
            final Map<String, String> error = new HashMap<>();
            error.put("error_message", "현재 실행할 수 없는 명령입니다.");
            response.status(400);
            response.body(JSONObject.toJSONString(error));
        });

    }

}

