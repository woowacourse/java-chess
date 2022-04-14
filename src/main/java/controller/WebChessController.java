package controller;

import domain.PieceGenerator;
import domain.Player;
import domain.Status;
import domain.chessgame.ChessBoard;
import domain.chessgame.ChessGame;
import domain.dao.ChessGameDao;
import domain.dao.PieceDao;
import domain.dto.ChessGameDto;
import domain.dto.PieceDto;
import domain.piece.Piece;
import domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.ChessBoardGenerator;

public class WebChessController {

    private static final String SPLIT_REGEX = "";
    private ChessGame chessGame;
    private ChessGameDao chessGameDao;
    private PieceDao pieceDao;

    public WebChessController() {
        chessGameDao = new ChessGameDao();
        pieceDao = new PieceDao();
    }

    public void start() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessGame = new ChessGame(chessBoard);
    }
    public void start2(String gameName) {
        if(chessGameDao.findByName(gameName) != null){
            chessGame = new ChessGame(load2(gameName));
            return;
        }
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        chessGame = new ChessGame(chessBoard);
        save(gameName);
    }

    public Map<String, Object> modelBoard() {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> board = chessGame.getChessBoard().getBoard();
        for (Position position : board.keySet()) {
            model.put(position.getPosition(), board.get(position).symbolByPlayer());
        }
        model.put("player", chessGame.getCurrentPlayer());
        return model;
    }

    public Map<String, Object> status() {
        Map<String, Object> model = modelBoard();
        Status status = chessGame.status();
        model.put("winner", status.winner());
        model.put("whiteScore", status.getWhiteScore());
        model.put("blackScore", status.getBlackScore());
        return model;
    }

    public boolean move(String source, String target) {
        String[] sources = source.split(SPLIT_REGEX);
        String[] targets = target.split(SPLIT_REGEX);
        Position sourcePosition = Position.of(sources[0], sources[1]);
        Position targetPosition = Position.of(targets[0], targets[1]);
        chessGame.move(sourcePosition, targetPosition);
        return chessGame.isFinished();
    }

    public void save(String gameName) {
        String player = chessGame.getCurrentPlayer().name();
        ChessGameDto chessGameDto = new ChessGameDto(gameName, player);
        chessGameDao.save(chessGameDto);
        for (Position position : chessGame.getChessBoard().getBoard().keySet()) {
            String positionSymbol = position.getPosition();
            Piece piece = chessGame.getChessBoard().findPiece(position);
            String piecePlayer = piece.getPlayer().name();
            PieceDto pieceDto = new PieceDto(gameName, positionSymbol, piece.symbol(), piecePlayer);
            pieceDao.save(pieceDto);
        }
    }

    public void load(String gameName) {
        ChessGameDto chessGameDto = chessGameDao.findByName(gameName);
        List<PieceDto> pieceDtoList = pieceDao.findByGameName(gameName);
        Map<Position, Piece> board = createBoard(pieceDtoList);
        ChessBoard chessBoard = new ChessBoard(board);
        changeBoard(Player.valueOf(chessGameDto.getPlayer()), chessBoard);
    }

    public ChessBoard load2(String gameName) {
        ChessGameDto chessGameDto = chessGameDao.findByName(gameName);
        List<PieceDto> pieceDtoList = pieceDao.findByGameName(gameName);
        Map<Position, Piece> board = createBoard(pieceDtoList);
        return new ChessBoard(board);
    }

    public Map<Position, Piece> createBoard(List<PieceDto> pieceDtoList) {
        Map<Position, Piece> board = new HashMap<>();
        for (PieceDto pieceDto : pieceDtoList) {
            String[] position = pieceDto.getPosition().split(SPLIT_REGEX);
            String piecePlayer = pieceDto.getPlayer();
            Position piecePosition = Position.of(position[0], position[1]);
            Piece piece = PieceGenerator.of(pieceDto.getType())
                .generate(Player.valueOf(piecePlayer));
            board.put(piecePosition, piece);
        }
        return board;
    }

    public List<String> findAllGameName() {
        return chessGameDao.findAllName();
    }

    private void changeBoard(Player player, ChessBoard chessBoard) {
        chessGame = new ChessGame(chessBoard, player);
    }

    public ChessGameDto findByGameName(String gameName) {
        return chessGameDao.findByName(gameName);
    }

    public void delete(String gameName) {
        pieceDao.delete(gameName);
        chessGameDao.delete(gameName);
    }

    public Player currentPlayer() {
        return chessGame.getCurrentPlayer();
    }
}
