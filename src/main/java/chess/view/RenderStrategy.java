package chess.view;

import chess.piece.Piece;

public interface RenderStrategy {
    String render(Piece piece);
}
