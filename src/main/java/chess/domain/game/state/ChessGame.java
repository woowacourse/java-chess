package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Color;

public interface ChessGame {

    ChessGame initBoard();

    ChessGame movePiece(Position fromPosition, Position toPosition);

    ChessGame end();

    boolean isFinish();

    double calculateScore(Color color);

    Color judgeWinner();

    Board getBoard();
}
