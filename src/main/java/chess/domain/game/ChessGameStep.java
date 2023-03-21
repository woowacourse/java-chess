package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;
import java.util.Map;

public interface ChessGameStep {

    boolean playable();

    ChessGameStep initialize();

    ChessGameStep movePiece(final PiecePosition source, final PiecePosition destination);

    ChessGameStep end();

    List<Piece> pieces();

    Color winColor();

    Map<Color, Double> calculateScore();
}
