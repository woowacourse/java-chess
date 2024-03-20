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
            String[] splitCommand = command.split(" ");
            Position sourcePosition = null;
            Position targetPosition = null;
            if (splitCommand.length != 1) {
                String source = splitCommand[1];
                sourcePosition = createPosition(source);

                String target = splitCommand[2];
                targetPosition = createPosition(target);
            }
            board.move(sourcePosition, targetPosition);
            OutputView.printChessBoard(createPieceInfo(board));
            command = InputView.inputCommand();
        }
    }

    private Position createPosition(final String positionValue) {
        File file = convertToFile(positionValue);
        Rank rank = convertToRank(positionValue);
        return new Position(file, rank);
    }

    private File convertToFile(final String positionValue) {
        String fileName = String.valueOf(positionValue.charAt(0)).toUpperCase();
        return File.of(fileName);
    }

    private Rank convertToRank(final String positionValue) {
        Integer rankNumber = Integer.parseInt(String.valueOf(positionValue.charAt(1))) - 1;
        return Rank.of(rankNumber);
    }

    private List<RankInfo> createPieceInfo(final Board board) {
        final List<RankInfo> rankInfos = new ArrayList<>();
        for (int rank = 7; rank >= 0; rank--) {
            final RankInfo pieceShapeOfRank = DtoMapper.getPieceShapeOfRank(board, rank);
            rankInfos.add(pieceShapeOfRank);
        }
        return rankInfos;
    }
}
