package chess;

import chess.domain.grid.ChessGame;
import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.PositionDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");
        Grid grid = new Grid(new NormalGridStrategy());
        ChessGame chessGame = new ChessGame(grid);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/pieces", (req,res)->{
           Map<String, String> pieceMap = grid.pieceMap();
           return pieceMap;
        }, GSON::toJson);

        get("/turn", (req,res)->{
            Color turn = chessGame.turn();
            return turn;
        }, GSON::toJson);

        get("/score/white", (req,res)->{
            return grid.score(Color.WHITE);
        }, GSON::toJson);

        get("/score/black", (req,res)->{
            return grid.score(Color.BLACK);
        }, GSON::toJson);

        post("/move", (req, res) -> {
            PositionDTO positionDTO = GSON.fromJson(req.body(), PositionDTO.class);
            String sourcePosition = positionDTO.getSourcePosition();
            String targetPosition = positionDTO.getTargetPosition();
            System.out.println(sourcePosition + targetPosition);
            try{
                chessGame.move(grid.piece(new Position(sourcePosition)), grid.piece(new Position(targetPosition)));
                return "200";
            }
            catch (IllegalArgumentException error){
                return "400";
            }
        });

        post("/start", (req, res) -> {
            try{
                chessGame.start();
                return 200;
            }
            catch (IllegalArgumentException error){
                return 201;
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
