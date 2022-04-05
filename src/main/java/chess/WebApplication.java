package chess;

import chess.domain.board.BoardGenerator;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.piece.unit.Piece;
import chess.web.dto.BoardDto;
import chess.domain.position.Position;
import chess.web.view.Render;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        get("/chess", (req, res) -> {
           Map<String, Object> model = new BoardDto(chessBoard).getResult();
           return Render.renderHtml(model, "/index.html");
        });

        get("/chess/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            Map<String, Object> model = move(chessBoard, source, target);
            return Render.renderHtml(model, "/index.html");
        });


//        get("/", (req, res) -> {
//            Map<String, Object> model = new BoardDto(chessBoard).getResult();
//            return render(model, "index.html");
//        });
    }

    private static Map<String, Object> move(ChessBoard chessBoard, String source, String target) {
        try{
            chessBoard.move(Position.of(source), Position.of(target));
            System.out.println("소스 : " + Position.of(source));
            System.out.println("타겟 : " + Position.of(target));
            return new BoardDto(chessBoard).getResult();
        }catch (Exception e){
            System.out.println("소스 : " + Position.of(source));
            System.out.println("타겟 : " + Position.of(target));
            System.out.println("오류 : " + e.getMessage());
            return new BoardDto(chessBoard).getResult();
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));

    }
}
