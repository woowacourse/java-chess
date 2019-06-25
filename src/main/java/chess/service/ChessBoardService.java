package chess.service;

import chess.dao.BoardDao;
import chess.dao.ResultDao;
import chess.dao.TurnDao;
import chess.domain.*;
import chess.domain.utils.InputParser;
import chess.dto.BoardDto;
import chess.dto.ResultDto;
import chess.dto.TurnDto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.generator.PieceGenerator.generatePiece;

public class ChessBoardService {
    private BoardDao boardDao;
    private TurnDao turnDao;
    private ResultDao resultDao;

    public ChessBoardService() {
        this.boardDao = BoardDao.getInstance();
        this.turnDao = TurnDao.getInstance();
        this.resultDao = ResultDao.getInstance();
    }

    public ChessBoard gameStart() throws SQLException {
        List<BoardDto> boardDtos = boardDao.findAll();
        TurnDto turnDto = turnDao.find();
        List<ResultDto> resultDtos = resultDao.find();

        if (turnDto.getTeam() == null) {
            return init();
        }
        return load(boardDtos, turnDto, resultDtos);
    }

    private ChessBoard init() throws SQLException {
        ChessBoard chessBoard = new ChessBoard();
        boardDao.addAll(chessBoard.boardToDto());
        turnDao.add(chessBoard.turnToDto());

        return chessBoard;
    }

    private ChessBoard load(List<BoardDto> boardDtos,
                            TurnDto turnDto, List<ResultDto> resultDtos) {
        Turn turn = Turn.load(turnDto.getTeam());
        Board board = convertBoardBy(boardDtos);
        ResultCounter resultCounter = convertResultBy(resultDtos);

        return new ChessBoard(board, turn, resultCounter);
    }

    private Board convertBoardBy(List<BoardDto> boardDtos) {
        Map<Position, Piece> board = new HashMap<>();

        for (BoardDto boardDto : boardDtos) {
            board.put(
                    InputParser.position(boardDto.getPosition()),
                    generatePiece(boardDto.getPieceName(), boardDto.getTeam())
            );
        }

        return Board.load(board);
    }

    private ResultCounter convertResultBy(List<ResultDto> resultDtos) {
        Map<Piece, Count> counter = new HashMap<>();

        for (ResultDto resultDto : resultDtos) {
            counter.put(
                    generatePiece(resultDto.getName(), resultDto.getTeam()),
                    new Count(resultDto.getCount())
            );
        }

        return ResultCounter.load(counter);
    }

    public void move(List<BoardDto> boardDtos, TurnDto turnDto, ResultDto resultDto) throws SQLException {
        boardDao.deleteAll();
        boardDao.addAll(boardDtos);
        turnDao.update(turnDto);
        resultDao.update(resultDto);
    }

    public void gameEnd() throws SQLException {
        boardDao.deleteAll();
        turnDao.delete();
        resultDao.delete();
    }
}
