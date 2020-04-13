package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardDAO;
import chess.domain.board.BoardFactory;
import chess.service.BoardService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Board board = BoardFactory.createBoard();
    private static BoardDAO boardDAO = new BoardDAO();

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            return render(BoardService.receiveEmptyBoard(), "index.html");
        });

        post("/start", (req, res) -> {
            return render(BoardService.receiveInitializedBoard(board, boardDAO), "index.html");
        });

        post("/end", (req, res) -> {
            return render(BoardService.receiveDeletedBoard(boardDAO), "index.html");
        });

        post("/load", (req, res) -> {
            return render(BoardService.receiveLoadedBoard(boardDAO), "index.html");
        });

//        post("/move", (req, res) -> {
//            Map<String, Object> model = ModelParser.parseBoard(board);
//            try {
//                board.move(req.queryParams("fromPiece"), req.queryParams("toPiece"));
//                if (board.isFinished()) {
//                    board.changeTurn();
//                }
//                model = (ModelParser.parseBoard(board));
//                model.put("isFinished", String.valueOf(board.isFinished()));
//                model.put("turn", board.getTeam());
//
//                boardDAO.deletePieces();
//                for (Piece piece : board.getBoard()) {
//                    boardDAO.placePiece(piece);
//                }
//            } catch (InvalidPositionException | PieceImpossibleMoveException ignored) {
//            } catch (TakeTurnException e) {
//                model.put("turn", board.getTeam());
//            }
//            return render(model, "index.html");
//        });
//
//        post("/status", (req, res) -> {
//            GameResult gameResult = new GameResult();
//            double blackScore = gameResult.calculateScore(board, Team.BLACK);
//            double whiteScore = gameResult.calculateScore(board, Team.WHITE);
//            if (blackScore == 0 && whiteScore == 0) {
//                return render(ModelParser.parseBoard(board), "index.html");
//            } else {
//                Map<String, Object> model = ModelParser.parseBoard(board);
//                model.put("black", blackScore);
//                model.put("white", whiteScore);
//                model.put("turn", board.getTeam());
//                return render(model, "index.html");
//            }
//        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
