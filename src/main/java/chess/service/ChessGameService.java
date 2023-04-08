package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chessgame.ChessGame;

import java.util.Optional;

public class ChessGameService {

    private final ChessGameDao chessGameDao = new ChessGameDaoImpl();

    public ChessGame readGame() {
        final Optional<ChessGame> optionalChessGame = chessGameDao.find();

        return optionalChessGame.orElseGet(this::generateNewChessGame);
    }

    private ChessGame generateNewChessGame() {
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        final ChessBoard chessBoard = chessBoardFactory.createInitialBoard();
        final ChessGame chessGame = null;

        chessGameDao.save(chessGame);

        return chessGame;
    }

//    public boolean isGameOver(final ChessGame chessGame) {
//        return chessGame.isGameOver();
//    }
//
//    public ResultDto getResult(final ChessGame chessGame) {
//        return new ResultDto(chessGame.getWinner());
//    }
//
//    public ScoreDto calculateScores(final ChessGame chessGame) {
//        final PlayerScore whiteScore = chessGame.calculateScore(Color.WHITE);
//        final PlayerScore blackScore = chessGame.calculateScore(Color.BLACK);
//
//        return new ScoreDto(whiteScore, blackScore);
//    }
//
//    public ChessBoardDto moveWithCapture(final ChessGame chessGame, final MoveCommand moveCommand) {
//        final Position from = moveCommand.getSourcePosition();
//        final Position to = moveCommand.getDestinationPosition();
//
//        chessGame.moveWithCapture(from, to);
//
//        return ChessBoardDto.from(chessGame.getChessBoard());
//    }
//
//    public ChessBoardDto getChessBoard(final ChessGame chessGame) {
//        return ChessBoardDto.from(chessGame.getChessBoard());
//    }
}
