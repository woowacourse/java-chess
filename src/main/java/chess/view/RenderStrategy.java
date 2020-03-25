package chess.view;

import chess.board.piece.Piece;

public interface RenderStrategy {
    String render(Piece piece);
}
