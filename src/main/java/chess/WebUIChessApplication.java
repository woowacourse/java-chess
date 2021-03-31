package chess;

import static spark.Spark.get;

import chess.domain.ChessBoard;
import com.google.gson.Gson;

public class WebUIChessApplication {
    private static final ChessBoard chessBoard = new ChessBoard();

    public static void main(String[] args) {
        //staticFileLocation("/static");

        Gson gson = new Gson();

        get("/", (req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.type("application/json");
            return chessBoard.getChessBoardDto();
        }, gson::toJson);
    }

}
