package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.board.ChessGame;
import chess.domain.piece.Type;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import chess.dto.ChessGameDto;
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
        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "/start.html");
        });

        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.initialize();
            return render(model, "/game.html");
        });

        post("/move", (req, res) -> {
            Gson gson = new Gson();
            MoveSquareDto moveSquareDto = gson.fromJson(req.body(), MoveSquareDto.class);
            MoveSquare moveSquare = new MoveSquare(moveSquareDto.getSource(),
                moveSquareDto.getTarget());
            MoveState moveState = chessGame.movePieceWhenCanMove(moveSquare);
            ChessGameDto chessGameDto = new ChessGameDto(chessGame, moveState);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            if (moveState == MoveState.KING_CAPTURED) {
                chessGameDto.clearPiece();
            }
            return new Gson().toJson(chessGameDto);
        });

        post("/promotion", (req, res) -> {
            Gson gson = new Gson();
            PromotionTypeDto promotionTypeDto = gson.fromJson(req.body(), PromotionTypeDto.class);
            Type type = Type.of(promotionTypeDto.getPromotionType());
            MoveState moveState = chessGame.promotion(type);
            ChessGameDto chessGameDto = new ChessGameDto(chessGame, moveState);
            return new Gson().toJson(chessGameDto);
        });

        get("/board", (req, res) -> {
            ChessGameDto chessGameDto = new ChessGameDto(chessGame);
            return new Gson().toJson(chessGameDto);
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ResultDto resultDto = new ResultDto(chessGame.getTeamScore());
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
