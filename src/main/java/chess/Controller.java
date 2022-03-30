package chess;

import chess.domain.Camp;
import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class Controller {

    public Map<Position, Piece> start(ChessGame chessGame) {
        chessGame.start();
        return chessGame.getBoard().getSquares();
    }

    public Map<Position, Piece> move(ChessGame chessGame, Position sourcePosition, Position targetPosition) {
        chessGame.move(sourcePosition, targetPosition);
        return chessGame.getBoard().getSquares();
    }

    public Map<Camp, Score> status(ChessGame chessGame) {
        return chessGame.getScores();
    }

    public void end(ChessGame chessGame) {
        chessGame.end();
    }

    public Camp getWinner(ChessGame chessGame) {
        return chessGame.getWinner();
    }
}
