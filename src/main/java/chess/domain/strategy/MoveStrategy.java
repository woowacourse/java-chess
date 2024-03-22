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

    public MoveStrategy changeStrategy(Position from) {
        Piece selectedPiece = board.get(from);
        return selectedPiece.strategy(board);
    }

    public abstract void move(Color turnColor, Position from, Position to);

    public void checkTurnOf(Piece currentPiece, Color turnColor) {
        if (!currentPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("상대 말은 이동할 수 없습니다.");
        }
    }

    protected boolean isAllBlankCourses(Set<Position> path) {
        return path.stream()
                .map(board::get)
                .allMatch(Piece::isBlank);
    }

    protected void updateBoard(Position from, Position to, Piece currentPiece) {
        board.replace(to, currentPiece.update(to));
        board.replace(from, new Blank(from));
    }

    public Map<Position, PieceType> collectBoard() {
        return board.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().pieceType()
                ));
    }
}
