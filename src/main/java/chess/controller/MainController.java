package chess.controller;

import chess.ChessGame;
import chess.domain.pieces.Position;
import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.pieces.component.Name;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private final ChessGame chessGame = new ChessGame();

    public void run() {
        OutputView.printGameStart();
        while (!chessGame.isEnd()){
            changeState();
            printBoard();
        }
    }

    private void changeState() {
        try {
            Command command = new Command(InputView.readCommand());
            chessGame.setState(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            changeState();
        }
    }

    private void printBoard() {
        List<List<Name>> pieceNames = new ArrayList<>();
        for (int rank = 8; rank > 0; rank--) {
            pieceNames.add(addName(chessGame.getBoard(), rank));
        }
        OutputView.printBoard(pieceNames);
    }

    private List<Name> addName(Board board, int rank) {
        List<Name> pieceNames = new ArrayList<>();
        for (int file = 0; file < 8; file++) {
            Name name = board.getBoard().get(new Position(Rank.of(rank), File.ofByFile(file))).getName();
            pieceNames.add(name);
        }
        return List.copyOf(pieceNames);
    }
}
