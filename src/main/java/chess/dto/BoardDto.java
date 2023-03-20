package chess.dto;

import static java.util.stream.Collectors.toUnmodifiableMap;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.domain.square.Squares;
import java.util.Map;

public class BoardDto {
    private final Map<Integer, String> pieces;

    private BoardDto(final Map<Integer, String> pieces) {
        this.pieces = pieces;
    }

    public static BoardDto of(final Board board) {
        final Map<Square, Piece> boardPieces = board.getBoard();
        return new BoardDto(boardPieces.entrySet().stream()
                        .collect(toUnmodifiableMap(
                                entry -> Squares.getIndex(entry.getKey()),
                                entry -> entry.getValue().getPieceTypeName())));
    }

    public Map<Integer, String> getPieces() {
        return pieces;
    }
}
