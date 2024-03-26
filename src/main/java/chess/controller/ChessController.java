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
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        validateStartCommand();
        Board board = new Board(BoardFactory.generateStartBoard());
        DbManager dbManager = new DbManager();
        ChessGame chessGame = new ChessGame(board);
        dbManager.initialize(board, Team.WHITE);
        OutputView.printGameState(new BoardStatusDto(board.getPieces(), State.NORMAL));

        play(chessGame, board, dbManager);
    }

    private void validateStartCommand() {
        try {
            InputView.inputStartCommand();
        } catch (InvalidCommandException e) {
            OutputView.printErrorMessage(e.getMessage());
            validateStartCommand();
        }
    }

    private void play(ChessGame chessGame, Board board, DbManager dbManager) {
        try {
            playTurns(chessGame, board, dbManager);
        } catch (InvalidCommandException | ImpossibleMoveException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame, board, dbManager);
        }
    }

    private void playTurns(ChessGame chessGame, Board board, DbManager dbManager) {
        CommandDto commandDto;
        State state = State.NORMAL;
        while ((commandDto = InputView.inputCommand()).gameCommand() == GameCommand.MOVE && state != State.CHECKMATE) {
            moveAndUpdate(chessGame, commandDto.toDomain(), dbManager);
            state = chessGame.checkStatus();
            OutputView.printGameState(new BoardStatusDto(board.getPieces(), state));
        }
        printWinnerByStatus(board, commandDto.gameCommand());
        printWinnerByCheckmate(chessGame, state);
    }

    private void moveAndUpdate(ChessGame chessGame, Movement movement, DbManager dbManager) {
        Piece piece = chessGame.movePiece(movement);
        dbManager.update(movement, piece, chessGame.getCurrentTeam());
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
