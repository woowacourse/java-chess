package controller;

import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.Position;
import domain.piece.info.File;
import domain.piece.info.Rank;
import dto.DtoMapper;
import dto.RankInfo;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessController {

    public static final int MAX_BOARD_SIZE = 7;
    public static final int MIN_BOARD_SIZE = 0;

    public void start() {
        OutputView.printGameStartMessage();
        final Board board = new Board(BoardInitiator.init());
        String command = InputView.inputCommand();
        if (!command.equals("start")) {
            throw new IllegalArgumentException("다른 명령어 입력 전, 체스 게임을 시작해야 합니다.");
        }
        OutputView.printChessBoard(createPieceInfo(board));
        command = InputView.inputCommand();
        while (command.contains("move")) {
            command = nextCommand(command, board);
        }
    }

    private String nextCommand(String command, final Board board) {
        final String[] splitCommand = command.split(" ");
        moveByCommand(board, splitCommand);
        OutputView.printChessBoard(createPieceInfo(board));
        command = InputView.inputCommand();
        return command;
    }

    private void moveByCommand(final Board board, final String[] splitCommand) {
        Position sourcePosition = null;
        Position targetPosition = null;
        if (splitCommand.length != 1) {
            final String source = splitCommand[1];
            sourcePosition = createPosition(source);

            final String target = splitCommand[2];
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
        final String fileName = String.valueOf(positionValue.charAt(MIN_BOARD_SIZE)).toUpperCase();
        return File.of(fileName);
    }

    private Rank convertToRank(final String positionValue) {
        final int rankNumber = Integer.parseInt(String.valueOf(positionValue.charAt(1))) - 1;
        return Rank.of(rankNumber);
    }

    private List<RankInfo> createPieceInfo(final Board board) {
        final List<RankInfo> rankInfos = new ArrayList<>();
        for (int rank = MAX_BOARD_SIZE; rank >= MIN_BOARD_SIZE; rank--) {
            final RankInfo pieceShapeOfRank = DtoMapper.getPieceShapeOfRank(board, rank);
            rankInfos.add(pieceShapeOfRank);
        }
        return rankInfos;
    }
}
