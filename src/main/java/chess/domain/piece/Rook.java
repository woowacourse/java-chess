package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.State;
import chess.domain.TeamColor;
import java.util.HashSet;

public class Rook implements Piece {

    private final TeamColor teamColor;
    private State state = State.ALIVE;

    private Position position;

    private String pieceType = "r";

    public Rook(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public boolean isMoveAble(Position source, Position target, ChessBoard chessBoard) {
        HashSet<Position> candidates = new HashSet<>();
        Position position = source.moveUp();
        while (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
            position = position.moveUp();

        }
        position = source.moveDown();
        while (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
            position = position.moveDown();
        }
        position = source.moveLeft();
        while (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
            position = position.moveLeft();
        }
        position = source.moveRight();
        while (position != Position.ERROR && chessBoard.isBlank(position) ||
            (position == target && isEnemyTeam(chessBoard.getPiece(position)))) {
            candidates.add(position);
            position = position.moveRight();
        }

        return candidates.contains(target);
    }

    @Override
    public void dead() {
        this.state = State.DEAD;
    }

    @Override
    public State getState() {
        return state;
    }


    @Override
    public String getPieceName() {
        if (teamColor == TeamColor.BLACK) {
            return pieceType.toUpperCase();
        }
        return pieceType.toLowerCase();
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
    public String toString() {
        return teamColor.name() + " Rock\n";
    }


}
