package chess.domain.piece;

import chess.domain.Position;
import chess.domain.XPosition;
import chess.domain.board.ChessBoard;
import chess.domain.move.MoveType;
import chess.domain.team.TeamStrategy;

public abstract class Piece implements PieceAbility {
    public Position position;
    final TeamStrategy teamStrategy;

    public Piece(Position position, TeamStrategy teamStrategy) {
        this.position = position;
        this.teamStrategy = teamStrategy;
    }

    public boolean isEqualPosition(Position position) {
        return this.position.equals(position);
    }

    public void move(MoveType moveType, ChessBoard chessBoard) {
        position.move(moveType, chessBoard);
    }

    public boolean isSameTeam(Piece targetPiece) {
        if (targetPiece == null) {
            return false;
        }
        return teamStrategy.equals(targetPiece.teamStrategy);
    }

    public boolean isSameFile(XPosition XPosition) {
        return this.position.isSameFile(XPosition);
    }
}

