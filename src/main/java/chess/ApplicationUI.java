package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.board.ChessGame;
import chess.domain.piece.Type;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import chess.dto.ChessGameDTO;
import chess.dto.MoveSquareDTO;
import chess.dto.PromotionTypeDTO;
import chess.dto.ResultDTO;
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
            MoveSquareDTO moveSquareDTO = gson.fromJson(req.body(), MoveSquareDTO.class);
            MoveSquare moveSquare = new MoveSquare(moveSquareDTO.getSource(),
                moveSquareDTO.getTarget());
            MoveState moveState = chessGame.movePieceWhenCanMove(moveSquare);
            ChessGameDTO chessGameDTO = new ChessGameDTO(chessGame, moveState);
            if (moveState == MoveState.KING_CAPTURED) {
                chessGameDTO.clearPiece();
            }
            return new Gson().toJson(chessGameDTO);
        });

        post("/promotion", (req, res) -> {
            Gson gson = new Gson();
            PromotionTypeDTO promotionTypeDTO = gson.fromJson(req.body(), PromotionTypeDTO.class);
            Type type = Type.of(promotionTypeDTO.getPromotionType());
            MoveState moveState = chessGame.promotion(type);
            ChessGameDTO chessGameDTO = new ChessGameDTO(chessGame, moveState);
            return new Gson().toJson(chessGameDTO);
        });

        get("/board", (req, res) -> {
            ChessGameDTO chessGameDTO = new ChessGameDTO(chessGame);
            return new Gson().toJson(chessGameDTO);
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ResultDTO resultDTO = new ResultDTO(chessGame.getTeamScore());
            model.put("winner", resultDTO.getWinner());
            model.put("blackScore", resultDTO.getBlackScore());
            model.put("whiteScore", resultDTO.getWhiteScore());
            return render(model, "/end.html");
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
