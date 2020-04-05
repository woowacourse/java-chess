package chess.controller;

import chess.domain.game.ScoreResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.util.WrongOperationException;
import chess.domain.util.WrongPositionException;
import chess.domain.web.Log;
import chess.domain.web.LogDao;
import chess.domain.web.WebChessGame;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebChessController {
    private final WebChessGame chessGame;
    private final LogDao logDao;

    public WebChessController(){
        chessGame = new WebChessGame();
        logDao = new LogDao();
    }

    public Map<String, Object> init(){
        Map<String, Object> model = new HashMap<>();

        chessGame.reset();
        model.put("status",true);

        return model;
    }

    public Map<String, Object> newGame() throws SQLException {
        Map<String, Object> model = new HashMap<>();

        logDao.clear();
        model.put("status",true);

        return model;
    }

    public Map<String, Object> load() throws SQLException {
        Map<String, Object> model = new HashMap<>();

        Map<Integer, Log> gameLog = logDao.selectAll();
        for (Log log : gameLog.values()) {
            chessGame.move(PositionFactory.of(log.getStart()), PositionFactory.of(log.getEnd()));
        }
        model.put("status", true);

        return model;
    }

    public Map<String, Object> board(){
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessGame.createBoard().getPiecesForTransfer());
        model.put("turn", chessGame.getTurn());
        model.put("score", chessGame.calculateScore());
        model.put("status", true);
        return model;
    }

    public Map<String, Object> move(String start, String end) throws SQLException{
        Map<String, Object> model = new HashMap<>();

        if (start.equals(end)) {
            model.put("status", true);
            return model;
        }

        try {
            chessGame.move(PositionFactory.of(start), PositionFactory.of(end));
            model.put("status", true);
            logDao.insert(start, end);

            if (chessGame.isKingDead()) {
                model.put("winner", chessGame.getAliveKingColor());
                ScoreResult scoreResult = chessGame.calculateScore();
                for (Color color : scoreResult.keySet()) {
                    model.put(color + "score", scoreResult.getScoreBy(color));
                }

                logDao.clear();

                chessGame.reset();
                return model;
            }
            return model;
        } catch (WrongPositionException | WrongOperationException e) {
            model.put("status", false);
            model.put("exception", e.getMessage());
            return model;
        }
    }

    public Map<String, Object> chooseFirstPosition(String position){
        Map<String, Object> model = new HashMap<>();
        try {
            model.put("status", true);
            List<String> movablePositionNames = chessGame
                    .findMovablePositions(PositionFactory.of(position))
                    .getPositions()
                    .stream()
                    .map(Position::toString)
                    .collect(Collectors.toList());
            model.put("position", position);
            model.put("movable", movablePositionNames);

            return model;
        } catch (WrongPositionException | WrongOperationException e) {
            model.put("status", false);
            model.put("exception", e.getMessage());
            return model;
        }
    }

    public Map<String, Object> chooseSecondPosition(String position){
        Map<String, Object> model = new HashMap<>();
        try {
            model.put("status", true);
            model.put("position", position);
            return model;
        } catch (WrongPositionException | WrongOperationException e) {
            model.put("status", false);
            model.put("exception", e.getMessage());
            return model;
        }
    }
}
