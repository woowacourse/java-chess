package domain;

import domain.piece.ChessBoardGenerator;
import domain.piece.Piece;
import domain.position.Position;
import dto.BoardStatus;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(final ChessBoardGenerator chessBoardGenerator) {
        this(chessBoardGenerator.generate());
    }

    public ChessBoard(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public BoardStatus status() {
        return BoardStatus.from(board);
    }

    public void move(String from, String to) {
        Position source = new Position(from);
        Position target = new Position(to);

        Piece sourcePiece = board.get(source);
        if (!sourcePiece.isMovable(source, target) || board.containsKey(target) || source.equals(target)) {
            throw new IllegalArgumentException("이동이 불가능합니다.");
        }
        board.put(target, sourcePiece);
        board.remove(source);
    }
}
