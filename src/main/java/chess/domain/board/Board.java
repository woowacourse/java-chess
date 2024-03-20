package chess.domain.board;

import chess.domain.position.Square;
import chess.domain.piece.Piece;
import chess.dto.BoardOutput;

import java.util.Map;

public class Board {

    private final Map<Square, Piece> board;

    public Board() {
        this.board = new BoardFactory().create();
    }

    public BoardOutput toBoardOutput() {
        return new BoardOutput(board);
    }

    public void move(Square source, Square destination) {
        Piece sourcePiece = board.get(source);
        Piece destinationPiece = board.get(destination);

        checkSameColor(sourcePiece, destinationPiece);
    }

    private void checkSameColor(Piece sourcePiece, Piece destinationPiece) {
        if (sourcePiece.isSameColor(destinationPiece)) {
            throw new IllegalArgumentException("목적지에 같은 편 말이 있어 이동할 수 없습니다.");
        }
    }
}
