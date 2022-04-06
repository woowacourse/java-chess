package chess.service;

import static java.util.stream.Collectors.toMap;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.model.ChessGame;
import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.model.board.Square;
import chess.model.piece.Piece;
import chess.model.piece.PieceLetter;
import chess.dao.RuntimeChessGameDao;
import chess.model.status.Status;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

//todo
public class ChessService {
    private final RuntimeChessGameDao dao;
    private BoardDao boardDao = new BoardDao();
    private GameDao gameDao = new GameDao();
    private int gameId = getGameIdFromDao();

    public ChessService(RuntimeChessGameDao dao) {
        this.dao = dao;
    }

    public void initGame() {
        ChessGame chessGame = new ChessGame(new ChessInitializer(), Status.PLAYING);
        Map<Square, Piece> pieces = chessGame.getBoard().getPieces();
        boardDao.initBoard(toBoardDto(pieces), gameId);
        updateGame(chessGame);
    }

    private void updateGame(ChessGame chessGame) {
        gameDao.update(new ChessGameDto(chessGame.getStatus().name(), chessGame.getTurn().name()), gameId);
    }

    private ChessGame getGameFromDao() {
        ChessGameDto game = gameDao.findByName("game");
        BoardDto boardDto = boardDao.getBoardByGameId(game.getId());
        return new ChessGame(new Board(boardDto), Color.valueOf(game.getTurn()), Status.valueOf(game.getStatus()));
    }

    private int getGameIdFromDao() {
        return gameDao.findByName("game").getId();
    }

    public BoardDto getBoard() {
        return boardDao.getBoardByGameId(gameId);
    }

    private BoardDto toBoardDto(Map<Square, Piece> board) {
        return new BoardDto(board.keySet().stream()
                .collect(toMap(Square::getName, key -> toDto(board.get(key)))));
    }

    private PieceDto toDto(Piece piece) {
        String pieceName = PieceLetter.getName(piece);
        return new PieceDto(pieceName, piece.getColor().name());
    }

    public List<List<String>> getAllPieceLetter() {
        return Rank.getRanksInBoardOrder().stream()
                .map(rank -> getPieceLetterInRank(getGameFromDao().getBoard(), rank))
                .collect(Collectors.toList());
    }

    private List<String> getPieceLetterInRank(Board board, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> board.findPieceBySquare(Square.of(file, rank)))
                .map(PieceLetter::getLetterByColor)
                .collect(Collectors.toList());
    }

    public void move(String from, String to) {
        ChessGame chessGame = getGameFromDao();
        chessGame.move(Square.of(from), Square.of(to));
        boardDao.move(from, to, gameId);
        updateGame(chessGame);
    }

    public boolean isWaitingOrRunning() {
        return getGameFromDao().isRunning();
    }

    public void endGame() {
        gameDao.updateStatus(new StatusDto(Status.END.name()), gameId);
    }

    public GameResultDto getResult() {
        Color winner = getGameFromDao().findWinner();
        if (winner.equals(Color.NOTHING)) {
            return new GameResultDto(getScores(), winner.name(), true);
        }
        return new GameResultDto(getScores(), winner.name(), false);
    }

    private Map<String, Double> getScores() {
        return getGameFromDao().getPlayersScore().entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Entry::getValue));
    }
}
