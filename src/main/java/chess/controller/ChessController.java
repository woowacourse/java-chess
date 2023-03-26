package chess.controller;

import chess.dao.ChessGameDao;
import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.ChessBoardGenerator;
import chess.domain.chessGame.Referee;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.request.CommandDto;
import chess.dto.response.ChessBoardDto;
import chess.dto.response.StatusDto;
import chess.utils.BoardToString;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;
import java.util.function.Supplier;

public final class ChessController {

    private static final String BLACK_TEAM = "검은색";
    private static final String WHITE_TEAM = "흰색";
    private static final String DRAW = "무승부";

    private final ChessGameDao chessGameDao;

    public ChessController(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        OutputView.printStartMessage();
        CommandDto commandDto = repeat(InputView::readInitialCommand);
        if (commandDto.getGameCommand() == GameCommand.START) {
            startGame();
        }
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return repeat(supplier);
        }
    }

    private void startGame() {
        ChessBoard chessBoard = setUpChessBoard();
        showChessBoardStatus(chessBoard);
        checkKingAlive(chessBoard);
        try {
            repeat(() -> playGame(chessBoard));
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkKingAlive(ChessBoard chessBoard) {
        if (chessBoard.isKingDead()) {
            chessGameDao.delete(chessBoard);
            throw new IllegalStateException("[ERROR] King이 죽었기 때문에 끝난 게임입니다.");
        }
    }

    private void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            repeat(runnable);
        }
    }

    private ChessBoard setUpChessBoard() {
        ChessBoard chessBoard = chessGameDao.select();
        if (chessBoard != null) {
            return chessBoard;
        }
        ChessBoardGenerator generator = new ChessBoardGenerator();
        chessBoard = new ChessBoard(generator.generate(), Color.WHITE);
        return chessBoard;
    }

    private void playGame(ChessBoard chessBoard) {
        CommandDto commandDto = InputView.readPlayingCommand();
        while (commandDto.getGameCommand() != GameCommand.END) {
            executePlayingCommand(chessBoard, commandDto);
            commandDto = InputView.readPlayingCommand();
        }
    }

    private void executePlayingCommand(ChessBoard chessBoard, CommandDto commandDto) {
        GameCommand gameCommand = commandDto.getGameCommand();
        if (gameCommand == GameCommand.MOVE) {
            executeMoveCommand(chessBoard, commandDto);
        }
        if (gameCommand == GameCommand.STATUS) {
            showStatus(chessBoard);
        }
    }

    private void executeMoveCommand(ChessBoard chessBoard, CommandDto commandDto) {
        String startInput = commandDto.getStartPosition();
        String endInput = commandDto.getEndPosition();

        chessBoard.movePiece(Position.of(startInput), Position.of(endInput));
        showChessBoardStatus(chessBoard);
        checkKingAlive(chessBoard);
        chessGameDao.update(chessBoard);
    }

    private void showChessBoardStatus(ChessBoard chessBoard) {
        Map<Position, Piece> chessBoardStatus = chessBoard.getChessBoard();
        String turn = chessBoard.getTurn().name();
        ChessBoardDto chessBoardDto = ChessBoardDto.of(BoardToString.convert(chessBoardStatus));
        OutputView.printChessBoard(chessBoardDto);
        OutputView.printTurn(turn);
    }

    private void showStatus(ChessBoard chessBoard) {
        Referee referee = new Referee(chessBoard.getChessBoard());

        double blackScore = referee.calculateScore(Color.BLACK);
        double whiteScore = referee.calculateScore(Color.WHITE);
        String winner = getWinner(blackScore, whiteScore);

        StatusDto statusDto = StatusDto.of(blackScore, whiteScore, winner);
        OutputView.printStatus(statusDto);
    }

    private String getWinner(double blackScore, double whiteScore) {
        Color winningColor = getWinningColor(blackScore, whiteScore);
        if (winningColor == Color.BLACK) {
            return BLACK_TEAM;
        }
        if (winningColor == Color.WHITE) {
            return WHITE_TEAM;
        }
        return DRAW;
    }

    private Color getWinningColor(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            return Color.BLACK;
        }
        if (blackScore < whiteScore) {
            return Color.WHITE;
        }
        return null;
    }
}
