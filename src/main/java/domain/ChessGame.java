package domain;

import domain.piece.Piece;
import domain.piece.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessGame {
    private boolean isEnd;
    private Board board;

    public ChessGame(List<Piece> pieces) {
        this.isEnd = false;
        board = new Board(pieces);
    }

    public void move(Position start, Position end) {
        Piece piece = board.getPiece(end);
        board.move(start, end);
        if (Objects.nonNull(piece) && piece.isKing()) {
            isEnd = true;
        }
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Map<Boolean, Score> piecesScore() {
        ScoreMachine scoreMachine = new ScoreMachine(board.getBoard());
        Map<Boolean, Score> result = new HashMap<>();
        result.put(true, scoreMachine.blackPiecesScore());
        result.put(false, scoreMachine.whitePiecesScore());
        return result;
    }

    public Board getBoard() {
        return board;
    }
}
