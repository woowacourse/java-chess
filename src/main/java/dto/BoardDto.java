package dto;

import domain.game.Board;
import domain.game.PieceType;
import domain.position.Position;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public record BoardDto(Map<Position, PieceType> piecePositions) {

    public static BoardDto from(final Board board) {
        Map<Position, PieceType> piecePositions = board.getChessBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getPieceType()));

        return new BoardDto(Collections.unmodifiableMap(piecePositions));
    }
}
