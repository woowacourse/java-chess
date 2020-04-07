package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.ScoreResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.exception.WrongOperationException;
import chess.domain.exception.WrongPositionException;
import chess.domain.game.MoveCommand;
import chess.dao.HistoryDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebChessController {
    private final ChessGame chessGame;
    private final HistoryDao historyDao;

    public WebChessController(){
        chessGame = new ChessGame();
        historyDao = new HistoryDao();
    }

    public Map<String, Object> init(){
        Map<String, Object> model = new HashMap<>();

        chessGame.reset();
        model.put("status",true);

        return model;
    }

    public Map<String, Object> newGame() throws SQLException {
        Map<String, Object> model = new HashMap<>();

        historyDao.clear();
        model.put("status",true);

        return model;
    }

    public Map<String, Object> load() throws SQLException {
        Map<String, Object> model = new HashMap<>();

        for (MoveCommand moveCommand : historyDao.selectAll()) {
            Position startPosition = PositionFactory.of(moveCommand.getFirstCommand());
            Position endPosition = PositionFactory.of(moveCommand.getSecondCommand());
            chessGame.move(startPosition, endPosition);
        }
        model.put("status", true);

        return model;
    }

    public Map<String, Object> board(){
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessGame.createWebBoard().getPiecesForTransfer());
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
            historyDao.insert(start, end);

            if (chessGame.isKingDead()) {
                model.put("winner", chessGame.getAliveKingColor());
                ScoreResult scoreResult = chessGame.calculateScore();
                for (Color color : scoreResult.keySet()) {
                    model.put(color + "score", scoreResult.getScoreBy(color));
                }
                historyDao.clear();

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
