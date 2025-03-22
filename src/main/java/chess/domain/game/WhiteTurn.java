package chess.domain.game;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.TeamColor;

public class WhiteTurn implements Turn{
    private final ChessBoard board;
    private final TeamColor turnColor;

    public WhiteTurn(ChessBoard board) {
        this.board = board;
        this.turnColor = TeamColor.WHITE;
    }

    @Override
    public Turn movePiece(Position start, Position target) {
        board.move(start, target);

        return new BlackTurn(board);
    }

    @Override
    public TeamColor getTeamColor() {
        return turnColor;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
