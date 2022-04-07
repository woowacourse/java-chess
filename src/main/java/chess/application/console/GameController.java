package chess.application.console;

import chess.domain.Camp;
import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.domain.gamestate.Score;
import chess.domain.piece.Piece;
import java.util.Map;

public class GameController {
    private final ChessGame chessGame;

    public GameController() {
        this.chessGame = new ChessGame();
    }

    public Map<Position, Piece> start() {
        chessGame.start();
        return chessGame.getBoardSquares();
    }

    public Map<Position, Piece> move(Position sourcePosition, Position targetPosition) {
        chessGame.move(sourcePosition, targetPosition);
        return chessGame.getBoardSquares();
    }

    public Map<Camp, Score> status() {
        return chessGame.getScores();
    }

    public void end() {
        chessGame.end();
    }

    public boolean isGameFinished() {
        return this.chessGame.isFinished();
    }

    public Camp getWinner() {
        return chessGame.getWinner();
    }

    public Map<Position, Piece> getBoard() {
        return chessGame.getBoardSquares();
    }
}
