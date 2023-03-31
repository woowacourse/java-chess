package chess.domain.chessGame;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PlayingCamp;
import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.util.List;
import java.util.Map;

public class PlayingChessGame implements ChessGame {
    private final Board board;
    private boolean isEnd = false;
    private PlayingCamp currentTurn;

    public PlayingChessGame(Board board) {
        this.board = board;
        this.currentTurn = PlayingCamp.WHITE;
    }

    public PlayingChessGame(Map<Position, Piece> board, PlayingCamp thisTurn) {
        this.board = new Board(board);
        this.currentTurn = thisTurn;
    }

    @Override
    public Map<Position, PieceDto> move(String currentPositionSymbol, String nextPositionSymbol) {
        Map<Position, PieceDto> piecePositions = board.move(Position.of(currentPositionSymbol), Position.of(nextPositionSymbol), currentTurn);
        if (board.isFinished()) {
            this.isEnd = true;
        }
        currentTurn = currentTurn.next();
        return piecePositions;
    }

    @Override
    public List<Double> calculateScore() {
        return board.calculateScore();
    }

    @Override
    public ChessGame start() {
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
    public PlayingCamp getThisTurn() {
        return this.currentTurn;
    }
}
