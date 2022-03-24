package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public class Board {
    private final Grid[][] board;

    public Board(Grid[][] board) {
        this.board = board;
    }

    public void movePiece(Color turnColor, Position source, Position target) {
        Grid sourceGrid = board[source.getPositionY().getCoordination()][source.getPositionX().getCoordination()];
        Piece sourcePiece = sourceGrid.getPiece();

        if (!sourceGrid.hasPieceOf(turnColor)) {
            throw new IllegalArgumentException("올바른 기물 선택이 아닙니다.");
        }
        if (!sourcePiece.isMovable(source, target)) {
            throw new IllegalArgumentException("기물은 해당 위치로 이동할 수 없습니다.");
        }
        List<Position> route = sourcePiece.findRoute(source, target);
        for (Position node : route) {
            Grid nodeGrid = board[node.getPositionY().getCoordination()][node.getPositionX().getCoordination()];
            if(nodeGrid.hasPiece()){
                throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
            }
        }
        board[source.getPositionY().getCoordination()][source.getPositionX().getCoordination()] = new Grid(new Blank());
        board[target.getPositionY().getCoordination()][target.getPositionX().getCoordination()] = new Grid(sourcePiece);
    }

    public Grid[][] getBoard() {
        return board;
    }
}
