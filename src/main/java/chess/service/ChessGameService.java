package chess.service;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;

import java.util.List;

public class ChessGameService {
    private final ChessGame chessGame;
    
    public ChessGameService(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
    
    public void newChessGame() {
        chessGame.newGame();
    }
    
    public ChessBoard chessBoard() {
        return chessGame.chessBoard();
    }
    
    public void move(List<String> inputCommand) {
        chessGame.move(inputCommand);
    }
    
    public boolean isChessBoardNotInitialized() {
        return chessGame.isChessBoardNotInitialized();
    }
    
    public double getWhiteTeamScore() {
        return chessGame.getWhiteTeamScore();
    }
}
