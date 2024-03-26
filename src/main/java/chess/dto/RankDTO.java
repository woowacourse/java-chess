package chess.dto;

import chess.model.board.Board;
import chess.view.PieceSignature;

import java.util.List;
import java.util.stream.IntStream;

public record RankDTO(List<String> line) {
    private static final List<Integer> FILE_ORDERS = IntStream.rangeClosed(Board.MIN_LENGTH, Board.MAX_LENGTH)
            .boxed()
            .toList();

    public static RankDTO of(int rank, Board board) {
        List<String> rankSignatures = FILE_ORDERS.stream()
                .map(file -> board.getPiece(file, rank))
                .map(PieceSignature::getSignature)
                .toList();
        return new RankDTO(rankSignatures);
    }
}
