package web.service;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.TWO;
import static java.util.stream.Collectors.toMap;

import chess.ChessBoard;
import chess.Score;
import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import web.controller.Movement;
import web.dao.ChessGameDao;
import web.dao.PieceDao;
import web.dao.ScoreDao;
import web.dto.GameStatus;
import web.dto.PieceDto;
import web.dto.PieceType;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final ScoreDao scoreDao;
    private final ChessGameDao chessGameDao;

    public ChessGameService(PieceDao pieceDao, ScoreDao scoreDao, ChessGameDao chessGameDao) {
        this.pieceDao = pieceDao;
        this.scoreDao = scoreDao;
        this.chessGameDao = chessGameDao;
    }

    public void move(Movement movement) {
        ChessBoard chessBoard = createChessBoard();
        chessBoard.move(movement.getFrom(), movement.getTo());
        updateChessGame(chessBoard, movement);
    }

    private ChessBoard createChessBoard() {
        List<PieceDto> pieces = pieceDao.findPieces();
        Color currentColor = chessGameDao.findCurrentColor();
        return new ChessBoard(createBoard(pieces), currentColor);
    }

    private Map<Position, Piece> createBoard(List<PieceDto> pieces) {
        return pieces.stream()
                .collect(toMap(PieceDto::getPosition, this::createPiece));
    }

    private Piece createPiece(PieceDto pieceDto) {
        if (pieceDto.getType() == PieceType.PAWN) {
            return new Pawn(pieceDto.getColor());
        }
        if (pieceDto.getType() == PieceType.KING) {
            return new King(pieceDto.getColor());
        }
        if (pieceDto.getType() == PieceType.QUEEN) {
            return new Queen(pieceDto.getColor());
        }
        if (pieceDto.getType() == PieceType.ROOK) {
            return new Rook(pieceDto.getColor());
        }
        if (pieceDto.getType() == PieceType.KNIGHT) {
            return new Knight(pieceDto.getColor());
        }
        if (pieceDto.getType() == PieceType.BISHOP) {
            return new Bishop(pieceDto.getColor());
        }
        throw new IllegalArgumentException("잘못된 piece type 입니다.");
    }

    private void updateChessGame(ChessBoard chessBoard, Movement movement) {
        updatePieces(chessBoard, movement);
        updateCurrentColor(chessBoard);
        updateScores(chessBoard);
        updateGameStatus(chessBoard);
    }

    private void updatePieces(ChessBoard chessBoard, Movement movement) {
        Map<Position, Piece> board = chessBoard.getBoard();
        pieceDao.deletePieceByPosition(movement.getFrom());
        pieceDao.deletePieceByPosition(movement.getTo());
        pieceDao.savePiece(new PieceDto(movement.getTo(), board.get(movement.getTo())));
    }

    private void updateCurrentColor(ChessBoard chessBoard) {
        chessGameDao.updateCurrentColor(chessBoard.getCurrentColor());
    }

    private void updateScores(ChessBoard chessBoard) {
        scoreDao.updateScoreByColor(chessBoard.getScore(Color.BLACK), Color.BLACK);
        scoreDao.updateScoreByColor(chessBoard.getScore(Color.WHITE), Color.WHITE);
    }

    private void updateGameStatus(ChessBoard chessBoard) {
        if (chessBoard.isFinished()) {
            chessGameDao.updateGameStatus(GameStatus.FINISHED);
            chessGameDao.updateWinner(chessBoard.getWinner());
        }
    }

    public void prepareNewChessGame() {
        deletePreviousChessGame();
        saveNewChessGame();
    }

    private void deletePreviousChessGame() {
        chessGameDao.deleteAll();
        pieceDao.deleteAll();
        scoreDao.deleteAll();
    }

    private void saveNewChessGame() {
        pieceDao.savePieces(createPieces());
        scoreDao.saveScoreByColor(new Score(new BigDecimal("38.0")), Color.BLACK);
        scoreDao.saveScoreByColor(new Score(new BigDecimal("38.0")), Color.WHITE);
        chessGameDao.saveGameStatus(GameStatus.RUNNING, Color.WHITE);
    }

    private List<PieceDto> createPieces() {
        return Stream.concat(createWhitePieces().stream(), createBlackPieces().stream())
                .collect(Collectors.toList());
    }

    private static List<PieceDto> createWhitePieces() {
        return List.of(
                new PieceDto(new Position(A, ONE), new Rook(Color.WHITE)),
                new PieceDto(new Position(B, ONE), new Knight(Color.WHITE)),
                new PieceDto(new Position(C, ONE), new Bishop(Color.WHITE)),
                new PieceDto(new Position(D, ONE), new Queen(Color.WHITE)),
                new PieceDto(new Position(E, ONE), new King(Color.WHITE)),
                new PieceDto(new Position(F, ONE), new Bishop(Color.WHITE)),
                new PieceDto(new Position(G, ONE), new Knight(Color.WHITE)),
                new PieceDto(new Position(H, ONE), new Rook(Color.WHITE)),
                new PieceDto(new Position(A, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(B, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(C, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(D, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(E, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(F, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(G, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(H, TWO), new Pawn(Color.WHITE)));
    }

    private static List<PieceDto> createBlackPieces() {
        return List.of(
                new PieceDto(new Position(A, EIGHT), new Rook(Color.BLACK)),
                new PieceDto(new Position(B, EIGHT), new Knight(Color.BLACK)),
                new PieceDto(new Position(C, EIGHT), new Bishop(Color.BLACK)),
                new PieceDto(new Position(D, EIGHT), new Queen(Color.BLACK)),
                new PieceDto(new Position(E, EIGHT), new King(Color.BLACK)),
                new PieceDto(new Position(F, EIGHT), new Bishop(Color.BLACK)),
                new PieceDto(new Position(G, EIGHT), new Knight(Color.BLACK)),
                new PieceDto(new Position(H, EIGHT), new Rook(Color.BLACK)),
                new PieceDto(new Position(A, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(B, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(C, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(D, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(E, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(F, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(G, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(H, SEVEN), new Pawn(Color.BLACK)));
    }
}
