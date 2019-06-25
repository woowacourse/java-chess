package chess;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessBoard;
import chess.domain.pieces.Type;
import chess.dto.ChessBoardDto;
import chess.dto.PieceDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.externalStaticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {

        staticFiles.location("/templates/html");
        externalStaticFileLocation("src/main/resources/templates");

        ChessGameDao chessGameDao = new ChessGameDao(DBManager.createDataSource());
        ChessPieceDao chessPieceDao = new ChessPieceDao(DBManager.createDataSource());
        PieceDto pieceDto = new PieceDto();
        ChessBoardDto chessBoardDto = new ChessBoardDto();
        chessBoardDto.setPoints(new ChessBoard().getPoints());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "index.html");
        });

        //새 게임
        get("/playgame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            chessBoardDto.getPoints().entrySet().stream()
                    .filter(point -> !point.getValue().equalsType(Type.BLANK))
                    .forEach(point -> {
                        try {
                            pieceDto.setColor(point.getValue().getColor());
                            pieceDto.setType(point.getValue().getType());
                            pieceDto.setPoint(point.getKey());
                            chessPieceDao.addPiece(
                                    pieceDto.getPoint().toString(),
                                    pieceDto.getColor().toString(),
                                    pieceDto.getType().toString());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

            return render(model, "game.html");
        });

        //계속되는 게임
        post("/playgame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();



            return render(model, "game.html");
        });


    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
