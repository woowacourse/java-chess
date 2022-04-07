package chess.web.dto;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.IntStream;

public class BoardResponse {

    private static final int START_INCLUSIVE = 0;
    private static final int END_INCLUSIVE = 7;

    private final List<PieceResponse> value;

    public BoardResponse(Board board) {
        this.value = generatePieces(board);
    }

    public List<PieceResponse> generatePieces(Board board) {
        return IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
                .mapToObj(rankIndex -> generateOneLinePieces(board, rankIndex))
                .flatMap(List::stream)
                .collect(toList());
    }

    private List<PieceResponse> generateOneLinePieces(Board board, int rankIndex) {
        return IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
                .mapToObj(fileIndex -> new Position(fileIndex, rankIndex))
                .map(position -> new PieceResponse(board.findPiece(position), position.getNotation()))
                .collect(toList());
    }

    public List<PieceResponse> getValue() {
        return value;
    }
}
