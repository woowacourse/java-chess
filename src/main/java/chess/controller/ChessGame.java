package chess.controller;

import static chess.domain.CommandType.END;
import static chess.domain.CommandType.MOVE;
import static chess.domain.CommandType.START;

import chess.domain.Board;
import chess.domain.Command;
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
        Command command = Command.from(InputView.inputCommand());
        while (!command.isTypeEqualTo(START) && !command.isTypeEqualTo(END)) {
            OutputView.printInputAgainMessage();
            command = Command.from(InputView.inputCommand());
        }
        if (command.isTypeEqualTo(START)) {
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
        Command command = Command.from(InputView.inputCommand());
        while (!command.isTypeEqualTo(MOVE) && !command.isTypeEqualTo(END)) {
            OutputView.printInputAgainMessage();
            command = Command.from(InputView.inputCommand());
        }
        if (command.isTypeEqualTo(MOVE)) {
            return playMoveCommand(command, turn);
        }
        return Team.NONE;
    }

    private Team playMoveCommand(Command command, Team turn) {
        Position source = command.getSource();
        Position target = command.getTarget();
        if (isOppositeTurn(source, turn) || !board.movePieceAndRenewBoard(source, target)) {
            OutputView.printWrongMovementMessage();
            return turn;
        }

        OutputView.printBoard(makeBoardDto(board.getBoard()));
        return Team.takeTurn(turn);
    }

    private boolean isOppositeTurn(Position source, Team team) {
        return board.isPieceFromOtherTeam(source, team);
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
