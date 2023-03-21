package service;

import java.util.Map;

import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class ChessService {
    private final ChessBoard chessBoard = new ChessBoard();
    private Camp currentCamp = Camp.WHITE;
    private boolean isOngoing;

    public void setUp() {
        if (isOngoing) {
            throw new IllegalStateException("이미 게임이 실행중 입니다.");
        }
        chessBoard.initialize();
        isOngoing = true;
    }

    public void move(String currentSquareInput, String targetSquareInput) {
        if(!isOngoing) {
            throw new IllegalStateException("start를 먼저 입력해주세요.");
        }
        Square currentSquare = getCurrentSquare(currentSquareInput);
        Square targetSquare = getTargetSquare(targetSquareInput);
        validateCurrentCamp(currentSquare);
        chessBoard.move(currentSquare, targetSquare);
        currentCamp = currentCamp.fetchOppositeCamp();
    }

    public void end() {
        if(!isOngoing) {
            throw new IllegalStateException("start를 먼저 입력해주세요.");
        }
        isOngoing = false;
    }

    private Square getCurrentSquare(String currentSquareInput) {
        return Square.of(File.findFile(currentSquareInput.charAt(0)),
                Rank.findRank(currentSquareInput.charAt(1)));
    }

    private Square getTargetSquare(String targetSquareInput) {
        return Square.of(File.findFile(targetSquareInput.charAt(0)),
                Rank.findRank(targetSquareInput.charAt(1)));
    }

    private void validateCurrentCamp(Square currentSquare) {
        if (!chessBoard.isCorrectCamp(currentCamp, currentSquare)) {
            throw new IllegalStateException("같은 진영의 말만 움직일 수 있습니다.");
        }
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard.getBoard();
    }
}
