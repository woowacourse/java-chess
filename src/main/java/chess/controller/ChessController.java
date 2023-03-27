package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.board.factory.BoardFactory;
import chess.domain.board.position.Position;
import chess.domain.board.score.BoardScore;
import chess.domain.board.score.Score;
import chess.domain.board.search.BoardSearch;
import chess.domain.board.service.BoardCommandService;
import chess.domain.board.service.BoardQueryService;
import chess.domain.piece.Color;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

import static chess.common.IndexCommand.POSITION_COLUMN;
import static chess.common.IndexCommand.POSITION_ROW;
import static chess.common.IndexCommand.SOURCE_POSITION;
import static chess.common.IndexCommand.START_COMMAND_INDEX;
import static chess.common.IndexCommand.TARGET_POSITION;

public class ChessController {

    private static final Long DEFAULT_BOARD_ID = 1L;

    private final BoardQueryService boardQueryService;
    private final BoardCommandService boardCommandService;

    public ChessController(final BoardQueryService boardQueryService,
                           final BoardCommandService boardCommandService) {
        this.boardQueryService = boardQueryService;
        this.boardCommandService = boardCommandService;
    }

    public void run() {

        System.out.println("새로운 게임을 시작하려면 1, 게임을 불러오려면 2를 누르세요.");

        final String newGameCommand = InputView.readNewGameCommand();

        Board board = Board.makeNewGame(new BoardFactory());
        Long savedId = DEFAULT_BOARD_ID;

        if (Command.isNewGame(newGameCommand)) {
            savedId = boardCommandService.registerBoard(board);
        }

        if (Command.isLoadGame(newGameCommand)) {
            System.out.println("현재 진행하고 있는 게임 번호들입니다.");

            final List<Long> boardIds = boardQueryService.searchAllBoards();

            OutputView.printGameCandidates(boardIds);

            System.out.println("불러올 게임의 번호를 입력해주세요.");

            savedId = InputView.readLoadGameCommand();

            board = boardQueryService.searchBoard(savedId);
        }

        final String command = InputView.readStartCommand();

        if (Command.isNotStart(command)) {
            return;
        }

        startGame(board, savedId);
    }

    private void startGame(final Board board, Long boardId) {

        while (true) {
            OutputView.printBoard(board.chessBoard());

            final List<String> moveCommands = InputView.readMoveCommand();

            final String startCommand = moveCommands.get(START_COMMAND_INDEX.value());

            if (Command.isEnd(startCommand)) {
                end(boardId, board);
                return;
            }

            if (Command.isStatus(startCommand)) {
                status(board, boardId);
                return;
            }

            final Turn beforeTurn = board.turn();

            if (Command.isMove(startCommand)) {
                final Position fromPosition = convertPositionFrom(moveCommands.get(SOURCE_POSITION.value()));
                final Position toPosition = convertPositionFrom(moveCommands.get(TARGET_POSITION.value()));

                board.move(fromPosition, toPosition);
            }

            final BoardSearch boardSearch = BoardSearch.countPiecePerClassTypeFrom(board.chessBoard());

            if (boardSearch.isKingDead()) {
                final BoardScore boardScore = BoardScore.flatByColumnFrom(board.chessBoard());
                OutputView.printWinner(beforeTurn.color(), boardScore.calculateBoardScoreBy(beforeTurn.color()));
                return;
            }
        }
    }

    private void end(final Long boardId, final Board board) {
        boardCommandService.modifyBoard(board, boardId);
    }

    private void status(final Board board, final Long boardId) {
        final BoardScore boardScore = BoardScore.flatByColumnFrom(board.chessBoard());

        final Score blackScore = boardScore.calculateBoardScoreBy(Color.BLACK);
        final Score whiteScore = boardScore.calculateBoardScoreBy(Color.WHITE);

        OutputView.printWinner(whoIsWinner(blackScore, whiteScore), whiteScore, blackScore);
        boardCommandService.modifyBoard(board, boardId);
    }

    private Color whoIsWinner(final Score blackScore, final Score whiteScore) {
        if (blackScore.isGreaterThan(whiteScore)) {
            return Color.BLACK;
        } else if (blackScore.equals(whiteScore)) {
            return Color.NONE;
        }

        return Color.WHITE;
    }

    private Position convertPositionFrom(String moveCommand) {
        return new Position(moveCommand.charAt(POSITION_COLUMN.value()), moveCommand.charAt(POSITION_ROW.value()));
    }
}
