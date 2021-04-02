package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessBoard;
import chess.domain.Result;
import chess.domain.dao.ChessBoardDAO;
import chess.domain.dao.ChessBoardDTOForDAO;
import chess.domain.dto.MovementDto;
import chess.domain.dto.PieceDto;
import com.google.gson.Gson;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static final ChessBoard chessBoard = new ChessBoard();
    private static final ChessBoardDAO chessBoardDao = new ChessBoardDAO();

    public static void main(String[] args) {
        staticFileLocation("/static");
        Gson gson = new Gson();
        Connection connection = chessBoardDao.getConnection();

        post("/chessboard", (req, res) ->{
            chessBoard.move(gson.fromJson(req.body(), MovementDto.class));
           return chessBoard.getChessBoardDto();
        }, gson::toJson);

        post("/chessboard/save",(req, res) ->{
            chessBoardDao.removePositions();
            Map<String, PieceDto> chessBoardDto = chessBoard.getChessBoardDto();
            List<ChessBoardDTOForDAO> results = new ArrayList<>();
            for (Map.Entry<String, PieceDto> eachInfo : chessBoardDto.entrySet()){
                PieceDto value = eachInfo.getValue();
                results.add(new ChessBoardDTOForDAO(eachInfo.getKey(),value.getTeamColor(), value.getPieceType(), value.getAlive()))   ;
            }
            chessBoardDao.addPositions(results);
            return chessBoard.result();
        }, gson::toJson);

        get("/chessboard", (req, res) -> {
            res.type("application/json");
            return chessBoard.getChessBoardDto();
        }, gson::toJson);

        get("/chessboard/result", (req, res) -> {
            res.type("application/json");
            return chessBoard.result();
        }, gson::toJson);

        get("/chessboard/end", (req, res) -> {
            res.type("application/json");
            chessBoard.terminate();
            return chessBoard.result();
        }, gson::toJson);


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
