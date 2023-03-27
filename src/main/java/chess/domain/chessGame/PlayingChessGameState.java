package chess.domain.chessGame;

import chess.KingDiedException;
import chess.domain.Board;
import chess.domain.PieceDto;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class PlayingChessGameState implements ChessGameState {
    private final Board board;
    private boolean isEnd = false;
    private Color currentTurn;

    public PlayingChessGameState(Board board) {
        this.board = board;
        this.currentTurn = Color.WHITE;
    }

    public PlayingChessGameState(Map<Position, Piece> board, Color thisTurn) {
        this.board = new Board(board);
        this.currentTurn = thisTurn;
    }

    @Override
    public Map<Position, PieceDto> move(String currentPositionSymbol, String nextPositionSymbol) {
        Color thisTurn = currentTurn;
        currentTurn = currentTurn.next();
        try {
            return board.move(Position.of(currentPositionSymbol), Position.of(nextPositionSymbol), thisTurn);
        } catch (KingDiedException e) {
            this.isEnd = true;
            return e.getBoard();
        }
    }

    @Override
    public List<Double> calculateScore() {
        return board.calculateScore();
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
    public boolean isReady() {
        return false;
    }

    @Override
    public Map<Position, PieceDto> getPrintingBoard() {
        return board.getPrintingBoard();
    }

    @Override
    public Color getThisTurn() {
        return this.currentTurn;
    }
}
