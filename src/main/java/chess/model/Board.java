package chess.model;

import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.piece.Pieces;

import java.util.List;

public class Board {

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

    public void move(String source, String target, Turn thisTurn) {
        Position sourcePosition = getSource(source);
        Position targetPosition = getTarget(target);

        Piece sourcePiece = pieces.findByPosition(sourcePosition);
        if (!sourcePiece.isCurrentTurn(thisTurn)) {
            throw new IllegalArgumentException("움직일 수 없습니다");
        }
        Piece targetPiece = pieces.findByPosition(targetPosition);
        if ((sourcePiece.isMovable(targetPosition) && !hasBlock(sourcePiece, targetPiece)) || sourcePiece.isKill(targetPiece)) {
            sourcePiece.moveTo(targetPiece);
            pieces.remove(targetPiece);
            pieces.add(new Empty(sourcePosition));
            return;
        }
        throw new IllegalArgumentException("움직일수 없습니다.");
    }

    private boolean hasBlock(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeam(targetPiece)) {
            return true;
        }
        List<Position> positions = sourcePiece.getIntervalPosition(targetPiece);
        return positions.stream()
                .anyMatch(position -> !pieces.findByPosition(position).equals(new Empty(position)));
    }

    private Position getSource(String commandPosition) {
        return Position.of(
                commandPosition.charAt(0),
                commandPosition.charAt(1));
    }

    private Position getTarget(String commandPosition) {
        return Position.of(
                commandPosition.charAt(0),
                commandPosition.charAt(1)
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

