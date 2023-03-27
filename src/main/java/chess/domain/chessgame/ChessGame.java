package chess.domain.chessgame;

import chess.domain.piece.Piece;
import chess.domain.result.Score;
import chess.domain.side.Color;

import java.util.List;
import java.util.Map;

public interface ChessGame {

    ChessGame start();

    ChessGame pause();

    ChessGame move(final String sourceSquareInput, final String targetSquareInput);

    Color findWinner();

    Map<Color, Score> status();

    List<List<Piece>> findChessBoard();

    boolean isContinue();
}
