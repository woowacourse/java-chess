package chess.service;

import static chess.web.dto.PieceType.BISHOP_BLACK;
import static chess.web.dto.PieceType.BISHOP_WHITE;
import static chess.web.dto.PieceType.KING_BLACK;
import static chess.web.dto.PieceType.KING_WHITE;
import static chess.web.dto.PieceType.KNIGHT_BLACK;
import static chess.web.dto.PieceType.KNIGHT_WHITE;
import static chess.web.dto.PieceType.PAWN_BLACK;
import static chess.web.dto.PieceType.PAWN_WHITE;
import static chess.web.dto.PieceType.QUEEN_BLACK;
import static chess.web.dto.PieceType.QUEEN_WHITE;
import static chess.web.dto.PieceType.ROOK_BLACK;
import static chess.web.dto.PieceType.ROOK_WHITE;
import static chess.web.dto.PieceType.parsePiece;

import chess.dao.PieceDao;
import chess.dao.TurnDao;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.generator.EmptyBoardGenerator;
import chess.domain.state.State;
import chess.domain.state.StateType;
import chess.web.dto.PieceDto;
import chess.web.dto.TurnDto;
import java.util.List;

public class ChessService {

    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessService(PieceDao pieceDao, TurnDao turnDao) {
        this.pieceDao = pieceDao;
        this.turnDao = turnDao;
    }

    public void create() {
        pieceDao.removeAll();
        List<PieceDto> initPieces = initPieces();
        for (PieceDto pieceDto : initPieces) {
            pieceDao.save(pieceDto);
        }

        turnDao.save(new TurnDto("white turn"));
    }

    public ChessGame getChessGame() {
        ChessBoard chessBoard = generateChessBoard();
        TurnDto lastTurn = turnDao.findLastTurn();

        State state = generateState(lastTurn, chessBoard);

        return new ChessGame(state);
    }

    public void move(String source, String target) {
        State state = generateState(turnDao.findLastTurn(), generateChessBoard());

        ChessGame chessGame = new ChessGame(state);
        chessGame.move(source, target);

        shift(source, target);

        StateType stateType = chessGame.getStateType();
        turnDao.save(new TurnDto(stateType.getValue()));
    }

    private void shift(String source, String target) {
        if (!pieceDao.findById(target).isEmpty()) {
            pieceDao.remove(target);
        }

        pieceDao.save(new PieceDto(target, findPiece(source).getPieceType()));
        pieceDao.remove(source);
    }

    private PieceDto findPiece(String source) {
        return pieceDao.findById(source)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다."));
    }

    private State generateState(TurnDto lastTurn, ChessBoard chessBoard) {
        StateType stateType = StateType.of(lastTurn.getTurn());
        return stateType.parseState(chessBoard);
    }

    private ChessBoard generateChessBoard() {
        List<PieceDto> pieces = pieceDao.findAll();
        ChessBoard chessBoard = new ChessBoard(new EmptyBoardGenerator());

        for (PieceDto pieceDto : pieces) {
            chessBoard.fill(pieceDto.getId(), parsePiece(pieceDto.getPieceType()));
        }

        return chessBoard;
    }

    private List<PieceDto> initPieces() {
        return List.of(
                new PieceDto("a1", ROOK_WHITE),
                new PieceDto("b1", KNIGHT_WHITE),
                new PieceDto("c1", BISHOP_WHITE),
                new PieceDto("d1", QUEEN_WHITE),
                new PieceDto("e1", KING_WHITE),
                new PieceDto("f1", BISHOP_WHITE),
                new PieceDto("g1", KNIGHT_WHITE),
                new PieceDto("h1", ROOK_WHITE),
                new PieceDto("a2", PAWN_WHITE),
                new PieceDto("b2", PAWN_WHITE),
                new PieceDto("c2", PAWN_WHITE),
                new PieceDto("d2", PAWN_WHITE),
                new PieceDto("e2", PAWN_WHITE),
                new PieceDto("f2", PAWN_WHITE),
                new PieceDto("g2", PAWN_WHITE),
                new PieceDto("h2", PAWN_WHITE),
                new PieceDto("a8", ROOK_BLACK),
                new PieceDto("b8", KNIGHT_BLACK),
                new PieceDto("c8", BISHOP_BLACK),
                new PieceDto("d8", QUEEN_BLACK),
                new PieceDto("e8", KING_BLACK),
                new PieceDto("f8", BISHOP_BLACK),
                new PieceDto("g8", KNIGHT_BLACK),
                new PieceDto("h8", ROOK_BLACK),
                new PieceDto("a7", PAWN_BLACK),
                new PieceDto("b7", PAWN_BLACK),
                new PieceDto("c7", PAWN_BLACK),
                new PieceDto("d7", PAWN_BLACK),
                new PieceDto("e7", PAWN_BLACK),
                new PieceDto("f7", PAWN_BLACK),
                new PieceDto("g7", PAWN_BLACK),
                new PieceDto("h7", PAWN_BLACK)
        );
    }
}
