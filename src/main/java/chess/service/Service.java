package chess.service;

import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public interface Service {

    Game findGame();

    Board findBoard();

    boolean addMove(Position from, Position to);

    Board restartBoard();

    boolean endGame();

    PieceColor findTurn();

    Map<PieceColor, Double> getScores();

    List<String> findPath(Position from);
}
