package chess.dto;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.view.Character;

import java.util.HashMap;
import java.util.Map;

public class BoardDto {
    public static final String EMPTY_POSITION = ".";

    private final Map<Position, Character> pieces;

    public BoardDto(Board board) {
        pieces = new HashMap<>();
        Map<Position, Piece> boardPieces = board.getPieces();
        for (Map.Entry<Position, Piece> piece : boardPieces.entrySet()) {
            pieces.put(
                    piece.getKey(),
                    Character.findCharacter(piece.getValue()));
        }
    }

    public String identifyPositionCharacter(int row, int column) {
        Position position = Position.of(row, column);
        if (pieces.containsKey(position)) {
            return pieces.get(position).getMessage();
        }
        return EMPTY_POSITION;
    }
}
