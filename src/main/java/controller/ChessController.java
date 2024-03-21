package controller;

import static constants.Bound.BOARD_LOWER_BOUND;
import static constants.Bound.BOARD_UPPER_BOUND;
import static view.Command.MOVE;
import static view.Command.START;

import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.Position;
import domain.piece.info.File;
import domain.piece.info.Rank;
import dto.DtoMapper;
import dto.RankInfo;
import java.util.ArrayList;
import java.util.List;
import view.Command;
import view.InputView;
import view.OutputView;

public class ChessController {
    public static final String DELIMITER = " ";
    public static final int SOURCE_POSITION = 1;
    public static final int TARGET_POSITION = 2;

    public void start() {
        OutputView.printGameStartMessage();
        final Board board = new Board(BoardInitiator.init());

        String command = InputView.inputCommand();
        readyForStartCommand(command);
        OutputView.printChessBoard(createPieceInfo(board));

        move(board);
    }

    private void readyForStartCommand(String command) {
        while (!START.equals(Command.of(command))) {
            OutputView.printErrorMessage("게임을 시작하려면 start를 입력해주세요.");
            command = InputView.inputCommand();
        }
    }

    private void move(final Board board) {
        String command;
        command = InputView.inputCommand();
        while (MOVE.equals(Command.of(command))) {
            command = inputCommandUntilNoException(command, board);
        }
    }

    private String inputCommandUntilNoException(String command, final Board board) {
        try {
            command = nextCommand(command, board);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            command = InputView.inputCommand();
        }
        return command;
    }

    private String nextCommand(String command, final Board board) {
        final String[] splitCommand = command.split(DELIMITER);
        moveByCommand(board, splitCommand);

        OutputView.printChessBoard(createPieceInfo(board));
        command = InputView.inputCommand();
        return command;
    }

    private void moveByCommand(final Board board, final String[] splitCommand) {
        Position sourcePosition = null;
        Position targetPosition = null;
        if (splitCommand.length != 1) {
            final String source = splitCommand[SOURCE_POSITION];
            sourcePosition = createPosition(source);

            final String target = splitCommand[TARGET_POSITION];
            targetPosition = createPosition(target);
        }
        board.move(sourcePosition, targetPosition);
    }

    private Position createPosition(final String positionValue) {
        final File file = convertToFile(positionValue);
        final Rank rank = convertToRank(positionValue);
        return new Position(file, rank);
    }

    private File convertToFile(final String positionValue) {
        final String fileName = String.valueOf(positionValue.charAt(0)).toUpperCase();
        return File.of(fileName);
    }

    private Rank convertToRank(final String positionValue) {
        final int rankNumber = Integer.parseInt(String.valueOf(positionValue.charAt(1))) - 1;
        return Rank.of(rankNumber);
    }

    private List<RankInfo> createPieceInfo(final Board board) {
        final List<RankInfo> rankInfos = new ArrayList<>();
        for (int rank = BOARD_UPPER_BOUND.value(); rank >= BOARD_LOWER_BOUND.value(); rank--) {
            final RankInfo pieceShapeOfRank = DtoMapper.getPieceShapeOfRank(board, rank);
            rankInfos.add(pieceShapeOfRank);
        }
        return rankInfos;
    }
}
