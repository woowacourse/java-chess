package controller;

import domain.Status;
import domain.chessgame.ChessBoard;
import domain.chessgame.ChessGame;
import domain.piece.Piece;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import utils.ChessBoardGenerator;

public class WebChessController {

    private ChessGame chessGame;

    public WebChessController() {
    }

    public void start() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessGame = new ChessGame(chessBoard);
    }

    public Map<String, Object> modelBoard() {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> board = chessGame.getChessBoard().getBoard();
        for (Position position : board.keySet()) {
            model.put(position.getPosition(), board.get(position).symbolByPlayer());
        }
        model.put("player",chessGame.getCurrentPlayer());
        return model;
    }

    public Map<String, Object> status() {
        Map<String, Object> model = modelBoard();
        Status status = chessGame.status();
        model.put("winner", status.winner());
        model.put("whiteScore", status.getWhiteScore());
        model.put("blackScore", status.getBlackScore());
        return model;
    }

    public boolean move(String source, String target) {
        String[] sources = source.split("");
        String[] targets = target.split("");
        Position sourcePosition = Position.of(sources[0], sources[1]);
        Position targetPosition = Position.of(targets[0], targets[1]);
        chessGame.move(sourcePosition, targetPosition);
        return chessGame.isFinished();
    }
}
