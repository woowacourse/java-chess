package chess;

import chess.domain.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static spark.Spark.*;

public class WebUIChessApplication {
    private static ChessBoard chessBoard;

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/create-room", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rno", 1);
            model.put("title", req.queryParams("title"));

            return model;
        }, gson::toJson);

        get("/room", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rno", 1);
            model.put("title", "누구나");

            return render(model, "room.html");
        });

        get("/board", (req, res) -> {
            initBoard();

            return chessBoard.getBoard();
        }, gson::toJson);

        put("/move-piece", (req, res) -> {
            initBoard();
            chessBoard.move(ChessCoordinate.valueOf(req.queryParams("from")).get(), ChessCoordinate.valueOf(req.queryParams("to")).get());
            return chessBoard.getBoard();
        }, gson::toJson);


    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void initBoard() {
        ChessPiece empty = EmptyCell.getInstance();

        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(BLACK), Knight.getInstance(BLACK), Bishop.getInstance(BLACK), Queen.getInstance(BLACK),
                        King.getInstance(BLACK), Bishop.getInstance(BLACK), Knight.getInstance(BLACK), Rook.getInstance(BLACK)),
                Arrays.asList(Pawn.getInstance(BLACK), Pawn.getInstance(BLACK), Pawn.getInstance(BLACK), Pawn.getInstance(BLACK),
                        Pawn.getInstance(BLACK), Pawn.getInstance(BLACK), Pawn.getInstance(BLACK), Pawn.getInstance(BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Pawn.getInstance(WHITE), Pawn.getInstance(WHITE), Pawn.getInstance(WHITE), Pawn.getInstance(WHITE),
                        Pawn.getInstance(WHITE), Pawn.getInstance(WHITE), Pawn.getInstance(WHITE), Pawn.getInstance(WHITE)),
                Arrays.asList(Rook.getInstance(WHITE), Knight.getInstance(WHITE), Bishop.getInstance(WHITE), Queen.getInstance(WHITE),
                        King.getInstance(WHITE), Bishop.getInstance(WHITE), Knight.getInstance(WHITE), Rook.getInstance(WHITE))
        );
        chessBoard = new ChessBoard(boardState);
    }
}
