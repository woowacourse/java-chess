package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.ChessBoardDao;
import chess.dao.PlayerDao;
import chess.domain.game.ChessGame;
import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.Player;
import chess.domain.game.state.RunningGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8082);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            ChessBoardDao chessBoardDao = new ChessBoardDao();
            PlayerDao playerDao = new PlayerDao();
            chessBoardDao.findAll();

            Map<String, String> board = chessBoardDao.findAll();
            Map<String, Object> model = new HashMap<>();
            for (String position : board.keySet()) {
                String piece = board.get(position);
                model.put(position, PieceFactory.of(position, piece));
            }

            Player player = playerDao.findAll();
            model.put("turn", player.name());

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            ChessBoardDao chessBoardDao = new ChessBoardDao();
            PlayerDao playerDao = new PlayerDao();

            Map<String, String> board = chessBoardDao.findAll();
            Map<Position, Piece> chessBoard = new HashMap<>();

            for (String position : board.keySet()) {
                Position currentPosition = Position.of(File.of(position.substring(0, 1).toUpperCase()),
                        Rank.of(position.substring(1, 2)));

                Piece currentPiece = PieceFactory.of(position, board.get(position));
                chessBoard.put(currentPosition, currentPiece);
            }
            ChessGame chessGame = ChessGame.of(new RunningGame(ChessBoard.of(chessBoard), playerDao.findAll()));

            final Request request = Request.of(req.body());
            String command = request.command();

            String source = command.substring(0, 2);
            String target = command.substring(2, 4);
            chessGame.move(Position.toPosition(source), Position.toPosition(target));

            //전부 지우고 다시 채운다.
            chessBoardDao.deleteAll();
            chessBoardDao.save(chessGame.getBoard());
            playerDao.deleteAll();
            playerDao.save(Color.of(chessGame.getTurn()));

            res.redirect("/");
            return null;
        });

        get("/start", (req, res) -> {
            ChessBoardDao chessBoardDao = new ChessBoardDao();
            PlayerDao playerDao = new PlayerDao();

            ChessGame chessGame = new ChessGame();
            chessGame.start();

            //전부 지우고 다시 채운다.
            chessBoardDao.deleteAll();
            chessBoardDao.save(chessGame.getBoard());
            playerDao.deleteAll();
            playerDao.save(Color.of(chessGame.getTurn()));

            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
