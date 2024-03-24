package chess.domain.strategy;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.blank.Blank;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class MoveStrategy {
    protected final Map<Position, Piece> board;

    public MoveStrategy(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public MoveStrategy changeStrategy(Position source) {
        PieceType pieceType = board.get(source).pieceType();
        if (pieceType == PieceType.BLANK) {
            return new BlankMoveStrategy(board);
        }
        if (pieceType == PieceType.BLACK_PAWN || pieceType == PieceType.WHITE_PAWN) {
            return new PawnMoveStrategy(board);
        }
        return new GeneralMoveStrategy(board);
    }

    public abstract void move(Color turnColor, Position source, Position destination);

    public void checkTurnOf(Piece currentPiece, Color turnColor) {
        if (!currentPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("상대 말은 이동할 수 없습니다.");
        }
    }

    protected boolean isAllBlankCourses(Set<Position> movablePositions) {
        return movablePositions.stream()
                .map(board::get)
                .allMatch(piece -> piece.pieceType() == PieceType.BLANK);
    }

    protected void updateBoard(Position source, Position destination, Piece currentPiece) {
        board.replace(destination, currentPiece.update(destination));
        board.replace(source, new Blank(source));
    }

    public Map<Position, PieceType> collectBoard() {
        return board.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().pieceType()
                ));
    }
}
