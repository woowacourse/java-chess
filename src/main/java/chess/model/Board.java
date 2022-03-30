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

    public void move(String source, String target, Turn thisTurn) {
        Position sourcePosition = Position.from(source);
        Position targetPosition = Position.from(target);

        Piece sourcePiece = pieces.findByPosition(sourcePosition);
        if (!sourcePiece.isCurrentTurn(thisTurn)) {
            throw new IllegalArgumentException("본인의 말을 움직여야 합니다.");
        }
        Piece targetPiece = pieces.findByPosition(targetPosition);
        if ((sourcePiece.isMovable(targetPosition) && !hasBlock(sourcePiece, targetPiece))
                || sourcePiece.isKill(targetPiece)) {
            sourcePiece.moveTo(targetPiece);
            pieces.arrange(targetPiece, sourcePosition);
            return;
        }
        throw new IllegalArgumentException("움직일 수 없습니다.");
    }

    public boolean isKingDead() {
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

    private boolean hasBlock(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeam(targetPiece)) {
            return true;
        }
        List<Position> positions = sourcePiece.getIntervalPosition(targetPiece);
        return positions.stream()
                .anyMatch(position -> !pieces.findByPosition(position).equals(new Empty(position)));
    }

    public Pieces getPieces() {
        return pieces;
    }
}

