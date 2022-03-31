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
import web.dto.PieceDTO;
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
        List<PieceDTO> pieces = pieceDao.findPieces();
        Color currentColor = chessGameDao.findCurrentColor();
        return new ChessBoard(createBoard(pieces), currentColor);
    }

    private Map<Position, Piece> createBoard(List<PieceDTO> pieces) {
        return pieces.stream()
                .collect(toMap(PieceDTO::getPosition, this::createPiece));
    }

    private Piece createPiece(PieceDTO pieceDTO) {
        if (pieceDTO.getType() == PieceType.PAWN) {
            return new Pawn(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.KING) {
            return new King(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.QUEEN) {
            return new Queen(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.ROOK) {
            return new Rook(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.KNIGHT) {
            return new Knight(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.BISHOP) {
            return new Bishop(pieceDTO.getColor());
        }
        throw new IllegalArgumentException("잘못된 piece type 입니다.");
    }

    private void updateChessGame(ChessBoard chessBoard, Movement movement) {
        updatePieces(chessBoard, movement);
        updateCurrentColor(chessBoard);
        updateScores(chessBoard);

        if (chessBoard.isFinished()) {
            updateGameStatus(chessBoard);
        }
    }

    private void updatePieces(ChessBoard chessBoard, Movement movement) {
        Map<Position, Piece> board = chessBoard.getBoard();
        pieceDao.deletePieceByPosition(movement.getFrom());
        pieceDao.deletePieceByPosition(movement.getTo());
        pieceDao.savePiece(new PieceDTO(movement.getTo(), board.get(movement.getTo())));
    }

    private void updateCurrentColor(ChessBoard chessBoard) {
        chessGameDao.updateCurrentColor(chessBoard.getCurrentColor());
    }

    private void updateScores(ChessBoard chessBoard) {
        scoreDao.updateScoreByColor(chessBoard.getScore(Color.BLACK), Color.BLACK);
        scoreDao.updateScoreByColor(chessBoard.getScore(Color.WHITE), Color.WHITE);
    }

    private void updateGameStatus(ChessBoard chessBoard) {
        chessGameDao.updateGameStatus(GameStatus.FINISHED);
        chessGameDao.updateWinner(chessBoard.getWinner());
    }

    public List<PieceDTO> queryPieces() {
        return pieceDao.findPieces();
    }

    public Score queryScoreByColor(Color color) {
        return scoreDao.findScoreByColor(color);
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

    private List<PieceDTO> createPieces() {
        return Stream.concat(createWhitePieces().stream(), createBlackPieces().stream())
                .collect(Collectors.toList());
    }

    private static List<PieceDTO> createWhitePieces() {
        return List.of(
                new PieceDTO(new Position(A, ONE), new Rook(Color.WHITE)),
                new PieceDTO(new Position(B, ONE), new Knight(Color.WHITE)),
                new PieceDTO(new Position(C, ONE), new Bishop(Color.WHITE)),
                new PieceDTO(new Position(D, ONE), new Queen(Color.WHITE)),
                new PieceDTO(new Position(E, ONE), new King(Color.WHITE)),
                new PieceDTO(new Position(F, ONE), new Bishop(Color.WHITE)),
                new PieceDTO(new Position(G, ONE), new Knight(Color.WHITE)),
                new PieceDTO(new Position(H, ONE), new Rook(Color.WHITE)),
                new PieceDTO(new Position(A, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(B, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(C, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(D, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(E, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(F, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(G, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(H, TWO), new Pawn(Color.WHITE)));
    }

    private static List<PieceDTO> createBlackPieces() {
        return List.of(
                new PieceDTO(new Position(A, EIGHT), new Rook(Color.BLACK)),
                new PieceDTO(new Position(B, EIGHT), new Knight(Color.BLACK)),
                new PieceDTO(new Position(C, EIGHT), new Bishop(Color.BLACK)),
                new PieceDTO(new Position(D, EIGHT), new Queen(Color.BLACK)),
                new PieceDTO(new Position(E, EIGHT), new King(Color.BLACK)),
                new PieceDTO(new Position(F, EIGHT), new Bishop(Color.BLACK)),
                new PieceDTO(new Position(G, EIGHT), new Knight(Color.BLACK)),
                new PieceDTO(new Position(H, EIGHT), new Rook(Color.BLACK)),
                new PieceDTO(new Position(A, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(B, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(C, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(D, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(E, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(F, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(G, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(H, SEVEN), new Pawn(Color.BLACK)));
    }

    public boolean existChessGame() {
        return chessGameDao.existChessGame();
    }

    public boolean isGameFinished() {
        return chessGameDao.findGameStatus() == GameStatus.FINISHED;
    }

    public Color findWinner() {
        return chessGameDao.findWinner();
    }

    public Color queryCurrentColor() {
        return chessGameDao.findCurrentColor();
    }
}
