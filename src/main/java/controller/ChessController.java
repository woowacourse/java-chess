package controller;

import static constants.Bound.BOARD_LOWER_BOUND;
import static constants.Bound.BOARD_UPPER_BOUND;
import static view.Command.END;
import static view.Command.MOVE;
import static view.Command.START;

import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.Position;
import java.util.ArrayList;
import java.util.List;
import view.Command;
import view.InputView;
import view.OutputView;
import view.dto.DtoMapper;
import view.dto.RankInfo;

public class ChessController {
    public void start() {
        OutputView.printGameStartMessage();
        final Board board = new Board(BoardInitiator.init());

        String command = InputView.inputCommand();
        if (END.equals(Command.of(command))) {
            return;
        }

        checkCommandIsStart(command, board);
        movePiecesByCommandOn(board);
    }

    private void checkCommandIsStart(final String command, final Board board) {
        readyForStartCommand(command);
        OutputView.printChessBoard(createRankInfo(board));
    }

    private void readyForStartCommand(String command) {
        try {
            inputUntilStartCommand(command);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void inputUntilStartCommand(String command) {
        while (!START.equals(Command.of(command))) {
            OutputView.printErrorMessage("게임을 시작하려면 start를 입력해주세요.");
            InputView.clearBuffer();
            command = InputView.inputCommand();
        }
    }

    private void movePiecesByCommandOn(final Board board) {
        String command = InputView.inputCommand();
        while (MOVE.equals(Command.of(command))) {
            command = inputCommandUntilNoException(board);
        }
    }

    private String inputCommandUntilNoException(final Board board) {
        String command;
        try {
            command = nextCommand(board);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            command = InputView.inputCommand();
        }
        return command;
    }

    private String nextCommand(final Board board) {
        moveByCommand(board);

        OutputView.printChessBoard(createRankInfo(board));
        return InputView.inputCommand();
    }

    private void moveByCommand(final Board board) {
        final String source = InputView.inputCommand();
        final String target = InputView.inputCommand();
        move(source, target, board);
    }

    public void move(final String source, final String target, final Board board) {
        final Position sourcePosition = Position.createPosition(source);
        final Position targetPosition = Position.createPosition(target);

        board.moveByPosition(sourcePosition, targetPosition);
    }

    private List<RankInfo> createRankInfo(final Board board) {
        final List<RankInfo> rankInfos = new ArrayList<>();
        for (int rank = BOARD_UPPER_BOUND.value(); rank >= BOARD_LOWER_BOUND.value(); rank--) {
            final RankInfo pieceShapeOfRank = DtoMapper.getPieceShapeOn(board, rank);
            rankInfos.add(pieceShapeOfRank);
        }
        return rankInfos;
    }
}
