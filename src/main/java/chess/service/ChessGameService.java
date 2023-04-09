package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.PlayerScore;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.dto.ScoreDto;
import chess.dto.WinnerDto;
import chess.dto.dtomapper.ChessBoardMapper;
import chess.dto.dtomapper.ResultMapper;

public class ChessGameService {

    private final ChessGameDao chessGameDao = new ChessGameDaoImpl();
    private final ChessBoardMapper chessBoardMapper = new ChessBoardMapper();
    private final ResultMapper resultMapper = new ResultMapper();


    public ChessGame createNewGame(final int gameId) {
        validateNotDuplicateGameId(gameId);

        final ChessGame chessGame = ChessGame.createNewChessGame(gameId);
        chessGameDao.save(chessGame);
        return chessGame;
    }

    private void validateNotDuplicateGameId(final int gameId) {
        if (chessGameDao.findById(gameId)
                        .isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다");
        }
    }

    public ChessGame loadGame(final int gameId) {
        return chessGameDao.findById(gameId)
                           .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다"));
    }

    public PieceDto[][] moveWithCapture(final ChessGame chessGame, final Position from, final Position to) {
        chessGame.moveWithCapture(from, to);
        chessGameDao.update(chessGame);
        return chessBoardMapper.toDto(chessGame.getChessBoard());
    }

    public boolean isGameOver(final ChessGame chessGame) {
        return chessGame.isGameOver();
    }

    public ScoreDto calculateScores(final ChessGame chessGame) {
        final PlayerScore whiteScore = chessGame.calculateScore(Color.WHITE);
        final PlayerScore blackScore = chessGame.calculateScore(Color.BLACK);
        return resultMapper.scoreToDto(whiteScore, blackScore);
    }

    public WinnerDto getWinner(final ChessGame chessGame) {
        return resultMapper.winnerToDto(chessGame.getWinner());
    }

    public PieceDto[][] getChessBoard(final ChessGame chessGame) {
        return chessBoardMapper.toDto(chessGame.getChessBoard());
    }
}
