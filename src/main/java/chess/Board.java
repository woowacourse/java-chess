package chess;

import chess.piece.*;

import java.util.List;

public class Board {

    private final Pieces pieces;

    private Board(Pieces pieces) {
        this.pieces = pieces;
    }

    public static Board create(Pieces pieces) {
        return new Board(pieces);
    }

    public Pieces getBoard() {
        return pieces;
    }

    public void move(List<String> commandPosition) {
        Position sourcePosition = getSource(commandPosition);
        Position targetPosition = getTarget(commandPosition);

        Piece sourcePiece = pieces.findByPosition(sourcePosition);
        Piece targetPiece = pieces.findByPosition(targetPosition);
        if (sourcePiece.isMovable(targetPosition) && !hasBlock(sourcePiece, targetPiece)) {
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
}

