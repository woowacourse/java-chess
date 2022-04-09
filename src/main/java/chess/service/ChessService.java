package chess.service;

import static java.util.stream.Collectors.toMap;

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
import chess.model.piece.PieceLetter;
import chess.service.dto.BoardDto;
import chess.service.dto.ChessGameDto;
import chess.service.dto.GameResultDto;
import chess.service.dto.PieceWithSquareDto;
import chess.service.dto.StatusDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessService {
    private BoardDao boardDao;
    private GameDao gameDao;

    public ChessService(BoardDao boardDao, GameDao gameDao) {
        this.boardDao = boardDao;
        this.gameDao = gameDao;
    }

    public void initGame(String gameName) {
        ChessGame chessGame = new ChessGame(new ChessInitializer(), Status.PLAYING);
        Map<Square, Piece> pieces = chessGame.getBoard().getPieces();
        boardDao.initBoard(toBoardDto(pieces), gameName);
        updateGame(chessGame, gameName);
    }

    private void updateGame(ChessGame chessGame, String gameName) {
        gameDao.update(new ChessGameDto(gameName, chessGame.getStatus().name(), chessGame.getTurn().name()));
    }

    private ChessGame getGameFromDao(String gameName) {
        ChessGameDto game = gameDao.findByName(gameName);
        BoardDto boardDto = getBoard(gameName);
        return new ChessGame(new Board(boardDto), Color.valueOf(game.getTurn()), Status.valueOf(game.getStatus()));
    }

    public BoardDto getBoard(String gameName) {
        return boardDao.getBoardByGameId(gameName);
    }

    private BoardDto toBoardDto(Map<Square, Piece> board) {
        return new BoardDto(board.keySet().stream()
                .map(square -> toPieceDto(square, board.get(square)))
                .collect(Collectors.toList()));
    }

    private PieceWithSquareDto toPieceDto(Square square, Piece piece) {
        String squareName = square.getName();
        String pieceName = PieceLetter.getName(piece);
        return new PieceWithSquareDto(squareName, pieceName, piece.getColor().name());
    }

    public List<List<String>> getAllPieceLetter(String gameName) {
        return Rank.getRanksInBoardOrder().stream()
                .map(rank -> getPieceLetterInRank(getGameFromDao(gameName).getBoard(), rank))
                .collect(Collectors.toList());
    }

    private List<String> getPieceLetterInRank(Board board, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> board.findPieceBySquare(Square.of(file, rank)))
                .map(PieceLetter::getLetterByColor)
                .collect(Collectors.toList());
    }

    //TODO
    public void move(String gameName, String from, String to) {
        ChessGame chessGame = getGameFromDao(gameName);
        chessGame.move(Square.of(from), Square.of(to));
        boardDao.move(from, to, gameName);
        updateGame(chessGame, gameName);
    }

    public boolean isRunning(String gameName) {
        return getGameFromDao(gameName).isPlaying();
    }

    public boolean isGameEmpty(String gameName) {
        return getGameFromDao(gameName).isEmpty();
    }

    public void endGame(String gameName) {
        gameDao.updateStatus(new StatusDto(Status.EMPTY.name()), gameName);
        boardDao.remove(gameName);
    }

    public GameResultDto getResult(String gameName) {
        Color winner = getGameFromDao(gameName).findWinner();
        if (winner.equals(Color.NOTHING)) {
            return new GameResultDto(getScores(gameName), winner.name(), true);
        }
        return new GameResultDto(getScores(gameName), winner.name(), false);
    }

    private Map<String, Double> getScores(String gameName) {
        return getGameFromDao(gameName).getPlayersScore().entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Entry::getValue));
    }
}
