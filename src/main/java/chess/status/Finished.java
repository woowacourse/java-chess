package chess.status;

import chess.chessgame.ChessGame;
import chess.controller.*;
import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;

public class Finished implements GameStatus {

    private final ChessGame chessGame;
    private final ChessGameDao chessGameDao;

    public Finished(final ChessGame chessGame) {
        this.chessGame = chessGame;
        this.chessGameDao = new ChessGameDaoImpl();
    }

    public Finished(final ChessGame chessGame, final ChessGameDao chessGameDao) {
        this.chessGame = chessGame;
        this.chessGameDao = chessGameDao;
    }

    @Override
    public GameStatus playGame(final Command command, final PrintAction printAction) {
        if (command.getCommandType() == CommandType.END) {
            chessGameDao.delete();
        }
        validateStatusCommand(command);

        printAction.run(new ResultDto(new ScoreDto(0, 0), new WinnerDto(chessGame.getWinner())));

        return this;
    }

    private void validateStatusCommand(final Command command) {
        if (command.getCommandType() != CommandType.STATUS) {
            throw new IllegalArgumentException("게임이 종료되었습니다");
        }
    }
}
