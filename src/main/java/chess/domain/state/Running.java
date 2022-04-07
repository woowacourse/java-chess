package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Score;
import chess.domain.game.Status;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

public class Running implements State {

    private final ChessBoard chessBoard;
    private Turn turn;

    public Running(ChessBoard chessBoard, Turn turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("상태가 Running인 경우 새로 시작 할 수 없습니다.");
    }

    @Override
    public State end() {
        return new Finished(chessBoard);
    }

    @Override
    public State move(Position from, Position to) {
        validateTurn(chessBoard.findByPiece(from));
        chessBoard.move(from, to);

        if (chessBoard.isFinished()) {
            return new Finished(chessBoard);
        }
        nextTurn();
        return this;
    }

    private void validateTurn(Piece piece) {
        if (!piece.isSameColor(turn.getPieceColor())) {
            throw new IllegalArgumentException("해당 팀의 차례가 아닙니다.");
        }
    }

    private void nextTurn() {
        turn = turn.change();
    }

    @Override
    public ChessBoard chessBoard() {
        return chessBoard;
    }

    @Override
    public Status status() {
        Score whiteScore = Score.calculateScore(chessBoard, PieceColor.WHITE);
        Score blackScore = Score.calculateScore(chessBoard, PieceColor.BLACK);
        PieceColor winnerColor = PieceColor.NONE;
        if (whiteScore.isOverScore(blackScore)) {
            winnerColor = PieceColor.WHITE;
        }
        if (blackScore.isOverScore(whiteScore)) {
            winnerColor = PieceColor.BLACK;
        }
        return new Status(whiteScore, blackScore, winnerColor);
    }

    @Override
    public Turn turn() {
        return turn;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
