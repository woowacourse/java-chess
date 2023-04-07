package chess.service;

import chess.controller.ChessBoardDto;
import chess.controller.MoveCommand;
import chess.controller.ScoreDto;
import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.PlayerScore;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.ResultDto;

import java.util.Optional;

public class ChessGameService {

    private final ChessGameDao chessGameDao = new ChessGameDaoImpl();

    public ChessGame readGame() {
        final Optional<ChessGame> optionalChessGame = chessGameDao.find();

        return optionalChessGame.orElseGet(this::generateNewChessGame);
    }

    private ChessGame generateNewChessGame() {
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        final ChessBoard chessBoard = chessBoardFactory.generate();
        final ChessGame chessGame = new ChessGame(chessBoard);

        chessGameDao.save(chessGame);

        return chessGame;
    }

    public boolean isGameOver(final ChessGame chessGame) {
        return chessGame.isGameOver();
    }

    public ResultDto getResult(final ChessGame chessGame) {
        return new ResultDto(chessGame.getWinner());
    }

    public ScoreDto calculateScores(final ChessGame chessGame) {
        final PlayerScore whiteScore = chessGame.calculateScore(Color.WHITE);
        final PlayerScore blackScore = chessGame.calculateScore(Color.BLACK);

        return new ScoreDto(whiteScore, blackScore);
    }

    public ChessBoardDto moveWithCapture(final ChessGame chessGame, final MoveCommand moveCommand) {
        final Position from = moveCommand.getSourcePosition();
        final Position to = moveCommand.getDestinationPosition();

        chessGame.moveWithCapture(from, to);

        return ChessBoardDto.from(chessGame.getChessBoard());
    }

    public ChessBoardDto getChessBoard(final ChessGame chessGame) {
        return ChessBoardDto.from(chessGame.getChessBoard());
    }
}
