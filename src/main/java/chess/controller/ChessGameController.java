package chess.controller;

import chess.domain.board.Board;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Command;
import chess.domain.chessgame.ScoreBoard;
import chess.domain.db.ChessBoardDao;
import chess.domain.dto.BoardDto;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.ChessBoardToBoardDto;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.domain.chessgame.CommandType.*;

public class ChessGameController {
    private final ChessBoardDao chessBoardDao = new ChessBoardDao();
    private ChessGame chessGame;

    public void run() {
        chessGame = new ChessGame();

        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        play();
    }

    private void play() {
        try {
            playChess();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            play();
        }
    }

    private void playChess() {
        Command command = Command.of(InputView.inputCommand());
        boolean isEnd = playOneTurn(command);
        while (!isEnd) {
            command = Command.of(InputView.inputCommand());
            isEnd = playOneTurn(command);
        }
    }

    private boolean playOneTurn(final Command command) {
        if (command.isCommand(END)) {
            return true;
        }
        if (command.isCommand(MOVE)) {
            return move(command);
        }
        return continueCommand(command);
    }

    private boolean continueCommand(final Command command) {
        if (command.isCommand(START)) {
            start();
        }
        if (command.isCommand(STATUS)) {
            status();
        }
        if (command.isCommand(SAVE)) {
            save();
        }
        if (command.isCommand(LOAD)) {
            load();
        }
        return false;
    }

    private void start() {
        chessGame.start();
        OutputView.printBoard(BoardDto.of(chessGame.getBoard()));
    }

    private boolean move(final Command command) {
        Position source = Position.of(command.getSource());
        Position target = Position.of(command.getTarget());
        chessGame.move(source, target);

        OutputView.printBoard(BoardDto.of(chessGame.getBoard()));
        return isFinished();
    }

    private boolean isFinished() {
        if (chessGame.isWin()) {
            OutputView.printWinner(chessGame.getTurn());
            return true;
        }
        return false;
    }

    private void status() {
        ScoreBoard scoreBoard = chessGame.status();
        Team winner = chessGame.findWinner(scoreBoard);
        result(winner, scoreBoard);
    }

    private void result(final Team winner, final ScoreBoard scoreBoard) {
        double whiteScore = scoreBoard.getWhiteScore();
        double blackScore = scoreBoard.getBlackScore();
        OutputView.printBoard(BoardDto.of(chessGame.getBoard()));
        if (winner.equals(Team.NONE)) {
            OutputView.printScoreWithDraw(whiteScore, blackScore);
            return;
        }
        OutputView.printScoreWithWinner(whiteScore, blackScore, winner);
    }

    private void save() {
        ChessBoardDto chessBoardDto = ChessBoardDto.of(chessGame.getBoard().values().stream().toList());
        chessBoardDto.getPieces().forEach(chessBoardDao::addPiece);
    }

    private void load() {
        ChessBoardToBoardDto chessBoardToBoardDto = ChessBoardToBoardDto.of(chessBoardDao.findAll());
        chessGame = new ChessGame(new Board(chessBoardToBoardDto.getPieces()), Team.WHITE);
    }
}
