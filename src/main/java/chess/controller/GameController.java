package chess.controller;

import chess.model.ChessGame;
import chess.model.ScoreResult;
import chess.model.boardcreatestrategy.ContinueGameCreateStrategy;
import chess.model.boardcreatestrategy.NewGameCreateStrategy;
import chess.model.dao.ChessDAO;
import chess.model.dto.BoardDTO;
import chess.service.GameService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static chess.WebUIChessApplication.render;

public class GameController {
    public static final int START_TURN = 1;
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public Object start(Request req, Response res) throws SQLException {
        gameService.deleteAll();
        gameService.insertInit();

        int turn = START_TURN;
        ChessGame game = gameService.initGame(turn);

        req.session().attribute("game", game);
        res.redirect("/show?turn="+turn);
        return null;
    }

    public Object show(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();

        ChessGame game = req.session().attribute("game");
        ScoreResult scoreResult = game.calculateScore();
        int turn = Integer.parseInt(req.queryParams("turn"));

        model.put("board", gameService.findByTurn(turn));
        model.put("score", scoreResult);
        model.put("currentTeam", game.getCurrentTeam());
        return render(model, "game.html");
    }

    public Object continueGame(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        ChessDAO chessDAO = ChessDAO.getInstance();
        String source = req.queryParams("source");
        String target = req.queryParams("target");

        ChessGame game = req.session().attribute("game");

        if (!Objects.isNull(source)) { // 게임 진행
            model.put("winner", game.getCurrentTeam());
            game.movePiece(source, target);
            BoardDTO boardDTO = new BoardDTO(game.convertToList());
            chessDAO.updateBoard(boardDTO);
            model.put("currentTeam", game.getCurrentTeam());
            if (game.checkKingDead()) {
                chessDAO.deleteAll();

                return render(model, "end.html");
            }

            model.put("board", boardDTO.getPieces());
        }
        if (Objects.isNull(source)) { // 이어서하기 첫 화면
            BoardDTO boardDTO = chessDAO.selectByTurn(chessDAO.getLatestTurn());
            game = new ChessGame(new ContinueGameCreateStrategy(boardDTO), chessDAO.getLatestTurn());
            model.put("currentTeam", game.getCurrentTeam());

            Collections.reverse(boardDTO.getPieces());

            model.put("board", boardDTO.getPieces());
        }
        req.session().attribute("game", game);

        ScoreResult scoreResult = game.calculateScore();
        model.put("score", scoreResult);

        return render(model, "game.html");
    }
}
