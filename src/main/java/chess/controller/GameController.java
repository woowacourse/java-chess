package chess.controller;

import chess.model.ChessGame;
import chess.model.ScoreResult;
import chess.model.boardcreatestrategy.ContinueGameCreateStrategy;
import chess.model.boardcreatestrategy.NewGameCreateStrategy;
import chess.model.dao.ChessDAO;
import chess.model.dto.BoardDTO;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static chess.WebUIChessApplication.render;

public class GameController {

    public Object start(Request req, Response res) throws SQLException {
        ChessDAO chessDAO = ChessDAO.getInstance();
        chessDAO.deleteAll();
        chessDAO.insertInit();
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessDAO.selectByTurn(1).getPieces());

        ChessGame game = new ChessGame(new NewGameCreateStrategy(), 1);

        ScoreResult scoreResult = game.calculateScore();

        model.put("score", scoreResult);
        model.put("currentTeam", game.getCurrentTeam());

        req.session().attribute("game", game);

        return render(model, "newgame.html");
    }

    public Object continueGame(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        ChessDAO chessDAO = ChessDAO.getInstance();
        String source = req.queryParams("source");
        String target = req.queryParams("target");

        ChessGame game;
        game = req.session().attribute("game");

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
