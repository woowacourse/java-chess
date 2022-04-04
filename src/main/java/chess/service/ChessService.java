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
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.generator.EmptyBoardGenerator;
import chess.domain.state.State;
import chess.domain.state.StateGenerator;
import chess.web.dto.PieceDto;
import java.util.List;

public class ChessService {

    private final PieceDao pieceDao;

    public ChessService(PieceDao pieceDao) {
        this.pieceDao = pieceDao;
    }

    public void create() {
        List<PieceDto> initPieces = initPieces();
        for (PieceDto pieceDto : initPieces) {
            pieceDao.save(pieceDto);
        }

        // TODO: state 저장 예정
    }

    public ChessGame getChessGame() {
        List<PieceDto> pieces = pieceDao.findAll();
        ChessBoard chessBoard = new ChessBoard(new EmptyBoardGenerator());

        for (PieceDto pieceDto : pieces) {
            chessBoard.fill(pieceDto.getId(), parsePiece(pieceDto.getPieceType()));
        }

        // TODO: DB 조회로 변경 예정
        State state = StateGenerator.WHITE_TURN.parseState(chessBoard);

        return new ChessGame(state);
    }

    public void move(ChessGame chessGame, String source, String target) {
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
