package chess;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.Controller.web.WebChessController;
import chess.util.path.Web;

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

        get(Web.COMMAND_ACTION, WebChessController.runCommand);

    }

}

