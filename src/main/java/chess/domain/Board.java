package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Grid> board;

    public Board(Map<Position, Grid> board) {
        this.board = board;
    }

    public void movePiece(Color turnColor, Position source, Position target) {
        Grid sourceGrid = board.get(source);
        Piece sourcePiece = sourceGrid.getPiece();

        if (!sourceGrid.hasPieceOf(turnColor)) {
            throw new IllegalArgumentException("올바른 기물 선택이 아닙니다.");
        }
        if (!sourcePiece.isMovable(source, target)) {
            throw new IllegalArgumentException("기물은 해당 위치로 이동할 수 없습니다.");
        }
        List<Position> route = sourcePiece.findRoute(source, target);
        for (Position node : route) {
            Grid nodeGrid = board.get(node);
            if (nodeGrid.hasPiece()) {
                throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
            }
        }
        board.replace(source, new Grid(new Blank()));
        board.replace(target, new Grid(sourcePiece));
    }

    public Map<Position, Grid> getBoard() {
        return board;
    }
}
