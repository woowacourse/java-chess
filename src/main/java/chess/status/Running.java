package chess.status;

import chess.chessboard.Side;
import chess.chessboard.Square;
import chess.chessgame.ChessGame;
import chess.controller.*;

public class Running implements GameStatus {

    private final ChessGame chessGame;

    public Running(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public GameStatus playGame(final Command command, final PrintAction printAction) {
        validate(command);

        if (command.getCommandType() == CommandType.MOVE) {
            return move((MoveCommand) command, printAction);
        }
        return status(printAction);
    }

    private void validate(final Command command) {
        validateNotStart(command);
        validateNotEnd(command);
    }

    private void validateNotEnd(final Command command) {
        if (command.getCommandType() == CommandType.END) {
            throw new EndCommandException("Running의 command로 END가 입력됨");
        }
    }

    private GameStatus status(final PrintAction printAction) {
        final double whiteScore = chessGame.getScore(Side.WHITE);
        final double blackScore = chessGame.getScore(Side.BLACK);
        final ScoreDto scoreDto = new ScoreDto(whiteScore, blackScore);

        printAction.run(new ResultDto(scoreDto, new WinnerDto(chessGame.getWinner())));

        return this;
    }

    private GameStatus move(final MoveCommand moveCommand, final PrintAction printAction) {
        final Square sourceSquare = moveCommand.getSourceSquare();
        final Square destinationSquare = moveCommand.getDestinationSquare();
        chessGame.move(sourceSquare, destinationSquare);

        if (chessGame.isKingDead()) {
            return new Finished(chessGame);
        }

        printAction.run(ChessBoardDto.of(chessGame.getChessBoard()));
        return this;
    }

    private void validateNotStart(final Command command) {
        if (command.getCommandType() == CommandType.START) {
            throw new IllegalArgumentException("게임은 이미 진행 중입니다");
        }
    }
}
