package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
        BoardFactory boardFactory = new BoardFactory();
        boardFactory.initialize(this);
    }

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePiece(Position from, Position to) {
        Piece piece = board.get(from);
        board.put(to, piece);
        board.remove(from);
    }

    public Piece findPieceByPosition(Position position) {
        if (existPiece(position)) {
            return board.get(position);
        }
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    public boolean isEmptySpace(Position position) {
        return !existPiece(position);
    }

    public boolean existPiece(Position position) {
        return board.containsKey(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    void put(Position position, Piece piece) {
        board.put(position, piece);
    }
}
