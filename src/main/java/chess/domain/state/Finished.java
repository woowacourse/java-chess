package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Score;
import chess.domain.game.Status;
import chess.domain.PieceColor;

public class Finished implements State {

    private ChessBoard chessBoard;

    public Finished(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("새로 시작 할 수 없습니다.");
    }

    @Override
    public State end() {
        return new Finished(chessBoard);
    }

    @Override
    public State move(Position from, Position to) {
        throw new UnsupportedOperationException("이미 종료되었습니다.");
    }

    @Override
    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    @Override
    public Status getStatus() {
        Score whiteScore = Score.calculateScore(chessBoard, PieceColor.WHITE);
        Score blackScore = Score.calculateScore(chessBoard, PieceColor.WHITE);
        PieceColor winnerColor = PieceColor.WHITE;

        if (chessBoard.hasKing(PieceColor.BLACK)) {
            winnerColor = PieceColor.BLACK;
        }
        return new Status(whiteScore, blackScore, winnerColor);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
