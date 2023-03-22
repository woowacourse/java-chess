package chess.domain.chessGame;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.Map;

public class PlayingChessGameState implements ChessGameState {
    private final Board board;
    private boolean isEnd = false;
    private Color currentTurn;

    public PlayingChessGameState(Board board) {
        this.board = board;
        this.currentTurn = Color.WHITE;
    }

    @Override
    public Map<Position, String> move(String currentPositionSymbol, String nextPositionSymbol) {
        Position currentPosition = Position.of(currentPositionSymbol);
        Position nextPosition = Position.of(nextPositionSymbol);
        Color thisTurn = currentTurn;
        currentTurn = currentTurn.next();
        return board.move(currentPosition, nextPosition, thisTurn);
    }

    @Override
    public ChessGameState start() {
        throw new IllegalStateException("이미 플레이중인 게임입니다.");
    }

    @Override
    public void end() {
        isEnd = true;
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public Map<Position, String> getPrintingBoard() {
        return board.getPrintingBoard();
    }
}
