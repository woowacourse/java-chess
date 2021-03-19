package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.State;
import chess.domain.TeamColor;

public abstract class PieceOnBoard implements Piece {

    private TeamColor teamColor;
    private PieceInformation pieceType;
    private State state;
    private Position position;

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = State.ALIVE;
    }

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType, Position position) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = State.ALIVE;
        this.position = position;
    }

    @Override
    public String getPieceName() {
        if (teamColor == TeamColor.BLACK) {
            return pieceType.getName().toUpperCase();
        }
        return pieceType.getName().toLowerCase();
    }

    public boolean movable(Position position, Position target, ChessBoard chessBoard) {
        return validBlank(position, chessBoard) || isMeetEnemy(position, target, chessBoard);
    }

    // isTargetOrEnemy
    protected boolean isMeetEnemy(Position position, Position target,
        ChessBoard chessBoard) {
        return position == target && isEnemyTeam(chessBoard.getPiece(position));
    }

    protected boolean validBlank(Position position, ChessBoard chessBoard) {
        return position != Position.ERROR && chessBoard.isBlank(position);
    }

    @Override
    public boolean isEnemyTeam(Piece comparePiece) {
        return teamColor != comparePiece.getColor();
    }

    @Override
    public TeamColor getColor() {
        return teamColor;
    }

    @Override
    public void dead() {
        state = State.DEAD;

    }

    @Override
    public State getState() {
        return state;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return teamColor.name() + "" + pieceType + "\n";
    }

}
