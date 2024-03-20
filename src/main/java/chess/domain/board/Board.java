package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Square;
import chess.dto.BoardOutput;
import chess.dto.SquareDifferent;

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

        SquareDifferent diff = source.calculateDiff(destination);
        Square candidate = source;

        while (!candidate.equals(destination)) {
            if (!source.equals(candidate) && !board.get(candidate).isEmpty()) {
                throw new IllegalArgumentException("막힌 경로입니다.");
            }

            if (diff.fileDiff() == 0 && diff.rankDiff() > 0) {
                candidate = candidate.moveVertical(1);
                continue;
            }

            if (diff.fileDiff() == 0 && diff.rankDiff() < 0) {
                candidate = candidate.moveVertical(-1);
                continue;
            }

            if (diff.fileDiff() < 0 && diff.rankDiff() == 0) {
                candidate = candidate.moveHorizontal(1);
                continue;
            }

            if (diff.fileDiff() > 0 && diff.rankDiff() == 0) {
                candidate = candidate.moveHorizontal(-1);
                continue;
            }

            if (diff.fileDiff() < 0 && diff.rankDiff() < 0) {
                candidate = candidate.moveDiagonal(1, -1);
                continue;
            }

            if (diff.fileDiff() < 0 && diff.rankDiff() > 0) {
                candidate = candidate.moveDiagonal(1, 1);
                continue;
            }

            if (diff.fileDiff() > 0 && diff.rankDiff() < 0) {
                candidate = candidate.moveDiagonal(-1, -1);
                continue;
            }

            if (diff.fileDiff() > 0 && diff.rankDiff() > 0) {
                candidate = candidate.moveDiagonal(-1, 1);
            }
        }

        board.replace(source, destinationPiece);
        board.replace(destination, sourcePiece);
    }

    private void checkSameColor(Piece sourcePiece, Piece destinationPiece) {
        if (sourcePiece.isSameColor(destinationPiece)) {
            throw new IllegalArgumentException("목적지에 같은 편 말이 있어 이동할 수 없습니다.");
        }
    }
}
