package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.state.TeamColor;

public class Bishop extends PieceOnBoard {

    public Bishop(TeamColor teamColor) {
        super(teamColor, PieceInformation.BISHOP);
    }

    public Bishop(TeamColor teamColor, Position position) {
        super(teamColor, PieceInformation.BISHOP, position);
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {

        return moveDiagonal(source, target, chessBoard).contains(target);
    }

}
