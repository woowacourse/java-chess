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
    private static final int BOARD_SIZE = 8;
    private static final int INDEX_OFFSET = 1;
    private static final String EMPTY_PIECE = ".";

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void startGame() {
        String startCommand = InputView.inputCommand().get(0);
        while (START.differentCommand(startCommand) && END.differentCommand(startCommand)) {
            OutputView.printInputAgainMessage();
            startCommand = InputView.inputCommand().get(0);
        }
        if (START.sameCommand(startCommand)) {
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
        while (MOVE.differentCommand(command) && END.differentCommand(command)) {
            OutputView.printInputAgainMessage();
            command = InputView.inputCommand().get(0);
        }
        if (MOVE.sameCommand(command)) {
            return playMoveCommand(commands, turn);
        }
        return Team.NONE;
    }

    private Team playMoveCommand(List<String> commands, Team turn) {
        Position source = Position.of(commands.get(1));
        Position target = Position.of(commands.get(2));
        if (source.equals(target) || !isCorrectTurn(source, turn) || !board.movePieceAndRenewBoard(source, target)) {
            OutputView.printWrongMovementMessage();
            return turn;
        }
        
        OutputView.printBoard(makeBoardDto(board.getBoard()));
        return Team.takeTurn(turn);
    }

    private boolean isCorrectTurn(Position source, Team team) {
        return board.getBoard().get(source).isSameTeam(team);
    }

    private BoardDto makeBoardDto(Map<Position, Piece> board) {
        List<List<String>> rawBoard = makeRawBoard();
        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();
            PieceType pieceType = piece.getType();
            PieceInfo pieceInfo = piece.getPieceInfo();

            rawBoard.get(position.getY() - INDEX_OFFSET)
                    .set(position.getX() - INDEX_OFFSET, pieceType.getPieceLetter(pieceInfo.getTeam()));
        }
        return new BoardDto(rawBoard);
    }

    private List<List<String>> makeRawBoard() {
        List<List<String>> rawBoard = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(BOARD_SIZE, EMPTY_PIECE));
            rawBoard.add(row);
        }

        return rawBoard;
    }
}
