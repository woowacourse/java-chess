package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class Piece {
    private final PieceType pieceType;
    private final Team team;

    public Piece(final PieceType pieceType, final Team team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public String toSymbol() {
        return this.team.convert(this.pieceType.getSymbol());
    }

    public boolean move(Position source, Position target, Board board) {
        return this.pieceType.move(source, target, board);
    }

    public boolean isEnemy(Piece targetPiece) {
        return this.team.isEnemy(targetPiece.getTeam());
    }

    public boolean isEnemy(Team team) {
        return this.team.isEnemy(team);
    }

    public boolean isWhiteKing() {
        return this.pieceType.isKing() && this.team.isWhite();
    }

    public boolean isBlackKing() {
        return this.pieceType.isKing() && !this.team.isWhite();
    }

    public Team getTeam() {
        return this.team;
    }

    public double getScore() {
        return this.pieceType.getScore();
    }

    public boolean isPawn() {
        return this.pieceType.isPawn();
    }
}
