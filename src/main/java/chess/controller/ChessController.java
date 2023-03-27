package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.dto.PositionRequest;
import chess.dto.SquareResponse;
import chess.game.ChessGame;
import chess.game.Turn;
import chess.service.ChessGameService;
import chess.view.*;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class ChessController {

    private static final int NEW_GAME_NUMBER = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final ChessGameService chessGameService;

    public ChessController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void start() {
        List<Integer> allGameIds = chessGameService.findAllGameIds();
        if (allGameIds.isEmpty()) {
            startNewGame();
            return;
        }
        OutputView.printGameNumberMessage(chessGameService.findAllGameIds());
        int gameNumber = repeat(() -> InputView.readGameNumber(allGameIds));
        if (gameNumber == NEW_GAME_NUMBER) {
            startNewGame();
            return;
        }
        startExistGame(gameNumber);
    }

    private void startNewGame() {
        Board board = new Board(BoardFactory.create());
        ChessGame chessGame = new ChessGame(board, new Turn(Team.WHITE));
        chessGameService.save(chessGame, GameStatus.CONTINUING.name());
        start(chessGame);
    }

    private void startExistGame(int gameNumber) {
        ChessGame chessGame = chessGameService.findById(gameNumber);
        start(chessGame);
    }

    public void start(ChessGame chessGame) {
        OutputView.printStartMessage();
        ChessEvent chessEvent = repeat(InputView::readGameCommand);
        if (chessEvent == ChessEvent.START) {
            OutputView.printBoard(convertBoardToResponse(chessGame));
            while (gameLoop(chessGame) == GameStatus.CONTINUING) ;
        }
    }

    private static List<SquareResponse> convertBoardToResponse(ChessGame chessGame) {
        return chessGame.getChessBoard().getBoard()
                .entrySet()
                .stream()
                .map(entry -> SquareResponse.of(entry.getKey(), entry.getValue()))
                .collect(toList());
    }

    private GameStatus gameLoop(ChessGame chessGame) {
        List<String> commands = repeat(InputView::readMoveCommand);
        return executeByCommands(chessGame, commands);
    }

    private GameStatus executeByCommands(ChessGame chessGame, List<String> commands) {
        ChessAction chessAction = ChessAction.of(commands).orElse(ChessAction.END);

        if (chessAction == ChessAction.MOVE) {
            move(chessGame, commands);
            return canContinue(chessGame);
        }
        if (chessAction == ChessAction.STATUS) {
            showTeamScores(chessGame);
            return GameStatus.CONTINUING;
        }
        chessGameService.update(chessGame, GameStatus.CONTINUING.name());
        return GameStatus.END;
    }

    private void move(ChessGame chessGame, List<String> command) {
        try {
            PositionRequest source = PositionMapper.map(command.get(SOURCE_INDEX));
            PositionRequest target = PositionMapper.map(command.get(TARGET_INDEX));
            chessGame.move(convertRequestToPosition(source), convertRequestToPosition(target));
            OutputView.printBoard(convertBoardToResponse(chessGame));
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private GameStatus canContinue(ChessGame chessGame) {
        if (chessGame.isEndCondition()) {
            chessGameService.update(chessGame, GameStatus.END.name());
            Team losingTeam = chessGame.getCurrentTurn().getTeam();
            Team winningTeam = chessGame.getCurrentTurn().next().getTeam();
            OutputView.printEndMessage(winningTeam, losingTeam);
            return GameStatus.END;
        }
        return GameStatus.CONTINUING;
    }

    private void showTeamScores(ChessGame chessGame) {
        double whiteTeamScore = chessGame.calculateScore(Team.WHITE);
        double blackTeamScore = chessGame.calculateScore(Team.BLACK);
        OutputView.printScore(whiteTeamScore, blackTeamScore);
    }

    private static Position convertRequestToPosition(PositionRequest request) {
        return Position.of(request.getX(), request.getY());
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeat(supplier);
        }
    }
}
