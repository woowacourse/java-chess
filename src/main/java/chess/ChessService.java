package chess;

import chess.dao.BoardDao;
import chess.dao.PieceDao;
import chess.dao.SquareDao;
import chess.dto.BoardDto;
import chess.dto.BoardsDto;
import chess.dto.RoomDto;
import chess.dto.ScoreDto;
import chess.model.Board;
import chess.model.piece.Color;
import chess.model.piece.Empty;
import chess.model.piece.Initializer;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private static final int PROPER_KING_COUNT = 2;

    private final BoardDao<Board> chessBoardDao;
    private final SquareDao<Square> chessSquareDao;
    private final PieceDao<Piece> chessPieceDao;

    public ChessService(BoardDao<Board> chessBoardDao, SquareDao<Square> chessSquareDao, PieceDao<Piece> chessPieceDao) {
        this.chessBoardDao = chessBoardDao;
        this.chessSquareDao = chessSquareDao;
        this.chessPieceDao = chessPieceDao;
    }

    public Board init(Board board) {
        return chessBoardDao.init(board, Initializer.initialize());
    }

    public BoardsDto getBoards() {
        List<RoomDto> boardsDto = new ArrayList<>();
        List<Board> boards = chessBoardDao.findAll();
        for (Board board : boards) {
            boardsDto.add(
                    new RoomDto(board.getId(), board.getTitle(), board.getMembers().get(0), board.getMembers().get(1)));
        }
        return new BoardsDto(boardsDto);
    }

    public BoardDto getBoard(int roomId) {
        final Board board = chessBoardDao.getById(roomId);
        final Map<Square, Piece> allPositionsAndPieces = chessSquareDao.findAllSquaresAndPieces(roomId);
        return BoardDto.of(allPositionsAndPieces, board.getTitle(), board.getMembers().get(0),
                board.getMembers().get(1));
    }

    public void move(String source, String target, int boardId) {
        Square sourceSquare = chessSquareDao.getBySquareAndBoardId(Square.fromString(source), boardId);
        Square targetSquare = chessSquareDao.getBySquareAndBoardId(Square.fromString(target), boardId);
        Piece piece = chessPieceDao.findBySquareId(sourceSquare.getId());
        checkMovable(sourceSquare, targetSquare, piece, boardId);
        chessPieceDao.deletePieceBySquareId(targetSquare.getId());
        chessPieceDao.updatePieceSquareId(sourceSquare.getId(), targetSquare.getId());
        chessPieceDao.save(new Empty(), sourceSquare.getId());
    }

    private void checkMovable(Square sourceSquare, Square targetSquare, Piece piece, int boardId) {
        Piece targetPiece = chessPieceDao.findBySquareId(targetSquare.getId());
        if (!piece.movable(targetPiece, sourceSquare, targetSquare)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
        List<Square> route = piece.getRoute(sourceSquare, targetSquare);

        if (piece.isPawn() && !route.isEmpty() && !piece.isNotAlly(targetPiece)) {
            throw new IllegalArgumentException("같은 팀이 있는 곳으로 갈 수 없습니다.");
        }

        checkMoveWithoutObstacle(route, boardId, piece, targetPiece);
    }

    private void checkMoveWithoutObstacle(List<Square> route, int boardId, Piece sourcePiece, Piece targetPiece) {
        List<Square> realSquares = chessSquareDao.getPaths(route, boardId);
        for (Square square : realSquares) {
            Piece piece = chessPieceDao.findBySquareId(square.getId());
            if (piece.equals(targetPiece) && sourcePiece.isNotAlly(targetPiece)) {
                return;
            }
            if (piece.isNotEmpty()) {
                throw new IllegalArgumentException("경로 중 기물이 있습니다.");
            }
        }
    }

    public boolean isEnd(int boardId) {
        long kingCount = chessPieceDao.getAllPiecesByBoardId(boardId).stream()
                .filter(Piece::isKing)
                .count();

        if(kingCount != PROPER_KING_COUNT) {
            chessBoardDao.deleteById(boardId);
        }
        return kingCount != PROPER_KING_COUNT;
    }

    public ScoreDto status(int roomId) {
        Map<String, Double> statusDto = Color.getPlayerColors().stream()
                .collect(Collectors.toMap(Enum::name, color -> calculateScore(roomId, color)));
        return ScoreDto.of(statusDto);
    }

    public double calculateScore(int roomId, final Color color) {
        return calculateDefaultScore(roomId, color) - 0.5 * countPawnsOnSameColumns(roomId, color);
    }

    private double calculateDefaultScore(int roomId, Color color) {
        return existPieces(roomId)
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    public List<Piece> existPieces(int roomId) {
        return chessPieceDao.getAllPiecesByBoardId(roomId);
    }

    private int countPawnsOnSameColumns(int roomId, final Color color) {
        return Arrays.stream(File.values())
                .mapToInt(file -> chessPieceDao.countPawnsOnSameColumn(roomId, file, color))
                .filter(count -> count > 1)
                .sum();
    }

    public void end(int roomId) {
        chessBoardDao.deleteById(roomId);
    }
}
