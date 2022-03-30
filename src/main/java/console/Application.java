package console;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.TWO;

import chess.ChessBoard;
import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Position;
import console.command.Command;
import console.command.CommandType;
import console.view.InputView;
import console.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitChessGameMessage();
        Command command = inputStartAndEndCommand();

        if (command.isTypeOf(CommandType.START)) {
            ChessBoard chessBoard = createChessBoard();
            OutputView.printChessBoard(chessBoard.getBoard());
            executeChessGame(chessBoard);
        }
    }

    private static Command inputStartAndEndCommand() {
        try {
            return InputView.inputStartOrEndCommand();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputStartAndEndCommand();
        }
    }

    private static ChessBoard createChessBoard() {
        return new ChessBoard(createBoard(), Color.WHITE);
    }

    private static Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = new HashMap<>();
        board.putAll(createWhitePieces());
        board.putAll(createBlackPieces());
        return board;
    }

    private static Map<Position, Piece> createWhitePieces() {
        return Map.ofEntries(
                Map.entry(new Position(A, ONE), new Rook(Color.WHITE)),
                Map.entry(new Position(B, ONE), new Knight(Color.WHITE)),
                Map.entry(new Position(C, ONE), new Bishop(Color.WHITE)),
                Map.entry(new Position(D, ONE), new Queen(Color.WHITE)),
                Map.entry(new Position(E, ONE), new King(Color.WHITE)),
                Map.entry(new Position(F, ONE), new Bishop(Color.WHITE)),
                Map.entry(new Position(G, ONE), new Knight(Color.WHITE)),
                Map.entry(new Position(H, ONE), new Rook(Color.WHITE)),
                Map.entry(new Position(A, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(B, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(C, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(D, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(E, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(F, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(G, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(H, TWO), new Pawn(Color.WHITE)));
    }

    private static Map<Position, Piece> createBlackPieces() {
        return Map.ofEntries(
                Map.entry(new Position(A, EIGHT), new Rook(Color.BLACK)),
                Map.entry(new Position(B, EIGHT), new Knight(Color.BLACK)),
                Map.entry(new Position(C, EIGHT), new Bishop(Color.BLACK)),
                Map.entry(new Position(D, EIGHT), new Queen(Color.BLACK)),
                Map.entry(new Position(E, EIGHT), new King(Color.BLACK)),
                Map.entry(new Position(F, EIGHT), new Bishop(Color.BLACK)),
                Map.entry(new Position(G, EIGHT), new Knight(Color.BLACK)),
                Map.entry(new Position(H, EIGHT), new Rook(Color.BLACK)),
                Map.entry(new Position(A, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(B, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(C, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(D, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(E, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(F, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(G, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(H, SEVEN), new Pawn(Color.BLACK)));
    }

    private static void executeChessGame(ChessBoard chessBoard) {
        Command command = inputCommand();

        while (!command.isTypeOf(CommandType.END)) {
            executeCommand(chessBoard, command);
            command = inputNextCommand(chessBoard);
        }

        if (chessBoard.isFinished()) {
            OutputView.printWinner(chessBoard.getWinner());
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

    private static void executeCommand(ChessBoard chessBoard, Command command) {
        if (command.isTypeOf(CommandType.MOVE)) {
            movePiece(chessBoard, command);
        }
        if (command.isTypeOf(CommandType.STATUS)) {
            printScores(chessBoard);
        }
    }

    private static void movePiece(ChessBoard chessBoard, Command command) {
        try {
            chessBoard.move(command.getFrom(), command.getTo());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            OutputView.printChessBoard(chessBoard.getBoard());
        }
    }

    private static void printScores(ChessBoard chessBoard) {
        OutputView.printScores(chessBoard.getScore(Color.WHITE).getValue(),
                chessBoard.getScore(Color.BLACK).getValue());
    }

    private static Command inputNextCommand(ChessBoard chessBoard) {
        if (!chessBoard.isFinished()) {
            return inputCommand();
        }
        return new Command(CommandType.END);
    }
}
