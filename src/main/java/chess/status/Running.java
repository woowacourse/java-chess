package chess.status;

import chess.chessboard.Side;
import chess.chessboard.Square;
import chess.chessgame.ChessGame;
import chess.controller.*;
import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;

public class Running implements GameStatus {

    private final ChessGame chessGame;
    private final ChessGameDao chessGameDao;

    public Running(final ChessGame chessGame, final ChessGameDao chessGameDao) {
        this.chessGame = chessGame;
        this.chessGameDao = chessGameDao;
    }

    public Running(final ChessGame chessGame) {
        this(chessGame, new ChessGameDaoImpl());
    }

    @Override
    public GameStatus playGame(final Command command, final PrintAction printAction) {
        validateNotStart(command);

        if (command.getCommandType() == CommandType.MOVE) {
            return move((MoveCommand) command, printAction);
        }
        if (command.getCommandType() == CommandType.STATUS) {
            return status(printAction);
        }
        return null;
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
        chessGameDao.update(chessGame);

        if (chessGame.isKingDead()) {
            return new Finished(chessGame, chessGameDao);
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
