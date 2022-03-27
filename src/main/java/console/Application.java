package console;

import static chess.position.File.*;
import static chess.position.File.H;
import static chess.position.Rank.*;
import static chess.position.Rank.TWO;

import chess.ChessBoard;
import chess.piece.*;
import chess.position.Position;
import console.view.*;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitChessGameMessage();
        Command command = inputCommand();
        ChessBoard chessBoard = null;

        while (true) {
            try {
                if (command instanceof StartCommand) {
                    if(chessBoard != null) {
                        throw new IllegalStateException("체스 게임이 이미 진행중입니다.");
                    }

                    chessBoard = new ChessBoard(createPieces(), Color.WHITE);
                    OutputView.printChessBoard(chessBoard.getPieces());
                }

                if (command instanceof MoveCommand) {
                    if (chessBoard == null) {
                        throw new IllegalStateException("체스 게임이 시작되지 않았습니다.");
                    }

                    chessBoard.move(command.getFrom(), command.getTo());
                    OutputView.printChessBoard(chessBoard.getPieces());
                }

                if (command instanceof StatusCommand) {
                    if (chessBoard == null) {
                        throw new IllegalStateException("체스 게임이 시작되지 않았습니다.");
                    }

                    OutputView.printScores(chessBoard.getScore(Color.WHITE),
                        chessBoard.getScore(Color.BLACK));
                }

                if (chessBoard != null && chessBoard.isFinished()) {
                    OutputView.printWinner(chessBoard.getWinner());
                    return;
                }

                if (command instanceof EndCommand) {
                    return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            command = inputCommand();
        }
    }

    private static Command inputCommand() {
        try {
            return InputView.inputCommand();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }

    private static List<Piece> createPieces() {
        return List.of(
            new Rook(Color.BLACK, new Position(A, EIGHT)),
            new Knight(Color.BLACK, new Position(B, EIGHT)),
            new Bishop(Color.BLACK, new Position(C, EIGHT)),
            new Queen(Color.BLACK, new Position(D, EIGHT)),
            new King(Color.BLACK, new Position(E, EIGHT)),
            new Bishop(Color.BLACK, new Position(F, EIGHT)),
            new Knight(Color.BLACK, new Position(G, EIGHT)),
            new Rook(Color.BLACK, new Position(H, EIGHT)),
            new Pawn(Color.BLACK, new Position(A, SEVEN)),
            new Pawn(Color.BLACK, new Position(B, SEVEN)),
            new Pawn(Color.BLACK, new Position(C, SEVEN)),
            new Pawn(Color.BLACK, new Position(D, SEVEN)),
            new Pawn(Color.BLACK, new Position(E, SEVEN)),
            new Pawn(Color.BLACK, new Position(F, SEVEN)),
            new Pawn(Color.BLACK, new Position(G, SEVEN)),
            new Pawn(Color.BLACK, new Position(H, SEVEN)),
            new Rook(Color.WHITE, new Position(A, ONE)),
            new Knight(Color.WHITE, new Position(B, ONE)),
            new Bishop(Color.WHITE, new Position(C, ONE)),
            new Queen(Color.WHITE, new Position(D, ONE)),
            new King(Color.WHITE, new Position(E, ONE)),
            new Bishop(Color.WHITE, new Position(F, ONE)),
            new Knight(Color.WHITE, new Position(G, ONE)),
            new Rook(Color.WHITE, new Position(H, ONE)),
            new Pawn(Color.WHITE, new Position(A, TWO)),
            new Pawn(Color.WHITE, new Position(B, TWO)),
            new Pawn(Color.WHITE, new Position(C, TWO)),
            new Pawn(Color.WHITE, new Position(D, TWO)),
            new Pawn(Color.WHITE, new Position(E, TWO)),
            new Pawn(Color.WHITE, new Position(F, TWO)),
            new Pawn(Color.WHITE, new Position(G, TWO)),
            new Pawn(Color.WHITE, new Position(H, TWO)));
    }
}
