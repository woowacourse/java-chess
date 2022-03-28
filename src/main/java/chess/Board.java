package chess;

import chess.piece.Empty;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.position.Position;

import java.util.List;

public final class Board {

    private final Pieces pieces;

    private Board(Pieces pieces) {
        this.pieces = pieces;
    }

    public static Board create(Pieces pieces) {
        return new Board(pieces);
    }

    public Pieces getPieces() {
        return pieces;
    }

    public void move(List<String> commandPosition, Turn thisTurn) {
        Position sourcePosition = getSource(commandPosition);
        Position targetPosition = getTarget(commandPosition);

        Piece sourcePiece = pieces.findByPosition(sourcePosition);
        Piece targetPiece = pieces.findByPosition(targetPosition);
        validateTurn(thisTurn, sourcePiece);
        if (canMove(targetPosition, sourcePiece, targetPiece)) {
            move(sourcePosition, targetPosition, sourcePiece, targetPiece);
            return;
        }
        throw new IllegalArgumentException("움직일수 없습니다.");
    }

    private void validateTurn(Turn thisTurn, Piece sourcePiece) {
        if (!sourcePiece.isCurrentTurn(thisTurn)) {
            throw new IllegalArgumentException("[ERROR] 현재 차례가 아닙니다.");
        }
    }

    private boolean canMove(Position targetPosition, Piece sourcePiece, Piece targetPiece) {
        return (sourcePiece.isMovable(targetPosition) && !hasBlock(sourcePiece, targetPiece)) ||
                sourcePiece.isKill(targetPiece);
    }

    private void move(Position sourcePosition, Position targetPosition, Piece sourcePiece, Piece targetPiece) {
        sourcePiece.moveTo(targetPosition);
        pieces.remove(targetPiece);
        pieces.add(new Empty(sourcePosition));
    }

    private boolean hasBlock(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeam(targetPiece)) {
            return true;
        }
        List<Position> positions = sourcePiece.getIntervalPosition(targetPiece);
        return positions.stream()
                .anyMatch(position -> !pieces.findByPosition(position).equals(new Empty(position)));
    }

    private Position getSource(List<String> commandPosition) {
        return Position.of(
                commandPosition.get(0).charAt(0),
                commandPosition.get(0).charAt(1));
    }

    private Position getTarget(List<String> commandPosition) {
        return Position.of(
                commandPosition.get(1).charAt(0),
                commandPosition.get(1).charAt(1)
        );
    }

    public boolean isDeadKing() {
        return pieces.countOfKing() == 1;
    }

    public double getWhiteTeamScore() {
        return pieces.getTotalScore(Team.WHITE);
    }

    public double getBlackTeamScore() {
        return pieces.getTotalScore(Team.BLACK);
    }

    public Team getWinTeam() {
        if (getWhiteTeamScore() > getBlackTeamScore()) {
            return Team.WHITE;
        }
        if (getBlackTeamScore() > getWhiteTeamScore()) {
            return Team.BLACK;
        }
        return Team.NONE;
    }
}

