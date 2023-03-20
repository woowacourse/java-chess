package chess.controller;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        boolean isContinue = true;
        while (isContinue) {
            isContinue = repeat(this::playGame);
        }
    }

    private boolean playGame() {
        String command = inputView.readCommand();
        if (command.equals("end")) {
            return false;
        }
        if (command.equals("start")) {
            initializeBoard();
            return true;
        }
        if (command.contains("move")) {
            String[] commands = command.split(" ");
            validateMoveCommand(commands);
            movePiece(commands);
            return true;
        }
        throw new IllegalArgumentException("해당 명령어는 존재하지 않습니다.");
    }

    private Boolean repeat(Supplier<Boolean> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return repeat(supplier);
        }
    }

    private void initializeBoard() {
        board = BoardFactory.create();
        showBoard();
    }

    private void validateMoveCommand(final String[] commands) {
        if (commands.length != 3) {
            throw new IllegalArgumentException("이동할 기물과 이동할 위치를 입력해주세요.");
        }
    }

    private void movePiece(final String[] commands) {
        Square sourceSquare = convertSquare(commands[1]);
        Square targetSquare = convertSquare(commands[2]);
        board.makeMove(sourceSquare, targetSquare);
        showBoard();
    }

    private Square convertSquare(final String command) {
        int fileValue = command.charAt(0) - 'a' + 1;
        int rankValue = command.charAt(1) - '1' + 1;
        File file = File.from(fileValue);
        Rank rank = Rank.from(rankValue);
        return Square.of(file, rank);
    }

    private void showStartMessage() {
        outputView.printStartMessage();
    }

    private void showBoard() {
        outputView.printBoard(convertBoard());
    }

    private List<List<Piece>> convertBoard() {
        List<List<Piece>> pieces = new ArrayList<>();
        List<File> files = List.of(File.values());
        List<Rank> ranks = List.of(Rank.values());
        for (Rank rank : ranks) {
            List<Piece> rankPieces = files.stream()
                    .map(file -> board.findPiece(file, rank))
                    .collect(Collectors.toList());
            pieces.add(rankPieces);
        }
        return pieces;
    }
}
