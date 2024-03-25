package service;

import domain.ChessGameResult;
import domain.Team;
import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardDao;
import domain.piece.Piece;
import domain.square.Square;

import java.util.Map;
import java.util.Optional;

public class ChessGame {

    private ChessBoard chessBoard;
    private ChessGameStatus chessGameStatus;
    private final ChessBoardDao chessBoardDao;

    public ChessGame(final ChessBoardDao chessBoardDao) {
        this.chessGameStatus = ChessGameStatus.INIT;
        this.chessBoardDao = chessBoardDao;
    }

    public void startNewGame() {
        if (chessGameStatus == ChessGameStatus.INIT) {
            chessBoard = ChessBoard.create();

            final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
            chessBoardDao.addAll(pieceSquares);

            chessGameStatus = ChessGameStatus.RUNNING;
            return;
        }
        throw new IllegalArgumentException("초기 상태에서만 게임을 시작할 수 있습니다.");
    }

    public void continueGame() {
        if (chessGameStatus == ChessGameStatus.INIT) {
            final Map<Square, Piece> pieceSquares = chessBoardDao.findAll();

            chessBoard = new ChessBoard(pieceSquares, Team.WHITE);

            chessGameStatus = ChessGameStatus.RUNNING;
            return;
        }
        throw new IllegalArgumentException("초기 상태에서만 게임을 시작할 수 있습니다.");
    }

    public void move(final Square source, final Square target) {
        if (chessGameStatus == ChessGameStatus.RUNNING) {
            chessBoard.move(source, target);

            final Piece piece = chessBoardDao.findBySquare(source)
                    .orElseThrow(() -> new IllegalArgumentException("Source에 기물이 없습니다."));

            final Optional<Piece> targetPiece = chessBoardDao.findBySquare(target);

            if (targetPiece.isEmpty()) {
                chessBoardDao.addSquarePiece(target, piece);
            } else {
                chessBoardDao.update(target, piece);
            }

            chessBoardDao.deleteBySquare(source);

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

        return ChessGameResult.from(pieceSquares);
    }

    public Map<Square, Piece> getPieceSquares() {
        return chessBoard.getPieceSquares();
    }

    public boolean hasSave() {
        return !chessBoardDao.isEmpty();
    }

    private enum ChessGameStatus {
        INIT,
        RUNNING,
        END
    }
}
