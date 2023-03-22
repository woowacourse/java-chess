package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.factory.BoardFactory;
import chess.domain.board.score.BoardScore;
import chess.domain.board.search.BoardSearch;
import chess.domain.board.score.Score;
import chess.domain.board.position.Position;
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

    public void run() {
        final BoardFactory boardFactory = new BoardFactory();
        final Board board = new Board(boardFactory);

        final String command = InputView.readStartCommand();

        if (Command.isNotStart(command)) {
            return;
        }

        final Color turn = Color.WHITE;
        startGame(board, turn);
    }

    private void startGame(final Board board, Color turn) {

        while (true) {
            OutputView.printBoard(board.chessBoard());

            final List<String> moveCommand = InputView.readMoveCommand();

            final String startCommand = moveCommand.get(START_COMMAND_INDEX.value());

            if (Command.isEnd(startCommand)) {
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
