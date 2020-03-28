package chess.manager;

import chess.board.ChessBoard;
import chess.board.MoveResult;
import chess.piece.Team;

public class ChessManager {
    private final ChessBoard chessBoard;
    private Team currentTeam = Team.WHITE;

    public ChessManager(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public MoveResult move(String source, String target) {
        if (chessBoard.isNotSameTeam(source, currentTeam)) {
            return MoveResult.FAIL;
        }
        MoveResult move = chessBoard.move(source, target);
        if (move == MoveResult.SUCCESS) {
            turnOver();
        }
        return move;
    }

    private void turnOver() {
        if (currentTeam == Team.WHITE) {
            currentTeam = Team.BLACK;
            return;
        }
        currentTeam = Team.WHITE;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public double calculateCurrentTeamScore() {
        return this.chessBoard.calculateScore(this.currentTeam);
    }


    public Team getCurrentTeam() {
        return currentTeam;
    }
}
