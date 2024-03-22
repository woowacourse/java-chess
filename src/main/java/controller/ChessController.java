package controller;

import static constants.Bound.BOARD_LOWER_BOUND;
import static constants.Bound.BOARD_UPPER_BOUND;
import static view.Command.MOVE;
import static view.Command.START;

import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.Position;
import dto.DtoMapper;
import dto.RankInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import view.Command;
import view.InputView;
import view.OutputView;

public class ChessController {
    private static final Pattern VALID_POSITION_INPUT = Pattern.compile("[^a-h][^1-8]");

    public void start() {
        OutputView.printGameStartMessage();
        final Board board = new Board(BoardInitiator.init());

        String command = InputView.inputCommand();
        readyForStartCommand(command);
        OutputView.printChessBoard(createRankInfo(board));

        movePiecesByCommandOn(board);
    }

    private void readyForStartCommand(String command) {
        while (!START.equals(Command.of(command))) {
            OutputView.printErrorMessage("게임을 시작하려면 start를 입력해주세요.");
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
        validatePositionCommands(source, target);
        move(source, target, board);
    }

    private void validatePositionCommands(final String source, final String target) {
        if (Pattern.matches(VALID_POSITION_INPUT.pattern(), source)) {
            throw new IllegalArgumentException("잘못된 시작 위치 입력입니다.");
        }
        if (Pattern.matches(VALID_POSITION_INPUT.pattern(), target)) {
            throw new IllegalArgumentException("잘못된 목표 위치 입력입니다.");
        }
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
