package chess;

import chess.domain.Controller.WebController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {

    public static void main(String[] args) {
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources";
        staticFiles.externalLocation(projectDir + staticDir);

        new WebController().run();
    }
}
