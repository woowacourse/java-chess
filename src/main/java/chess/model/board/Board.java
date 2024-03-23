package chess.model.board;

import chess.model.Position;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
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

        validate(sourcePiece, targetPiece, source, target);

        pieces.put(target, sourcePiece);
        pieces.put(source, Piece.from(PieceType.NONE));
        turnCount++;
    }

    private void validate(Piece sourcePiece, Piece targetPiece, Position source, Position target) {
        validatePiecesPosition(sourcePiece, targetPiece);
        validateTurn(sourcePiece);
        validatePieceCanMove(sourcePiece, targetPiece, source, target);
        validatePieceRoute(sourcePiece, source, target);
    }

    private void validatePiecesPosition(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isNone()) {
            throw new IllegalArgumentException("source위치에 기물이 존재하지 않습니다.");
        }
        if (targetPiece.isAlly(turnCount)) {
            throw new IllegalArgumentException("target위치에 내 기물이 존재합니다.");
        }
    }

    private void validateTurn(Piece sourcePiece) {
        boolean isEnemy = sourcePiece.isEnemy(turnCount);
        if (isEnemy && sourcePiece.isWhite()) {
            throw new IllegalArgumentException("지금은 Black 차례입니다.");
        }
        if (isEnemy && sourcePiece.isBlack()) {
            throw new IllegalArgumentException("지금은 White 차례입니다.");
        }
    }

    private void validatePieceCanMove(Piece sourcePiece, Piece targetPiece, Position source,
        Position target) {
        if (targetPiece.isEnemy(turnCount) && sourcePiece.isPawn()
            && ((Pawn) sourcePiece).canAttack(source, target)) {
            return;
        }
        if (!sourcePiece.canMove(source, target)) {
            throw new IllegalArgumentException("target위치로 기물을 이동할 수 없습니다.");
        }
    }

    private void validatePieceRoute(Piece sourcePiece, Position source, Position target) {
        if (sourcePiece.isKnight()) {
            return;
        }
        int rowDifference = target.getRow() - source.getRow();
        int columnDifference = target.getColumn() - source.getColumn();

        while (Math.abs(rowDifference) > 1 || Math.abs(columnDifference) > 1) {
            rowDifference = consumeRow(rowDifference);
            columnDifference = consumeColumn(columnDifference);
            Position position = new Position(source.getRow() + rowDifference,
                source.getColumn() + columnDifference);
            Piece targetPiece = pieces.get(position);
            if (targetPiece.isExist()) {
                throw new IllegalArgumentException("경로 상에 다른 기물이 존재합니다.");
            }
        }
    }

    private int consumeRow(int rowDifference) {
        if (rowDifference > 0) {
            rowDifference--;
        }
        if (rowDifference < 0) {
            rowDifference++;
        }
        return rowDifference;
    }

    private int consumeColumn(int columnDifference) {
        if (columnDifference > 0) {
            columnDifference--;
        }
        if (columnDifference < 0) {
            columnDifference++;
        }
        return columnDifference;
    }

    public Piece findPiece(Position position) {
        return pieces.get(position);
    }
}
