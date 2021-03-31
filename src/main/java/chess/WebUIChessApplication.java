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
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFileLocation("public");
        ChessBoard chessBoard = new ChessBoard();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            Map<String, pieceOnBoardDTO> pieces = new HashMap<>();
            for (Entry<Position, Piece> entry : chessBoard.getChessBoard().entrySet()) {
                pieces.put(entry.getKey().getPosition(),
                    new pieceOnBoardDTO(entry.getValue().getColor(),
                        entry.getValue().getPieceName()));
            }
            return gson.toJson(pieces);
        });

        post("/move", (req, res) -> {
            MoveRequestDTO moveRequestDto = gson.fromJson(req.body(), MoveRequestDTO.class);
            MoveResultDTO moveResultDto;

            if (!chessBoard.isPlaying()) {
                return new MoveResultDTO(false, chessBoard.isPlaying());
            }

            try {
                chessBoard.move(moveRequestDto.getSource(), moveRequestDto.getTarget());
                moveResultDto = new MoveResultDTO(true, chessBoard.isPlaying());
            } catch (IllegalArgumentException exception) {
                moveResultDto = new MoveResultDTO(false, chessBoard.isPlaying());
            }
            return moveResultDto;
        }, gson::toJson);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
