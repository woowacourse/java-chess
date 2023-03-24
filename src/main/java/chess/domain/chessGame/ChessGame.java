package chess.domain.chessGame;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private ChessGameState chessGameState = new ReadyChessGameState();
    private final Board board;

    public ChessGame(){
        board = new Board();
    }

    public void start() {
        chessGameState = chessGameState.start();
    }

    public void move(String currentPosition, String nextPosition){
        Piece movingPiece = board.findPieceByPosition(Position.from(currentPosition));
        chessGameState.validateMove(currentPosition, nextPosition, movingPiece);
        board.move(Position.from(currentPosition), Position.from(nextPosition));
    }

    public void end(){
        chessGameState = chessGameState.end();
    }

    public boolean isPlaying(){
        return chessGameState.isPlaying();
    }

    public Map<Position, String> getPrintingBoard(){
        return chessGameState.getPrintingBoard(board);
    }

    public Map<Color, Double> getScores(){
        return chessGameState.getScores(board);
    }

}
