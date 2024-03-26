package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.view.mapper.PieceMapper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String resolveBoardMessage(List<Piece> pieces) {
        return Arrays.stream(Rank.values())
                .map(rank -> rankPieces(rank, pieces))
                .map(this::resolvePiecesMessage)
                .collect(Collectors.joining(LINE_SEPARATOR, "", LINE_SEPARATOR));
    }

    private List<Piece> rankPieces(Rank rank, List<Piece> pieces) {
        int fromIndex = rank.ordinal() * File.length();
        int toIndex = fromIndex + File.length();
        return pieces.subList(fromIndex, toIndex);
    }

    private String resolvePiecesMessage(List<Piece> pieces) {
        return pieces.stream()
                .map(PieceMapper::toText)
                .collect(Collectors.joining(""));
    }
}
