package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.DTO.MoveRequestDTO;
import chess.domain.DTO.MoveResultDTO;
import chess.domain.DTO.pieceOnBoardDTO;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.ResultDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {


    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFileLocation("public");
        AtomicReference<ChessBoard> chessBoard = new AtomicReference<>(new ChessBoard());
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            Map<String, pieceOnBoardDTO> pieces = new HashMap<>();
            for (Entry<Position, Piece> entry : chessBoard.get().getChessBoard().entrySet()) {
                pieces.put(entry.getKey().getPosition(),
                    new pieceOnBoardDTO(entry.getValue().getColor(),
                        entry.getValue().getPieceName()));
            }
            return gson.toJson(pieces);
        });

        post("/move", (req, res) -> {
            MoveRequestDTO moveRequestDto = gson.fromJson(req.body(), MoveRequestDTO.class);
            ResultDto result;

            try {
                chessBoard.get().move(moveRequestDto.getSource(), moveRequestDto.getTarget());
                if (!chessBoard.get().isPlaying()) {
                    throw new IllegalArgumentException("game End state");
                }
                result = chessBoard.get().result();
                return new MoveResultDTO(true,
                    chessBoard.get().isPlaying(),
                    result.whiteScore().getScore(),
                    result.blackScore().getScore());
            } catch (IllegalArgumentException exception) {
                result = chessBoard.get().result();
                return new MoveResultDTO(false,
                    chessBoard.get().isPlaying(),
                    result.whiteScore().getScore(),
                    result.blackScore().getScore());
            }
        }, gson::toJson);

        post("/reset", (req, res) -> {
            chessBoard.set(new ChessBoard());
            res.redirect("");
            return res;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
