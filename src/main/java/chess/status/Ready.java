package chess.status;

import chess.chessboard.ChessBoard;
import chess.chessboard.ChessBoardFactory;
import chess.chessgame.ChessGame;
import chess.chessgame.Turn;
import chess.controller.ChessBoardDto;
import chess.controller.Command;
import chess.controller.CommandType;
import chess.controller.PrintAction;
import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;

import java.util.Optional;

public class Ready implements GameStatus {

    private final ChessGameDao chessGameDao;

    public Ready() {
        this(new ChessGameDaoImpl());
    }

    public Ready(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    @Override
    public GameStatus playGame(final Command command, final PrintAction printAction) {
        validateStartCommand(command);

        final Optional<ChessGame> optionalChessGame = chessGameDao.find();
        final ChessBoard chessBoard = new ChessBoardFactory().generate();
        final ChessGame chessGame = optionalChessGame.orElse(new ChessGame(Turn.initialTurn(), chessBoard));

        printAction.run(ChessBoardDto.of(chessGame.getChessBoard()));

        return new Running(chessGame, chessGameDao);
    }

    private void validateStartCommand(final Command command) {
        if (command.getCommandType() != CommandType.START) {
            throw new IllegalArgumentException("게임이 진행 중이지 않습니다");
        }
    }
}
