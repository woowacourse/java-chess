package chess.domain.game;

import chess.domain.board.BlackWhiteChessBoard;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;

import java.util.List;
import java.util.Objects;

public class ChessGame {
    private static final int FROM_COORDINATE_INDEX = 1;
    private static final int TO_COORDINATE_INDEX = 2;
    
    private ChessBoard chessBoard;
    private Team currentTeam;
    
    public void newGame() {
        chessBoard = BlackWhiteChessBoard.create();
        currentTeam = Team.WHITE;
    }
    
    public void move(List<String> inputCommand) {
        chessBoard.move(inputCommand.get(FROM_COORDINATE_INDEX), inputCommand.get(TO_COORDINATE_INDEX), currentTeam);
        currentTeam = currentTeam.nextTeam();
    }
    
    public boolean isChessBoardNotInitialized() {
        return Objects.isNull(chessBoard);
    }
    
    public ChessBoard chessBoard() {
        return chessBoard;
    }
    
    public boolean isKingDied() {
        return chessBoard.isKingDied();
    }
    
    public Team teamWithKing() {
        return chessBoard.teamWithKing();
    }
}
