package hackerrank;

/**
 * Created by Muneer on 23-12-2016.
 */
public class State {
    int[] ar;
    boolean[] isPassed;

    public State(int[] ar, boolean[] isPassed) {
        this.ar = ar;
        this.isPassed = isPassed;
    }

    public int[] getAr() {
        return ar;
    }

    public void setAr(int[] ar) {
        this.ar = ar;
    }

    public boolean[] getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(boolean[] isPassed) {
        this.isPassed = isPassed;
    }

    @Override
    public boolean equals(Object obj) {
        State ob = (State) obj;
        boolean allZeros = true;
        boolean allEqual = true;
        for(int i = 0; i < ar.length; i++) {
            if(this.ar[i] == 0 && ob.ar[i] == 0) {

            } else if(this.ar[i] == ob.ar[i] && this.isPassed[i] == ob.isPassed[i]) {
                allZeros = false;
            } else {
                allZeros = false;
                allEqual = false;
                break;
            }
        }

        if(allZeros || allEqual)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(int i = 0; i < ar.length; i++)
            hash = hash * (ar[i]+1) * getNumVal(isPassed[i]);
        return hash;
    }

    private int getNumVal(boolean b) {
        if(b)
            return 2;
        return 1;
    }
}
