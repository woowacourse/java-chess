package chess;

import static spark.Spark.get;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.state.WhiteTurn;
import chess.dto.RankDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard())));
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Board board = chessGame.getBoard();
            List<RankDTO> ranks = new ArrayList<>();
            for (int i = 8; i > 0; i--) {
                ranks.add(RankDTO.toDTO(board.getRank(i - 1), i));
            }
            model.put("turn", chessGame.getTurn());
            model.put("ranks", ranks);
            return render(model, "play.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
