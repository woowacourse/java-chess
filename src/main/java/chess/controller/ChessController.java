package chess.controller;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private Board board;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        showStartMessage();
        while (true) {
            String command = inputView.readCommand();
            if (command.equals("end")) {
                break;
            }
            if (command.equals("start")) {
                initializeBoard();
            }
        }
    }

    private void showStartMessage() {
        outputView.printStartMessage();
    }

    private void initializeBoard() {
        board = BoardFactory.create();
        outputView.printBoard(convertBoard());
    }

    private List<List<Piece>> convertBoard() {
        List<List<Piece>> pieces = new ArrayList<>();
        List<File> files = List.of(File.values());
        List<Rank> ranks = List.of(Rank.values());
        for (Rank rank : ranks) {
            List<Piece> piecesByRank = new ArrayList<>();
            for (File file : files) {
                piecesByRank.add(board.findPiece(file, rank));
            }
            pieces.add(piecesByRank);
        }
        return pieces;
    }
}
