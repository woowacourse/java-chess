package chess.controller;

import static chess.domain.CommandType.END;
import static chess.domain.CommandType.MOVE;
import static chess.domain.CommandType.START;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.dto.BoardDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void startGame() {
        String startCommand = InputView.inputCommand().get(0);
        while (!isCommandStart(startCommand) && !isCommandEnd(startCommand)) {
            OutputView.printInputAgainMessage();
            startCommand = InputView.inputCommand().get(0);
        }
        if (isCommandStart(startCommand)) {
            OutputView.printBoard(makeBoardDto(board.getBoard()));
            playGame();
        }
    }

    private void playGame() {
        Team turn = Team.WHITE;
        while (turn != Team.NONE) {
            turn = playTurn(turn);
        }
    }

    private Team playTurn(Team turn) {
        List<String> commands = InputView.inputCommand();
        String command = commands.get(0);
        while (!isCommandMove(command) && !isCommandEnd(command)) {
            OutputView.printInputAgainMessage();
            command = InputView.inputCommand().get(0);
        }
        if (isCommandMove(command)) {
            return playMoveCommand(commands, turn);
        }
        return Team.NONE;
    }

    private Team playMoveCommand(List<String> commands, Team turn) {
        boolean isMoveNoError = movePieceAndRenewBoard(commands, turn);
        if (isMoveNoError) {
            return Team.takeTurn(turn);
        }
        return turn;
    }

    private boolean isCommandStart(String startCommand) {
        return startCommand.equals(START.getCommandType());
    }

    private boolean isCommandMove(String startCommand) {
        return startCommand.equals(MOVE.getCommandType());
    }

    private boolean isCommandEnd(String startCommand) {
        return startCommand.equals(END.getCommandType());
    }

    private boolean movePieceAndRenewBoard(List<String> commands, Team turn) {
        Position source = Position.of(commands.get(1));
        Position target = Position.of(commands.get(2));
        if (source.equals(target) || !isCorrectTurn(source, turn)) {
            OutputView.printWrongMovementMessage();
            return false;
        }

        movePieceAndRenewBoard(source, target);
        OutputView.printBoard(makeBoardDto(board.getBoard()));
        return true;
    }

    public void movePieceAndRenewBoard(Position source, Position target) {
        board.movePieceAndRenewBoard(source, target);
    }

    public boolean isCorrectTurn(Position source, Team team) {
        return board.getBoard().get(source).isSameTeam(team);
    }

    private BoardDto makeBoardDto(Map<Position, Piece> board) {
        List<List<String>> rawBoard = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(8, "."));
            rawBoard.add(row);
        }

        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();

            int realYPosition = position.getY() - 1;
            int realXPosition = position.getX() - 1;
            PieceType pieceType = piece.getType();
            PieceInfo pieceInfo = piece.getPieceInfo();

            rawBoard.get(realYPosition).set(realXPosition, pieceType.getPieceLetter(pieceInfo.getTeam()));
        }
        return new BoardDto(rawBoard);
    }
}
