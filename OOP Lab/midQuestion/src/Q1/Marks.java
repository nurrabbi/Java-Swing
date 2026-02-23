package Q1;


public class Marks {

    String id;
    double quizMark, midMark, finalMark;

    public Marks(String id, double quiz, double mid, double Final) {
        this.id = id;
        this.quizMark = quiz;
        this.midMark = mid;
        this.finalMark = Final;
    }

    public void passedOrNot() {
        double total = quizMark + midMark + finalMark;
        if (total >= 55) {
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }
    }
}
