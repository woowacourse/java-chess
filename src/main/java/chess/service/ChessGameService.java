package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.Position;
import chess.domain.piece.pieceinfo.TeamType;
import chess.dto.ChessGameDto;

import java.util.Set;

public class ChessGameService {
    private static final int NEW_GAME_ID = 1;
    private static final int BLACK_TURN = -1;
    private static final int WHITE_TURN = 1;

    private static ChessGameDao chessGameDao = ChessGameDao.getInstance();

    private ChessGameService() {
    }

    private static class ChessGameServiceHolder {
        static final ChessGameService CHESS_GAME_SERVICE = new ChessGameService();
    }

    public static ChessGameService getInstance() {
        return ChessGameService.ChessGameServiceHolder.CHESS_GAME_SERVICE;
    }

    public ChessGameDto.GameLoading createNewGame() {
        chessGameDao.addChessGame(chessGameDao.findChessGameById(NEW_GAME_ID), BLACK_TURN);

        return createLatestGame();
    }

    public ChessGameDto.GameLoading createLatestGame() {
        int latestId = chessGameDao.findLatestChessGameId();
        return createGameBy(latestId);
    }

    private ChessGameDto.GameLoading createGameBy(int gameId) {
        String newBoard = chessGameDao.findChessGameById(gameId);
        int turn = chessGameDao.findTurnById(gameId);

        Board board = BoardGenerator.createBoard(newBoard);
        String currentTeam = (turn == WHITE_TURN) ? "WHITE" : "BLACK";

        return new ChessGameDto.GameLoading(board.getBoard(), currentTeam);
    }

    public Set<Position> getPossiblePositions(String seletedPosition) {
        Position source = Position.of(seletedPosition);
        Game game = getLatestGame();

        return game.selectSourcePiece(source);
    }

    public ChessGameDto.GameLoading movePiece(String sourceInput, String destinationInput) {
        Position source = Position.of(sourceInput);
        Position destination = Position.of(destinationInput);
        Game game = getLatestGame();
        game.move(source, destination);
        chessGameDao.addChessGame(game.getBoard().parseBoard(),
                game.getCurrentTeam() == TeamType.WHITE ? BLACK_TURN : WHITE_TURN);

        return createLatestGame();
    }

    private Game getLatestGame() {
        int latestId = chessGameDao.findLatestChessGameId();
        String board = chessGameDao.findChessGameById(latestId);
        TeamType teamType = (chessGameDao.findTurnById(latestId) == WHITE_TURN) ? TeamType.WHITE : TeamType.BLACK;
        Board latestBoard = BoardGenerator.createBoard(board);
        return new Game(latestBoard, teamType);
    }

    public double calculateScore(TeamType teamType) {
        int latestId = chessGameDao.findLatestChessGameId();
        String board = chessGameDao.findChessGameById(latestId);
        Board latestBoard = BoardGenerator.createBoard(board);
        Game game = new Game(latestBoard, teamType);

        return game.getScore(teamType);
    }
}
