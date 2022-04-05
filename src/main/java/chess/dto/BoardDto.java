package chess.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Row;
import chess.domain.position.Square;

public class BoardDto {
    private final List<Piece> pieces;

    public BoardDto(Board board) {
        this.pieces = splitByRank(board.getBoard());
    }

    private List<Piece> splitByRank(Map<Square, Piece> board) {
        List<Piece> pieces = new ArrayList<>(board.values());
        List<List<Piece>> splitPieces = Lists.partition(pieces, Row.values().length);
        return reverse(splitPieces);
    }

    private List<Piece> reverse(List<List<Piece>> splitPieces) {
        List<Piece> pieces = new ArrayList<>();
        for (List<Piece> splitPiece : splitPieces) {
            pieces = Stream.concat(splitPiece.stream(), pieces.stream())
                    .collect(Collectors.toList());
        }
        return pieces;
    }

    public List<String> getEmojis() {
        return pieces.stream()
                .map(Piece::getEmoji)
                .collect(Collectors.toList());
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
