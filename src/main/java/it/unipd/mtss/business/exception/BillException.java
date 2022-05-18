////////////////////////////////////////////////////////////////////
// XIDA CHEN 1217780
// DANILO STOJKOVIC 1222399
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business.exception;

public class BillException extends Exception {
    public int id;
    public String message;

    public BillException(int id, String message){
        this.id = id;
        this.message = message;
    }
}
