package chess.view;

import chess.domain.board.Board;
import chess.view.mapper.PieceMapper;
import chess.domain.piece.Piece;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String resolveBoardMessage(Board board) {
        String message = board.pieces().stream()
                .map(this::resolvePieceMessage)
                .collect(Collectors.joining(""));

        StringBuilder result = new StringBuilder();

        IntStream.range(0, message.length())
                .mapToObj(i -> message.charAt(i) + (i % 8 == 7 ? LINE_SEPARATOR : ""))
                .forEach(result::append);
        return result.toString();
    }

    private String resolvePieceMessage(Piece piece) {
        return PieceMapper.toSymbol(piece);
    }
}
