package chess.manager;

import chess.board.ForwardChessBoard;
import chess.board.Tile;
import chess.coordinate.Coordinate;
import chess.piece.Team;

import java.util.Map;

public class ChessManager {
    private final ForwardChessBoard chessBoard;
    private Team currentTeam = Team.WHITE;

    public ChessManager(final ForwardChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean move(String source, String target) {
        if (chessBoard.isNotSameTeam(source, currentTeam)) {
            return false;
        }
        boolean moveResult = chessBoard.move(source, target);
        if (moveResult) {
            turnOver();
        }
        return moveResult;
    }

    private void turnOver() {
        if (currentTeam == Team.WHITE) {
            currentTeam = Team.BLACK;
            return;
        }
        currentTeam = Team.WHITE;
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return chessBoard.getChessBoard();
    }

    public double calculateCurrentTeamScore() {
        return this.chessBoard.calculateScore(this.currentTeam);
    }


    public Team getCurrentTeam() {
        return currentTeam;
    }
}
