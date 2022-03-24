package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void validateMovement(Color turnColor, Position source, Position target) {
        Piece sourcePiece = board.get(source);

        if (!sourcePiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("올바른 기물 선택이 아닙니다.");
        }
        if (!sourcePiece.isMovable(source, target)) {
            throw new IllegalArgumentException("기물은 해당 위치로 이동할 수 없습니다.");
        }
        List<Position> route = sourcePiece.findRoute(source, target);
        for (Position node : route) {
            Piece nodePiece = board.get(node);
            if (!nodePiece.isBlank()) {
                throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
            }
        }
    }

    public void movePiece(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        board.replace(source, new Blank());
        board.replace(target, sourcePiece);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
