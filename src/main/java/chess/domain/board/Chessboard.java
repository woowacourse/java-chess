package chess.domain.board;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.*;
import java.util.stream.Collectors;

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
            board.put(Square.getInstanceOf(file, rank),
                    Empty.getInstanceOf(Camp.NONE));
        }
    }

    public void putPiece(Rank rank, List<Piece> pieces) {
        List<File> values = Arrays.asList(File.values());

        for (int i = 0, end = values.size(); i < end; i++) {
            board.put(Square.getInstanceOf(values.get(i), rank), pieces.get(i));
        }
    }

    public void putPiece(Square square, Piece piece) {
        board.put(square, piece);
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

    public Map<PieceType, Integer> getAlivePieceAndCountMap(Camp camp) {
        return board.values().stream()
                .filter(piece -> piece.isSameCamp(camp))
                .distinct()
                .collect(Collectors.toMap(Piece::getPieceType, this::countSamePieceOnBoard));
    }

    public int countSamePieceOnBoard(Piece targetPiece) {
        return (int) board.values().stream()
                .filter(piece -> piece.equals(targetPiece))
                .count();
    }

    public int countSameCampPawnInFile(Camp camp, File file) {
        Piece targetPawn = PieceType.PAWN.createPiece(camp);

        return (int) Square.getSquaresAt(file).stream()
                .filter(square -> board.get(square).equals(targetPawn))
                .count();
    }

    public Map<Square, Piece> getBoardMap() {
        return Collections.unmodifiableMap(board);
    }
}
