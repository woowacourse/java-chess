package chess.service;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.model.ChessGame;
import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Status;
import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.model.board.Square;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.service.dto.BoardDto;
import chess.service.dto.ChessGameDto;
import chess.service.dto.GameResultDto;
import chess.service.dto.GamesDto;
import chess.service.dto.PieceWithSquareDto;
import chess.service.dto.StatusDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessService {
    private final BoardDao boardDao;
    private final GameDao gameDao;

    public ChessService(BoardDao boardDao, GameDao gameDao) {
        this.boardDao = boardDao;
        this.gameDao = gameDao;
    }

    public void initGame(int gameId) {
        ChessGame chessGame = new ChessGame(new ChessInitializer(), Status.PLAYING);
        boardDao.initBoard(gameId);
        updateGame(chessGame, gameId);
    }

    private void updateGame(ChessGame chessGame, int id) {
        gameDao.update(new ChessGameDto(id, chessGame.getStatus().name(), chessGame.getTurn().name()));
    }

    public BoardDto getBoard(int id) {
        return boardDao.getBoardByGameId(id);
    }

    public List<List<String>> getAllPieceLetter(int id) {
        return Rank.getRanksInBoardOrder().stream()
                .map(rank -> getPieceLetterInRank(getGameFromDao(id).getBoard(), rank))
                .collect(Collectors.toList());
    }

    private List<String> getPieceLetterInRank(Board board, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> board.findPieceBySquare(Square.of(file, rank)))
                .map(PieceType::getLetterByColor)
                .collect(Collectors.toList());
    }

    public void move(int id, String from, String to) {
        ChessGame chessGame = getGameFromDao(id);
        Square fromSquare = Square.of(from);
        Square toSquare = Square.of(to);
        chessGame.move(fromSquare, toSquare);
        boardDao.update(toPieceDto(toSquare, chessGame.findPieceBySquare(toSquare)), id);
        boardDao.update(toPieceDto(fromSquare, chessGame.findPieceBySquare(fromSquare)), id);
        updateGame(chessGame, id);
    }

    private PieceWithSquareDto toPieceDto(Square square, Piece piece) {
        String squareName = square.getName();
        String pieceName = PieceType.getName(piece);
        return new PieceWithSquareDto(squareName, pieceName, piece.getColor().name());
    }

    public boolean isRunning(int id) {
        return getGameFromDao(id).isPlaying();
    }

    private ChessGame getGameFromDao(int id) {
        ChessGameDto game = gameDao.findById(id);
        BoardDto boardDto = getBoard(id);
        return new ChessGame(new Board(boardDto), Color.valueOf(game.getTurn()), Status.valueOf(game.getStatus()));
    }

    public boolean isGameEmpty(int id) {
        return getGameFromDao(id).isEmpty();
    }

    public void endGame(int id) {
        gameDao.updateStatus(new StatusDto(Status.EMPTY.name()), id);
        boardDao.remove(id);
    }

    public GameResultDto getResult(int id) {
        Color winner = getGameFromDao(id).findWinner();
        if (winner.equals(Color.NOTHING)) {
            return new GameResultDto(getScores(id), winner.name(), true);
        }
        return new GameResultDto(getScores(id), winner.name(), false);
    }

    private Map<String, Double> getScores(int id) {
        return getGameFromDao(id).getPlayersScore().entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Entry::getValue));
    }

    public GamesDto getAllGames() {
        return gameDao.findAll();
    }

    public void createGame(String name) {
        gameDao.createGame(name);
    }
}
