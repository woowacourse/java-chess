package chess.domain.board;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chessboard {
    private final Map<Square, Piece> board;

    public Chessboard() {
        this.board = new HashMap<>();
        for (File file : File.values()) {
            putFile(file);
        }
    }

    private void putFile(File file) {
        for (Rank rank : Rank.values()) {
            board.put(new Square(file, rank), new Empty(Camp.NONE));
        }
    }

    public void putPiece(Rank rank, List<Piece> pieces) {
        List<File> values = Arrays.asList(File.values());
        for (int i = 0, end = values.size(); i < end; i++) {
            board.put(new Square(values.get(i), rank), pieces.get(i));
        }
    }

    public Piece getPieceAt(Square square) {
        return board.get(square);
    }

    public void swapPiece(Square source, Square target) {
        board.put(target, board.get(source));
        board.put(source, PieceType.EMPTY.createPiece(Camp.NONE));
    }

    public boolean isEmptyInRoute(Square source, Square target) {
        return getMovableRoute(source, target)
                .stream()
                .filter(square -> board.get(square).getPieceType() != PieceType.EMPTY)
                .findAny()
                .isEmpty();
    }

    private List<Square> getMovableRoute(Square source, Square target) {
        if (source.isSameFile(target)) {
            return source.getSquaresInSameFile(target);
        }

        if (source.isSameRank(target)) {
            return source.getSquaresInSameRank(target);
        }

        return source.getDiagonalSquares(target);
    }

    public double countScore(Camp camp) {
        return board.values().stream()
                .filter(piece -> piece.isCamp(camp))
                .map(Piece::getScore)
                .reduce(0.0, Double::sum);
    }
}
