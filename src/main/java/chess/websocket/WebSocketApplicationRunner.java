package chess.websocket;

import static spark.Spark.init;
import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;

public class WebSocketApplicationRunner {

    public static void main(String[] args) {
        staticFiles.location("/public");
        staticFiles.expireTime(600);

        webSocket("/chess", WebSocketHandler.class);
        init();
    }

}
