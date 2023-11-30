package checkers.logic;

import common.logic.PossibleMovements;
import common.models.Board;
import common.models.Piece;


public class CheckForWinByNoMoreMoves {
    PossibleMovements possibleMovements = new PossibleMovements();
    public Boolean check(Board board){
        for (Piece p : board.getPieces()) {
            if (!possibleMovements.getPossibleMovements(board, p, board.getSquareOfPiece(p).successfulResult().get()).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
