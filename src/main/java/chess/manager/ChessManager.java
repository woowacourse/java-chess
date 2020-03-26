package chess.manager;

import chess.board.ChessBoard;
import chess.board.piece.Team;

public class ChessManager {
    private final ChessBoard chessBoard;
    private Team currentTeam = Team.WHITE;

    public ChessManager(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean move(String source, String target) {
        if (chessBoard.isNotSameTeam(source, currentTeam)) {
            return false;
        }
        boolean move = chessBoard.move(source, target);
        if (move) {
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
}
