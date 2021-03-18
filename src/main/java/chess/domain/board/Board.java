package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Piece;

import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board){
        this.board = board;
    }

    public Piece of(Position position) {
        return board.get(position);
    }

    public Piece of(Vertical vertical, Horizontal horizontal) {
        return of(new Position(vertical, horizontal));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void move(Position source, Position target){
        // 중간에 아무것도 없다는거 확인
        Piece sourcePiece = of(source);
        Piece targetPiece = of(target);

        sourcePiece.validateMove(source, target, targetPiece);
    }
}
