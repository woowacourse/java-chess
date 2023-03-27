package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.game.Game;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.dto.GameDto;
import chess.dto.PieceDto;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private final GameDao gameDao;
    private final PieceDao pieceDao;

    public ChessService(GameDao gameDao, PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public Game makeGame() {
        GameDto gameDto = gameDao.select();
        if (gameDto == null) {
            return null;
        }
        List<PieceDto> pieceDtos = pieceDao.select(gameDto.getGameId());
        Map<Square, Piece> squarePieceMap = generateBoard(pieceDtos);
        Board board = new Board(squarePieceMap);
        return new Game(board, Team.valueOf(gameDto.getTurn()));
    }

    private Map<Square, Piece> generateBoard(List<PieceDto> pieceDtos) {
        Map<Square, Piece> initBoard = initializeBoard();

        for (PieceDto pieceDto : pieceDtos) {
            Square square = new Square(File.valueOf(pieceDto.getFile()), Rank.valueOf(pieceDto.getRank()));
            initBoard.put(square, makePiece(pieceDto));
        }

        return initBoard;
    }

    private Map<Square, Piece> initializeBoard() {
        List<Square> squares = generateSquares();
        Map<Square, Piece> initBoard = new LinkedHashMap<>();

        for (Square square : squares) {
            initBoard.put(square, new Empty());
        }

        return initBoard;
    }

    private List<Square> generateSquares() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Square(file, rank)))
                .collect(Collectors.toList());
    }

    public Piece makePiece(PieceDto pieceDto) {
        PieceType pieceType = PieceType.valueOf(pieceDto.getType());
        Team team = Team.valueOf(pieceDto.getTeam());

        return pieceType.getInstance(team);
    }

    public void save(Game game) {
        gameDao.save(game);
        GameDto gameDto = gameDao.select();
        pieceDao.save(game, gameDto.getGameId());
    }

    public void delete() {
        pieceDao.delete();
        gameDao.delete();
    }
}
