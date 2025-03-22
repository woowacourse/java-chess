package chess.domain.game;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.TeamColor;
import chess.domain.piece.Piece;

public class BlackTurn implements Turn{
    private final ChessBoard board;
    private final TeamColor turnColor;

    public BlackTurn(ChessBoard board) {
        this.board = board;
        this.turnColor = TeamColor.BLACK;
    }

    @Override
    public Turn movePiece(Position start, Position target) {
        Piece startPiece = board.findPieceBy(start);
        if(startPiece.isOtherTeamColor(turnColor)) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }

        Piece targetPiece = board.findPieceBy(target);
        board.move(start, target);
        if(targetPiece.isKing()) {
            return new Finished(turnColor);
        }

        return new WhiteTurn(board);
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
