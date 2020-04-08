import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.command.MoveCommand;
import chess.location.Location;
import chess.progress.Progress;
import converter.ChessGameConverter;
import dao.BoardDao;
import dao.ChessGameDao;
import dao.ChessGamesDao;
import dao.PieceDao;
import dto.*;
import chess.game.ChessGame;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import vo.PieceVo;

public class WebUIChessApplication {
    private static final HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();
    private static final Gson GSON = new Gson();
    private static final int GAME_ID = 1;

    public static void main(String[] args) {
        staticFiles.location("templates");

        ChessGame chessGame = new ChessGame();

        get("/main", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "start.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start/boards", (req, res) -> {
            ChessGamesDao chessGamesDao = new ChessGamesDao();
            ArrayList<ChessGameDto> all = chessGamesDao.findAll();
            ChessGamesDto chessGamesDto = new ChessGamesDto(all);
            return GSON.toJson(chessGamesDto);
        });

        get("/start/board", (req, res) -> {
            int boardId = Integer.parseInt(req.queryParams("id"));

            PieceDao pieceDao = new PieceDao();
            List<PieceVo> pieceVos = pieceDao.findAll(boardId);

            ChessGameDao chessGameDao = new ChessGameDao();
            ChessGameDto chessGameDto = chessGameDao.findChessGameBy(boardId);

            if (pieceVos == null) {
                BoardDto boardDto = new BoardDto(new ChessGame().getChessBoard());
                return GSON.toJson(boardDto);
            }

            BoardDto boardDto = new BoardDto(ChessGameConverter.convert(pieceVos, chessGameDto).getChessBoard());
            return GSON.toJson(boardDto);
        });

        post("/start/move", (req, res) -> {
            LocationDto nowDto = new LocationDto(req.queryParams("now"));
            LocationDto destinationDto = new LocationDto(req.queryParams("des"));

            Location nowLocation = new Location(nowDto.getRow(), nowDto.getCol());
            Location destinationLocation =
                    new Location(destinationDto.getRow(), destinationDto.getCol());

            MoveCommand move = MoveCommand.of(nowLocation, destinationLocation, chessGame);

            Progress progress = chessGame.doOneCommand(move);

            changeTurnIfMoved(chessGame, progress);

            ChessMoveDto chessMoveDto = new ChessMoveDto(
                    new ChessGameScoresDto(chessGame.calculateScores())
                    , progress
                    , chessGame.getTurn());

            return GSON.toJson(chessMoveDto);
        });

        get("/start/winner", (req, res) -> {
            ChessResultDto chessResultDto = new ChessResultDto(chessGame.findWinner());
            return GSON.toJson(chessResultDto);
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BoardDao boardDao = new BoardDao();
            boardDao.addBoard(chessGame.getChessBoard(), GAME_ID);
            return render(model, "start.html");
        });
    }

    private static void changeTurnIfMoved(ChessGame chessGame, Progress progress) {
        if (Progress.CONTINUE == progress) {
            chessGame.changeTurn();
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
    }
}
