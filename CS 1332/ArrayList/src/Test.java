public class Test {
    public static void main(String args[]) {
        String[] intArr = new String[6];
        String[] intArr2 = new String[10];

        intArr[0] = "hi";
        intArr[1] = "cute";
        intArr[2] = "ur";
        intArr[5] = "bad";
        intArr[4] = "help";

        int i = 1;

        intArr2[0] = intArr[4];

        for (String word: intArr) {
            intArr2[i] = word;
            i = i + 1;
        }

        for (int j = 0; j < intArr2.length; j = j + 1) {
            System.out.println(intArr2[j]);
        }
        System.out.println(intArr.length);

    }
}
