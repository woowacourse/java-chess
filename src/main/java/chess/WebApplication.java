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
            Map<String, Object> model = new HashMap<>();
            return render(model, "start.html");
        });

        get("/start", (req, res) -> {
            ChessBoardDao chessBoardDao = new ChessBoardDao();
            PlayerDao playerDao = new PlayerDao();

            ChessGame chessGame = new ChessGame();
            chessGame.start();

            //전부 지우고 다시 채운다.
            chessBoardDao.deleteAll();
            Map<Position, Piece> chessBoard = chessGame.getBoard();
            for (Position position : chessBoard.keySet()) {
                chessBoardDao.save(position, chessBoard.get(position));
            }
            playerDao.deleteAll();
            playerDao.save(Color.of(chessGame.getTurn()));

            res.redirect("/play");
            return null;
        });

        get("/play", (req, res) -> {
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

            //에러메시지 세션
            if (req.session().attribute("ERROR_MESSAGE") != null) {
                model.putAll(req.session().attribute("ERROR_MESSAGE"));
                req.session().removeAttribute("ERROR_MESSAGE");
            }

            return render(model, "index.html");
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if (req.session().attribute("WINNER_MESSAGE") != null) {
                model.putAll(req.session().attribute("WINNER_MESSAGE"));
                req.session().removeAttribute("WINNER_MESSAGE");
            }
            return render(model, "end.html");
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

            //command a2a4
            String source = command.substring(0, 2);
            String target = command.substring(2, 4);
            chessGame.move(Position.from(source), Position.from(target));

            //전부 지우고 다시 채운다.
            chessBoardDao.deleteAll();
            chessBoard = chessGame.getBoard();
            for (Position position : chessBoard.keySet()) {
                chessBoardDao.save(position, chessBoard.get(position));
            }
            playerDao.deleteAll();
            playerDao.save(Color.of(chessGame.getTurn()));

            res.redirect("/play");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            String errorMessage = exception.getMessage();
            if (errorMessage.equals("이미 종료된 게임입니다.")) {
                PlayerDao playerDao = new PlayerDao();
                Map<String, Object> winner = new HashMap<>();
                winner.put("hasWinner", true);
                winner.put("winnerMessage", "게임이 종료되었습니다. 새 게임을 시작해주세요!!!");
                request.session().attribute("WINNER_MESSAGE", winner);

                response.redirect("/end");
                return;
            }

            Map<String, Object> error = new HashMap<>();
            error.put("hasError", true);
            error.put("errorMessage", errorMessage);
            request.session().attribute("ERROR_MESSAGE", error);

            response.redirect("/play");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
