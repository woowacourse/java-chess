package chess.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import chess.board.ChessBoard;
import chess.board.Position;
import chess.dto.ChessBoardDto;
import chess.game.ChessGame;
import chess.game.Command;
import chess.piece.Team;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionConvertor;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printInitGame();
        processGame();
    }

    // TODO: move에 대한 재입력 로직 추가, 왕이 죽었을 때 승리팀을 알려주도록(기존의 view를 사용해서 승리팀을 알려줄지, 그냥 승리팀만 보여줄지  전자가 더 좋아보이긴 함)
    private void processGame() {
        final Command firstCommand = repeat(this::readFirstCommand);
        chessGame.receiveCommand(firstCommand);

        while (chessGame.isProcessing()) {
            renderChessBoard();

            final List<String> movePositions = new ArrayList<>();
            final Command command = repeat(() -> readMoveCommand(movePositions));
            if (command == Command.END) {
                outputView.printScore(chessGame.calculateScore(Team.WHITE), chessGame.calculateScore(Team.BLACK));
                outputView.printWinner(chessGame.findWinner());
                break;
            }
            if (command == Command.STATUS) {
                outputView.printScore(chessGame.calculateScore(Team.WHITE), chessGame.calculateScore(Team.BLACK));
                outputView.printWinner(chessGame.findWinner());
                continue;
            }

            move(movePositions);
        }
        outputView.printWinner(chessGame.findWinner());
    }

    private Command readFirstCommand() {
        final List<String> commands = inputView.readGameCommand();
        validateMove(commands);
        validateStatus(commands);
        return Command.from(commands.get(0));
    }

    private void validateMove(final List<String> commands) {
        if (Command.from(commands.get(0)) == Command.MOVE) {
            throw new IllegalArgumentException("게임을 시작하기 전에는 움직일 수 없습니다.");
        }
    }

    private void validateStatus(final List<String> commands) {
        if (Command.from(commands.get(0)) == Command.STATUS) {
            throw new IllegalArgumentException("게임을 시작하기 전에는 진행상태를 확인할 수 없습니다.");
        }
    }

    private void renderChessBoard() {
        final ChessBoard chessBoard = chessGame.getChessBoard();
        final ChessBoardDto chessBoardDto = ChessBoardDto.toView(chessBoard);
        outputView.printChessBoard(chessBoardDto);
    }

    private Command readMoveCommand(final List<String> movePositions) {
        final List<String> commands = inputView.readGameCommand();
        final Command command = Command.from(commands.get(0));
        if (command != Command.MOVE) {
            return command;
        }
        movePositions.add(commands.get(1));
        movePositions.add(commands.get(2));
        return command;
    }

    private void move(final List<String> movePositions) {
        final Position from = PositionConvertor.convert(movePositions.get(0));
        final Position to = PositionConvertor.convert(movePositions.get(1));
        chessGame.movePiece(from, to);
    }

    private <T> T repeat(final Supplier<T> function) {
        Optional<T> input;
        do {
            input = repeatByEx(function);
        } while (input.isEmpty());
        return input.get();
    }

    private <T> Optional<T> repeatByEx(final Supplier<T> function) {
        try {
            return Optional.of(function.get());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
