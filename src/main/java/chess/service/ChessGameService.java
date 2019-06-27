package chess.service;

import chess.dao.ChessGameDAO;
import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.Position;
import chess.domain.piece.pieceinfo.TeamType;
import chess.dto.ChessGameDTO;

import java.util.Set;

public class ChessGameService {
    private static final int NEW_GAME_ID = 1;
    private static final int BLACK_TURN = -1;
    private static final int WHITE_TURN = 1;

    private static ChessGameDAO chessGameDAO = ChessGameDAO.getInstance();

    private ChessGameService() {
    }

    private static class ChessGameServiceHolder {
        static final ChessGameService CHESS_GAME_SERVICE = new ChessGameService();
    }

    public static ChessGameService getInstance() {
        return ChessGameService.ChessGameServiceHolder.CHESS_GAME_SERVICE;
    }

    public ChessGameDTO.GameLoading createNewGame() {
        chessGameDAO.addChessGame(chessGameDAO.findChessGameById(NEW_GAME_ID), BLACK_TURN);

        return createLatestGame();
    }

    public ChessGameDTO.GameLoading createLatestGame() {
        int latestId = chessGameDAO.findLatestChessGameId();
        return createGameBy(latestId);
    }

    private ChessGameDTO.GameLoading createGameBy(int gameId) {
        String newBoard = chessGameDAO.findChessGameById(gameId);
        int turn = chessGameDAO.findTurnById(gameId);

        Board board = BoardGenerator.createBoard(newBoard);
        String currentTeam = (turn == WHITE_TURN) ? "WHITE" : "BLACK";

        return new ChessGameDTO.GameLoading(board.getBoard(), currentTeam);
    }

    public Set<Position> getPossiblePositions(String seletedPosition) {
        Position source = Position.of(seletedPosition);
        int latestId = chessGameDAO.findLatestChessGameId();
        String board = chessGameDAO.findChessGameById(latestId);
        TeamType teamType = (chessGameDAO.findTurnById(latestId) == WHITE_TURN) ? TeamType.WHITE : TeamType.BLACK;
        Board latestBoard = BoardGenerator.createBoard(board);
        Game game = new Game(latestBoard, teamType);

        return game.selectSourcePiece(source);
    }

    // TODO 중복 제거
    public ChessGameDTO.GameLoading movePiece(String sourceInput, String destinationInput) {
        Position source = Position.of(sourceInput);
        Position destination = Position.of(destinationInput);
        int latestId = chessGameDAO.findLatestChessGameId();
        String board = chessGameDAO.findChessGameById(latestId);
        TeamType teamType = (chessGameDAO.findTurnById(latestId) == WHITE_TURN) ? TeamType.WHITE : TeamType.BLACK;
        Board latestBoard = BoardGenerator.createBoard(board);
        Game game = new Game(latestBoard, teamType);
        game.move(source, destination);
        chessGameDAO.addChessGame(game.getBoard().parseBoard(), teamType == TeamType.WHITE ? BLACK_TURN : WHITE_TURN);

        return createLatestGame();
    }

    public double calculateScore(TeamType teamType) {
        int latestId = chessGameDAO.findLatestChessGameId();
        String board = chessGameDAO.findChessGameById(latestId);
        Board latestBoard = BoardGenerator.createBoard(board);
        Game game = new Game(latestBoard, teamType);

        return game.getScore(teamType);
    }
}
