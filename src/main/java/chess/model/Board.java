package chess.model;

import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.piece.Pieces;

import java.util.List;
import java.util.Map;

public class Board {

    private final Pieces pieces;
    private Map<Position, Piece> board; //나중에 꼭 final로 바꿔야함

    private Board(Pieces pieces) {
        this.pieces = pieces;
    }

    public static Board create(Pieces pieces) {
        return new Board(pieces);
    }

    public void move(Position source, Position target, Turn thisTurn) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

    }

    public void move(String source, String target, Turn thisTurn) {
        Position sourcePosition = Position.from(source);
        Position targetPosition = Position.from(target);
        Piece sourcePiece = pieces.findByPosition(sourcePosition);
        validateCurrentTurn(thisTurn, sourcePiece);
        Piece targetPiece = pieces.findByPosition(targetPosition);
        if (canMove(sourcePosition, targetPosition, sourcePiece, targetPiece)) {
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

    private boolean canMove(Position sourcePosition, Position targetPosition, Piece sourcePiece, Piece targetPiece) {
        return (sourcePiece.isMovable(sourcePosition, targetPosition) && !hasBlock(sourcePosition, targetPosition, sourcePiece, targetPiece))
                || sourcePiece.isKill(targetPiece);
    }

    private boolean hasBlock(Position source, Position target, Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeam(targetPiece)) {
            return true;
        }
        List<Position> positions = sourcePiece.getIntervalPosition(targetPiece);
        return positions.stream()
                .anyMatch(position -> !pieces.findByPosition(position).equals(new Empty(position)));
    }

    private void validateCurrentTurn(Turn thisTurn, Piece sourcePiece) {
        if (!sourcePiece.isCurrentTurn(thisTurn)) {
            throw new IllegalArgumentException("본인의 말을 움직여야 합니다.");
        }
    }

    public Pieces getPieces() {
        return pieces;
    }
}

