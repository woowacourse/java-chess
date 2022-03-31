package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Winner {

    private final Color color;

    public Winner(Board board) {
        if (board.isAllKingAlive()) {
            color = Color.NONE;
            return;
        }
        color = judge(board.getValue());
    }

    private Color judge(Map<Position, Piece> boardPieces) {
        return boardPieces.values().stream()
            .filter(Piece::isKing)
            .map(Piece::getColor)
            .findAny()
            .get();
    }

    public Color getColor() {
        return color;
    }
}
