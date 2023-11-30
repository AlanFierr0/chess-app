package chess.logic.classicGame;

import common.logic.LegalMove;
import common.logic.PieceMover;
import common.logic.WinCondition;
import common.models.Board;
import common.models.Coordinate;
import common.moves.Move;
import common.models.Piece;
import common.results.MoveResult;

import java.util.List;

public class ChessLegalMove implements LegalMove {
    private final PieceMover pieceMover = new PieceMover();
    @Override
    public MoveResult<Board, Boolean> movePiece(Piece piece, Coordinate toSquare, Board board, Coordinate initial, List<Move> moves, WinCondition winCondition) {
        MoveResult<Board, Boolean> move = pieceMover.check(board, initial, toSquare, moves,piece, board.getSquare(toSquare).getPiece());
        if (move.errorResult())
            return move;
        else {
            return winCondition.checkWin(board, piece, move, toSquare);
        }
    }
}