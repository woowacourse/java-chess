package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Type;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import chess.dto.ChessBoardDto;
import chess.dto.MoveSquareDto;
import chess.dto.PromotionTypeDto;
import chess.dto.ResultDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ApplicationUI {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "/start.html");
        });

        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessBoard.initialize();
            return render(model, "/game.html");
        });

        post("/move", (req, res) -> {
            Gson gson = new Gson();
            MoveSquareDto moveSquareDto = gson.fromJson(req.body(), MoveSquareDto.class);
            MoveSquare moveSquare = new MoveSquare(moveSquareDto.getSource(),
                moveSquareDto.getTarget());
            MoveState moveState = chessBoard.movePieceWhenCanMove(moveSquare);
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard, moveState);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            if (moveState == MoveState.KING_CAPTURED) {
                chessBoardDto.clearPiece();
            }
            return new Gson().toJson(chessBoardDto);
        });

        post("/promotion", (req, res) -> {
            Gson gson = new Gson();
            PromotionTypeDto promotionTypeDto = gson.fromJson(req.body(), PromotionTypeDto.class);
            Type type = Type.of(promotionTypeDto.getPromotionType());
            MoveState moveState = chessBoard.promotion(type);
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard, moveState);
            return new Gson().toJson(chessBoardDto);
        });

        get("/board", (req, res) -> {
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard);
            return new Gson().toJson(chessBoardDto);
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ResultDto resultDto = new ResultDto(chessBoard.getTeamScore());
            model.put("winner", resultDto.getWinner());
            model.put("blackScore", resultDto.getBlackScore());
            model.put("whiteScore", resultDto.getWhiteScore());
            return render(model, "/end.html");
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
