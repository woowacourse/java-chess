package view;

import domain.board.Board;
import domain.piece.Piece;
import domain.square.File;
import domain.square.Square;
import domain.square.Rank;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.mapper.PieceMapper;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPTY = ".";

    public String resolveBoardMessage(Board board) {
        String message = board.pieces().stream()
                .map(this::resolvePieceMessage)
                .collect(Collectors.joining(""));

        StringBuilder result = new StringBuilder();

        IntStream.range(0, message.length())
                .mapToObj(i -> message.charAt(i) + (i % 8 == 7 ? "\n" : ""))
                .forEach(result::append);
        return result.toString();
    }

    private String resolvePieceMessage(Piece piece) {
        return PieceMapper.toSymbol(piece);
    }

    private StringBuilder resolveRankMessage(Rank rank, Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int horizontal = 1; horizontal <= File.max(); horizontal++) {
            Square position = new Square(File.find(horizontal), rank);
            String square = resolveSquareMessage(board, position);
            stringBuilder.append(square);
        }
        stringBuilder.append(LINE_SEPARATOR);
        return stringBuilder;
    }

    private String resolveSquareMessage(Board board, Square square) {
        if (board.hasPiece(square)) {
            Piece piece = board.findPiece(square);
            return PieceMapper.toSymbol(piece);
        }
        return EMPTY;
    }
}
