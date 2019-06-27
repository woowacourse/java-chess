package chess;

import chess.domain.*;
import chess.domain.piece.Piece;
import com.google.gson.Gson;
import dao.*;
import dto.GameDto;
import dto.NavigatorDto;
import dto.PieceDto;
import dto.UserDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/");

        get("/", (req, res) -> {
            GameDao gameDao = GameDaoImpl.getInstance();
            List<GameDto> notEndGameDtos = gameDao.findNotEndGames();

            Map<String, Object> model = new HashMap<>();
            model.put("notEndGames", notEndGameDtos);

            return render(model, "intro.html");
        });

        post("/", (req, res) -> {
            GameDao gameDao = GameDaoImpl.getInstance();
            int gameId = gameDao.addGame();

            UserDao userDao = UserDaoImpl.getInstance();
            UserDto whiteUserDto = new UserDto(gameId, req.queryParams("white_user"), 2);
            UserDto blackUserDto = new UserDto(gameId, req.queryParams("black_user"), 1);
            userDao.addUser(whiteUserDto);
            userDao.addUser(blackUserDto);

            res.redirect("/" + gameId);
            return "";
        });

        get("/:gameId", (req, res) -> {
            int gameId = Integer.parseInt(req.params("gameId"));

            List<Row> rows = Row.getRows();
            List<Column> columns = Column.getColumns();
            Collections.reverse(rows);

            GameDao gameDao = GameDaoImpl.getInstance();
            GameDto gameDto = gameDao.findById(gameId);

            UserDao userDao = UserDaoImpl.getInstance();
            List<UserDto> userDtos = userDao.findByGameId(gameId);

            Board board = new Board();
            req.session().attribute("board", board);
            board.setThisTurn(gameDto.getTurn());

            PieceDao pieceDao = PieceDaoImpl.getInstance();
            List<PieceDto> pieceDtos = pieceDao.findByGameId(gameId);

            if (pieceDtos.size() == 0) {
                board.initBoard();
                Map<Position, Piece> pieces = board.getPieces();

                for (Position position : pieces.keySet()) {
                    int teamId = pieces.get(position).getAliance().getTeamId();
                    int kindId = pieces.get(position).getPieceValue().getKindId();
                    PieceDto pieceDto = new PieceDto(teamId, gameId, kindId, position.toString());
                    pieceDao.addPiece(pieceDto);
                }
            }

            if (pieceDtos.size() != 0) {
                for (PieceDto piece : pieceDtos) {
                    board.putPiece(piece.getPosition(), piece.getAliance().getTeamId(), piece.getKindId());
                }
            }

            if (gameDto.isEnd() == true) {
                ResultCalculator resultCalculator = new ResultCalculator(board);
                Result result = new Result(resultCalculator.calculateResult());
                throw new RuntimeException(String.format("[최종 스코어] 백 : 흑 = %.1f : %.1f 게임이 종료되었습니다.",
                        result.getWhiteResult(), result.getBlackResult()));
            }

            Gson gson = new Gson();
            Map<String, Object> model = new HashMap<>();
            model.put("gameId", gameId);
            model.put("rows", rows);
            model.put("columns", columns);
            model.put("whiteUser", userDtos.get(0));
            model.put("blackUser", userDtos.get(1));
            model.put("whiteTurn", gameDto.getTurn() == Aliance.WHITE);
            model.put("blackTurn", gameDto.getTurn() == Aliance.BLACK);
            model.put("pieces", gson.toJson(board.getPieces()));

            return render(model, "chess.html");
        });

        post("/:gameId", (req, res) -> {
            int gameId = Integer.parseInt(req.params("gameId"));

            Board board = req.session().attribute("board");

            String start = req.queryParams("start");
            String end = req.queryParams("end");

            board.movePiece(start, end);
            Aliance nextTurn = board.switchTurn();

            NavigatorDto navigatorDto = new NavigatorDto(gameId, start, end);
            PieceDao pieceDao = PieceDaoImpl.getInstance();
            pieceDao.updatePiece(navigatorDto);

            boolean isEnd = !board.isKingAlive(nextTurn);

            GameDto newGameDto = new GameDto(gameId, isEnd, nextTurn.getTeamId());
            GameDao gameDao = GameDaoImpl.getInstance();
            gameDao.updateGame(newGameDto);

            res.redirect("/" + gameId);
            return "";
        });

        exception(Exception.class, (exception, req, res) -> {
            res.body(String.format("<script>alert('%s'); history.back();</script>", exception.getMessage(), req.pathInfo()));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
