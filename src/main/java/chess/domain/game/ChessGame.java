package chess.domain.game;

import chess.boardstrategy.BoardStrategy;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.type.Piece;

import java.util.Map;

public class ChessGame {
    private final ChessBoard chessBoard;
    private Color turn = Color.WHITE;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    public void start(BoardStrategy boardStrategy) {
        if(isStarted()){
            throw new IllegalArgumentException("게임이 이미 시작되었습니다");
        }
        this.chessBoard.initialize(boardStrategy.generate());
    }

    public void move(Position start, Position end) {
        if(!isStarted()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다");
        }
        Color colorOfStartPiece = chessBoard.findColorOfPieceInPosition(start);
        checkCorrectTurnByColor(colorOfStartPiece);
        chessBoard.move(start, end);
        turn = colorOfStartPiece.getOpponent();
    }

    private void checkCorrectTurnByColor(Color colorOfStartPiece) {
        if(colorOfStartPiece.isOpponent(turn)) {
            throw new IllegalArgumentException("상대편의 기물을 움직일 수 없습니다");
        }
    }

    private boolean isStarted() {
        return chessBoard.isInitialized();
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getChessBoard();
    }

}
