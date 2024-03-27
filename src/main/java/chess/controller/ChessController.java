package chess.controller;

import chess.db.DbManager;
import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Movement;
import chess.domain.State;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;
import chess.dto.BoardStatusDto;
import chess.dto.CommandDto;
import chess.exception.ImpossibleMoveException;
import chess.exception.InvalidCommandException;
import chess.exception.InvalidGameRoomException;
import chess.util.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.SQLException;

public class ChessController {
    public void run() throws SQLException {
        try {
            switch (validateStartCommand()) {
                case START -> startNewGame();
                case LOAD -> loadGame();
            }
        } catch (InvalidGameRoomException e) {
            OutputView.printErrorMessage(e.getMessage());
            run();
        }
    }

    private void loadGame() throws SQLException {
        final String roomName = InputView.inputLoadRoomName();
        DbManager dbManager = new DbManager();
        ChessGame chessGame = dbManager.loadChessGame(roomName);
        Board board = chessGame.getBoard();
        OutputView.printGameState(new BoardStatusDto(board.getPieces(), chessGame.checkState()));

        play(chessGame, dbManager, roomName);
    }

    private void startNewGame() throws SQLException {
        final String roomName = InputView.inputNewRoomName();
        DbManager dbManager = new DbManager();
        Board board = new Board(BoardFactory.generateStartBoard());
        ChessGame chessGame = new ChessGame(board);
        dbManager.initialize(roomName, board, chessGame.getCurrentTeam());
        OutputView.printGameState(new BoardStatusDto(board.getPieces(), State.NORMAL));

        play(chessGame, dbManager, roomName);
    }

    private GameCommand validateStartCommand() {
        try {
            return InputView.inputStartCommand();
        } catch (InvalidCommandException e) {
            OutputView.printErrorMessage(e.getMessage());
            return validateStartCommand();
        }
    }

    private void play(ChessGame chessGame, DbManager dbManager, String roomName) throws SQLException {
        try {
            playTurns(chessGame, dbManager, roomName);
        } catch (InvalidCommandException | ImpossibleMoveException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame, dbManager, roomName);
        }
    }

    private void playTurns(ChessGame chessGame, DbManager dbManager, String roomName) throws SQLException {
        CommandDto commandDto = new CommandDto();
        State state = chessGame.checkState();
        Board board = chessGame.getBoard();
        while (state != State.CHECKMATE && (commandDto = InputView.inputCommand()).gameCommand() == GameCommand.MOVE) {
            playTurn(chessGame, dbManager, roomName, commandDto.toMovementDomain());
            state = chessGame.checkState();
        }
        printWinnerByStatus(board, commandDto.gameCommand());
        printWinnerByCheckmate(chessGame, state);
        dbManager.deleteChessGame(roomName);
    }

    private void playTurn(ChessGame chessGame, DbManager dbManager, String roomName, Movement movement)
            throws SQLException {
        Board board = chessGame.getBoard();
        Piece piece = chessGame.movePiece(movement);
        dbManager.update(roomName, movement, piece, chessGame.getCurrentTeam());
        OutputView.printGameState(new BoardStatusDto(board.getPieces(), chessGame.checkState()));
    }

    private void printWinnerByStatus(Board board, GameCommand gameCommand) {
        if (gameCommand == GameCommand.STATUS) {
            OutputView.printPoint(Team.WHITE, board.calculateScore(Team.WHITE));
            OutputView.printPoint(Team.BLACK, board.calculateScore(Team.BLACK));
            OutputView.printWinner(board.findResultByScore());
        }
    }

    private void printWinnerByCheckmate(ChessGame chessGame, State state) {
        if (state == State.CHECKMATE) {
            OutputView.printWinner(chessGame.getCurrentTeam().opponent());
        }
    }
}
