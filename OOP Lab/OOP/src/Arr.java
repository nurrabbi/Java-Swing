
public class Arr {

    public static void main(String[] args) {
        birds[] ar1 = new birds[3];
        for (int i = 0; i < 3; i++) {
            ar1[i] = new birds();
        }
        for (int i = 0; i < 3; i++) {
            ar1[i].fly();
        }
    }

}
