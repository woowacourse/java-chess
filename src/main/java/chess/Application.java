package chess;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.pieces.Piece;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final ChessGame chessGame = new ChessGame();

    public static void main(String[] args) {
        OutputView.printGameStart();

        while (!chessGame.isEnd()) {
            changeState();
            printBoard();
        }
    }

    private static void changeState() {
        try {
            Command command = new Command(InputView.readCommand());
            chessGame.changeState(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            changeState();
        }
    }

    private static void printBoard() {
        List<List<String>> pieceNames = new ArrayList<>();
        for (int rank = 8; rank > 0; rank--) {
            pieceNames.add(addName(chessGame.getBoard(), rank));
        }
        OutputView.printBoard(pieceNames);
    }

    private static List<String> addName(Board board, int rank) {
        List<String> pieceNames = new ArrayList<>();
        for (int file = 0; file < 8; file++) {
            Piece piece = board.getBoard().get(new Position(Rank.of(rank), File.ofByFile(file)));

            if (piece.isWhiteTeam()) {
                pieceNames.add(piece.getType().getName().toLowerCase());
                continue;
            }
            pieceNames.add(piece.getType().getName());
        }
        return List.copyOf(pieceNames);
    }
}
