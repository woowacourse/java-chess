package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.State;
import chess.domain.TeamColor;
import chess.domain.player.Score;
import chess.domain.position.Moves;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public abstract class PieceOnBoard implements Piece {

    private final TeamColor teamColor;
    private final PieceInformation pieceType;
    private State state;
    private Position currentPosition;

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = State.ALIVE;
    }

    public PieceOnBoard(TeamColor teamColor, PieceInformation pieceType, Position currentPosition) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
        this.state = State.ALIVE;
        this.currentPosition = currentPosition;
    }

    @Override
    public String getPieceName() {
        if (teamColor == TeamColor.BLACK) {
            return pieceType.getName().toUpperCase();
        }
        return pieceType.getName().toLowerCase();
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
    public Character getColumn() {
        return currentPosition.getColumn();
    }

    @Override
    public void dead() {
        state = State.DEAD;
    }

    @Override
    public State getState() {
        return state;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Score getScore() {
        return new Score(pieceType.getScore());
    }

    @Override
    public void setCurrentPosition(Position end) {
        currentPosition = end;
    }

    @Override
    public String toString() {
        return teamColor.name() + "" + pieceType + "\n";
    }

    protected Set<Position> moveAsPossible(Moves direction, Position target, ChessBoard chessBoard){
        Set<Position> candidates = new HashSet<>();
        Position position = direction.move(currentPosition);
        while (movable(position, target, chessBoard)) {
            candidates.add(position);
            position = direction.move(position);
        }
        return candidates;
    }


    protected Position moveOnce(Moves direction, Position target, ChessBoard chessBoard){
        Position position = direction.move(currentPosition);
        if (movable(position, target, chessBoard)) {
            return position;
        }
        return Position.ERROR;
    }

    public boolean movable(Position position, Position target, ChessBoard chessBoard) {
        return validBlank(position, chessBoard) || isMeetEnemy(position, target, chessBoard);
    }

    protected boolean isMeetEnemy(Position position, Position target,
        ChessBoard chessBoard) {
        return position == target && isEnemyTeam(chessBoard.getPiece(position));
    }

    protected boolean validBlank(Position position, ChessBoard chessBoard) {
        return position != Position.ERROR && chessBoard.isBlank(position);
    }

}
