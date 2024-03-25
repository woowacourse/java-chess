package chess.model.board;

import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.piece.None;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieces;
    private int turnCount;

    public Board(Map<Position, Piece> pieces, int turnCount) {
        this.pieces = pieces;
        this.turnCount = turnCount;
    }

    public void move(String sourceCoordinate, String targetCoordinate) {
        Position source = Position.from(sourceCoordinate);
        Position target = Position.from(targetCoordinate);
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);

        validate(sourcePiece, targetPiece);
        sourcePiece.move(source, target, pieces);

        pieces.put(target, sourcePiece);
        pieces.put(source, new None(Color.NONE));
        turnCount++;
    }

    private void validate(Piece sourcePiece, Piece targetPiece) {
        validatePiecesPosition(sourcePiece, targetPiece);
        validateTurn(sourcePiece);
    }

    private void validatePiecesPosition(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isNone()) {
            throw new IllegalArgumentException("source위치에 기물이 존재하지 않습니다.");
        }
        if (targetPiece.isAlly(sourcePiece)) {
            throw new IllegalArgumentException("target위치에 내 기물이 존재합니다.");
        }
    }

    private void validateTurn(Piece sourcePiece) {
        boolean isEnemy = sourcePiece.isEnemyTurn(turnCount);
        if (isEnemy && sourcePiece.isSameColor(Color.WHITE)) {
            throw new IllegalArgumentException("지금은 Black 차례입니다.");
        }
        if (isEnemy && sourcePiece.isSameColor(Color.BLACK)) {
            throw new IllegalArgumentException("지금은 White 차례입니다.");
        }
    }

    public Piece findPiece(Position position) {
        return pieces.get(position);
    }
}
