package service;

import java.util.Map;

import controller.Command;
import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class ChessService {
    private final ChessBoard chessBoard = new ChessBoard();
    private Camp currentCamp = Camp.WHITE;

    public boolean isOngoing() {
        return isOngoing;
    }

    private boolean isOngoing;


    public void execute(String[] inputs) {
        if (commandIsStart(inputs)) {
            return;
        }
        if (commandIsEnd(inputs)) {
            return;
        }
        if (commandIsMove(inputs)) {
            return;
        }
        throw new IllegalStateException("명령에 알맞은 상태가 아닙니다.");
    }

    private boolean commandIsMove(String[] inputs) {
        if (Command.findRunCommand(inputs[0]).equals(Command.MOVE) && isOngoing) {
            Square currentSquare = getCurrentSquare(inputs);
            Square targetSquare = getTargetSquare(inputs);
            validateCurrentCamp(currentSquare);

            chessBoard.move(currentSquare, targetSquare);
            return true;
        }
        return false;
    }

    private Square getTargetSquare(String[] inputs) {
        return new Square(File.findFile(inputs[2].charAt(0)),
                Rank.findRank(inputs[2].charAt(1)));
    }

    private Square getCurrentSquare(String[] inputs) {
        return new Square(File.findFile(inputs[1].charAt(0)),
                Rank.findRank(inputs[1].charAt(1)));
    }

    private void validateCurrentCamp(Square currentSquare) {
        if (!chessBoard.isCorrectCamp(currentCamp, currentSquare)) {
            throw new IllegalStateException("같은 진영의 말만 움직일 수 있습니다.");
        }
        ;
    }

    private boolean commandIsEnd(String[] inputs) {
        if (Command.findRunCommand(inputs[0]).equals(Command.END) && isOngoing) {
            isOngoing = false;
            return true;
        }
        return false;
    }

    private boolean commandIsStart(String[] inputs) {
        if (Command.findRunCommand(inputs[0]).equals(Command.START) && !isOngoing) {
            chessBoard.initialize();
            isOngoing = true;
            return true;
        }
        return false;
    }

    public void setUp() {
        chessBoard.initialize();
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard.getBoard();
    }
}
