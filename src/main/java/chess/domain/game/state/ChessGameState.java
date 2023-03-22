package chess.domain.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;

import java.util.Map;

public interface ChessGameState {

    boolean playable();

    ChessGameState movePiece(final ChessBoard chessBoard, final PiecePosition source, final PiecePosition destination);

    ChessGameState end();

    Color winColor();

    Map<Color, Double> calculateScore(final ChessBoard chessBoard);
}
