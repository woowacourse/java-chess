package chess.domain.chessgame;

import chess.domain.piece.Piece;
import chess.domain.result.Score;
import chess.domain.side.Side;

import java.util.List;
import java.util.Map;

public interface ChessGame {

    ChessGame start();

    ChessGame pause();

    ChessGame move(final String sourceSquareInput, final String targetSquareInput);

    Side findWinner();

    Map<Side, Score> status();

    List<List<Piece>> findChessBoard();

    boolean isContinue();
}
