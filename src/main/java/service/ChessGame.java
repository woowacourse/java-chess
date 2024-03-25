package service;

import domain.*;
import domain.piece.Piece;
import domain.square.Square;

import java.util.Map;

public class ChessGame {

    private ChessBoard chessBoard;
    private ChessGameStatus chessGameStatus;

    public ChessGame() {
        this.chessGameStatus = ChessGameStatus.INIT;
    }

    public void start() {
        if (chessGameStatus == ChessGameStatus.INIT) {
            chessBoard = ChessBoard.create();
            chessGameStatus = ChessGameStatus.RUNNING;
            return;
        }
        throw new IllegalArgumentException("초기 상태에서만 게임을 시작할 수 있습니다.");
    }

    public void move(final Square source, final Square target) {
        if (chessGameStatus == ChessGameStatus.RUNNING) {
            chessBoard.move(source, target);
            checkEnd();
            return;
        }
        throw new IllegalArgumentException("게임이 진행 중일 때만 움직일 수 있습니다.");
    }

    private void checkEnd() {
        if (chessBoard.isAnyKingDead()) {
            chessGameStatus = ChessGameStatus.END;
        }
    }

    public void end() {
        chessGameStatus = ChessGameStatus.END;
    }

    public boolean isNotEnd() {
        return chessGameStatus != ChessGameStatus.END;
    }

    public ChessGameResult calculateResult() {
        if (chessGameStatus == ChessGameStatus.INIT) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }

        final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
        final ChessScoreCalculator chessScoreCalculator = ChessScoreCalculator.from(pieceSquares);

        final Score whiteScore = chessScoreCalculator.score(Team.WHITE);
        final Score blackScore = chessScoreCalculator.score(Team.BLACK);

        final WinStatus winStatus = WinStatus.of(whiteScore, blackScore);

        return new ChessGameResult(whiteScore, blackScore, winStatus);
    }

    public Map<Square, Piece> getPieceSquares() {
        return chessBoard.getPieceSquares();
    }

    private enum ChessGameStatus {
        INIT,
        RUNNING,
        END
    }
}
