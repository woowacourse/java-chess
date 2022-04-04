package chess.service;

import chess.domain.ChessBoard;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.dto.BoardDto;

public class ChessService {

    public BoardDto start() {
        // 게임 시작, TODO DB에서 가져옴
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        return new BoardDto(chessBoard.getPieces());
    }
}
