package chess.status;

import chess.chessboard.ChessBoard;
import chess.chessboard.ChessBoardFactory;
import chess.chessgame.ChessGame;
import chess.controller.ChessBoardDto;
import chess.controller.Command;
import chess.controller.CommandType;
import chess.controller.PrintAction;

public class Ready implements GameStatus {

    @Override
    public GameStatus playGame(final Command command, final PrintAction printAction) {
        validateStartCommand(command);

        final ChessBoard chessBoard = new ChessBoardFactory().generate();
        final ChessGame chessGame = new ChessGame(chessBoard);

        printAction.run(ChessBoardDto.of(chessBoard));

        return new Running(chessGame);
    }

    private void validateStartCommand(final Command command) {
        if (command.getCommandType() != CommandType.START) {
            throw new IllegalArgumentException("게임이 진행 중이지 않습니다");
        }
    }
}
