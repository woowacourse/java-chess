package chess.controller;

import chess.config.ChessConfigure;
import chess.domain.board.Board;
import chess.domain.board.factory.BoardFactory;
import chess.domain.board.position.Position;
import chess.domain.board.score.BoardScore;
import chess.domain.board.score.Score;
import chess.domain.board.search.BoardSearch;
import chess.domain.board.service.BoardCommandService;
import chess.domain.board.service.BoardQueryService;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.board.service.dto.BoardSearchResponse;
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

    private final BoardQueryService boardQueryService;
    private final BoardCommandService boardCommandService;

    public ChessController() {
        final ChessConfigure chessConfigure = new ChessConfigure();

        this.boardQueryService = new BoardQueryService(chessConfigure.boardRepository(),
                                                       chessConfigure.boardMapper());

        this.boardCommandService = new BoardCommandService(chessConfigure.boardRepository(),
                                                           chessConfigure.boardMapper());
    }

    public void run() {

        System.out.println("새로운 게임을 시작하려면 1, 게임을 불러오려면 2를 누르세요.");

        final int newGameCommand = InputView.readNewGameCommand();

        Color turn = Color.WHITE;

        final BoardFactory boardFactory = new BoardFactory();
        Board board = new Board(boardFactory);

        if (newGameCommand == 2) {
            System.out.println("현재 진행하고 있는 게임 번호들입니다.");
            System.out.println("불러올 게임의 번호를 입력해주세요.");

            final long loadGameNumber = InputView.readLoadGameCommand();

            final BoardSearchResponse boardSearchResponse = boardQueryService.searchBoard(loadGameNumber);

            board = new Board(boardSearchResponse.chessBoard());
            turn = Color.valueOf(boardSearchResponse.turn());
        }

        final String command = InputView.readStartCommand();

        if (Command.isNotStart(command)) {
            return;
        }

        startGame(board, turn);
    }

    private void startGame(final Board board, Color turn) {

        while (true) {
            OutputView.printBoard(board.chessBoard());

            final List<String> moveCommand = InputView.readMoveCommand();

            final String startCommand = moveCommand.get(START_COMMAND_INDEX.value());

            if (Command.isEnd(startCommand)) {
                BoardRegisterRequest boardRegisterRequest = new BoardRegisterRequest(board, turn);
                boardCommandService.registerBoard(boardRegisterRequest);
                return;
            }

            final BoardScore boardScore = BoardScore.flatByColumnFrom(board.chessBoard());

            if (Command.isStatus(startCommand)) {

                final Score blackScore = boardScore.calculateBoardScoreBy(Color.BLACK);
                final Score whiteScore = boardScore.calculateBoardScoreBy(Color.WHITE);

                OutputView.printWinner(whoIsWinner(blackScore, whiteScore), whiteScore, blackScore);
                return;
            }

            movePiece(board, turn, moveCommand, startCommand);

            final BoardSearch boardSearch = BoardSearch.countPiecePerClassTypeFrom(board.chessBoard());

            if (boardSearch.isKingDead()) {
                OutputView.printWinner(turn, boardScore.calculateBoardScoreBy(turn));
                return;
            }

            turn = turn.opposite();
        }
    }

    private Color whoIsWinner(final Score blackScore, final Score whiteScore) {
        if (blackScore.isGreaterThan(whiteScore)) {
            return Color.BLACK;
        } else if (blackScore.equals(whiteScore)) {
            return Color.NONE;
        }

        return Color.WHITE;
    }

    private void movePiece(final Board board, final Color turn,
                           final List<String> moveCommands, final String startCommand) {
        if (Command.isMove(startCommand)) {
            final Position fromPosition = convertPositionFrom(moveCommands.get(SOURCE_POSITION.value()));
            final Position toPosition = convertPositionFrom(moveCommands.get(TARGET_POSITION.value()));

            board.move(fromPosition, toPosition, turn);

        }
    }

    private Position convertPositionFrom(String moveCommand) {
        return new Position(moveCommand.charAt(POSITION_COLUMN.value()), moveCommand.charAt(POSITION_ROW.value()));
    }
}
