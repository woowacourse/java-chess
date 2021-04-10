package practice;

import chess.domain.ChessBoard;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.post;

public class Api {
    public static ChessBoard chessBoard = new ChessBoard();

    public static void main(String[] args) {
        Gson gson = new Gson();
        post("/add", (req, res) -> {
            res.type("application/json");
            Product product = gson.fromJson(req.body(), Product.class);
            return product.getName();
        }, gson::toJson);

        get("/", (req, res) -> {
            res.type("application/json");
            return chessBoard.getChessBoard();
        }, gson::toJson);
    }

}
