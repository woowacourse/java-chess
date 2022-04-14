package chess.service;

import chess.model.GameResult;
import chess.model.MoveType;
import chess.model.Turn;
import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.dao.PieceDao;
import chess.model.dao.TurnDao;
import chess.model.dto.MoveDto;
import chess.model.dto.WebBoardDto;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;
import chess.model.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private static final String NONE = "";

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessService() {
        this.pieceDao = new PieceDao();
        this.turnDao = new TurnDao();
    }

    public WebBoardDto start() {
        Board board = initBoard();
        initTurn();

        return WebBoardDto.from(board);
    }

    public WebBoardDto move(MoveDto moveDto) {
        Piece sourcePiece = PieceFactory.create(pieceDao.findByPosition(moveDto.getSource()));
        Piece targetPiece = PieceFactory.create(pieceDao.findByPosition(moveDto.getTarget()));
        Turn turn = Turn.from(turnDao.findOne());
        validateCurrentTurn(turn, sourcePiece);
        try {
            movePiece(moveDto, sourcePiece, targetPiece, turn);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        Board board = toBoard(pieceDao.findAll());
        if (board.isKingDead()) {
            turnDao.update(turn.finish());
        }

        return WebBoardDto.from(board);
    }

    private void movePiece(MoveDto moveDto, Piece sourcePiece, Piece targetPiece, Turn turn) {
        if (canMove(moveDto, sourcePiece, targetPiece)) {
            pieceDao.updateByPosition(moveDto.getTarget(), PieceDao.getPieceName(sourcePiece));
            pieceDao.updateByPosition(moveDto.getSource(), "none-.");
            turnDao.update(turn.change().getThisTurn());
            return;
        }
        throw new IllegalArgumentException("기물을 이동할 수 없습니다.");
    }

    public String getTurn() {
        return turnDao.findOne();
    }

    public boolean isKingDead() {
        Board board = toBoard(pieceDao.findAll());
        return board.isKingDead();
    }

    public GameResult getResult() {
        Board board = toBoard(pieceDao.findAll());
        return GameResult.from(board);
    }

    public void exitGame() {
        pieceDao.deleteAll();
        turnDao.deleteAll();
    }

    private Board initBoard() {
        Map<String, String> board = pieceDao.findAll();

        if (board.size() == 0) {
            pieceDao.init(BoardFactory.create());
        }

        return toBoard(pieceDao.findAll());
    }

    private Board toBoard(Map<String, String> rawBoard) {

        return new Board(rawBoard.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> Position.from(entry.getKey()),
                        entry -> PieceFactory.create(entry.getValue()))
                ));
    }

    private void initTurn() {
        String turn = turnDao.findOne();

        if (turn.equals(NONE)) {
            turnDao.init();
        }
    }

    private void validateCurrentTurn(Turn thisTurn, Piece sourcePiece) {
        if (!sourcePiece.isCurrentTurn(thisTurn)) {
            throw new IllegalArgumentException("본인의 말을 움직여야 합니다.");
        }
    }

    private boolean canMove(MoveDto moveDto, Piece sourcePiece, Piece targetPiece) {
        Position sourcePosition = Position.from(moveDto.getSource());
        Position targetPosition = Position.from(moveDto.getTarget());
        MoveType moveType = MoveType.of(sourcePiece, targetPiece);

        return sourcePiece.isMovable(sourcePosition, targetPosition, moveType)
                && !hasBlock(sourcePosition, targetPosition, sourcePiece);
    }

    private boolean hasBlock(Position source, Position target, Piece sourcePiece) {
        Board board = toBoard(pieceDao.findAll());
        List<Position> positions = sourcePiece.getIntervalPosition(source, target);
        return positions.stream()
                .anyMatch(position -> !board.get(position).equals(new Empty()));
    }
}
