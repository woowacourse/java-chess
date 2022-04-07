package chess.web;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebConfig {

    public void init() {
        port(8080);
        staticFiles.location("/static");
    }
}
