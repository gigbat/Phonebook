import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phonebook {
    static String[][] phone = new String[1][2];
    static boolean firstInput = true;
    static final String SOUT_INPUT = "1.Input full name\n2.Input phone";
    static final String WANT_TO_CONTACT = "Do you want to add a new contact?";

    public static void main(String[] args) throws IOException {
        boolean add = true;
        check(add);
    }

    public static void check(boolean add) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(WANT_TO_CONTACT);
        System.out.println("1 - YES");
        System.out.println("2 - NO/EXIT");

        String str = bf.readLine();

        do {
            String operation = str;
            if (operation.equals("1")) {
                add();
                System.out.println(WANT_TO_CONTACT + " yet?");
                System.out.println("Y - yes, N - no, S - stop");
                String stop = bf.readLine();
                while (!stop.equals("S")) {
                    if (stop.equals("Y")) {
                        add();
                        System.out.println(WANT_TO_CONTACT + " yet?");
                        System.out.println("Y - yes, N - no, S - stop");
                        stop = bf.readLine();
                        if (stop.equals("N")) {
                            break;
                        }
                    }
                }
                break;
            } else {
                System.out.println("Not correct input");
            }
        } while (add);
    }

    public static void add() throws IOException {

        if (firstInput) {
            fillingFirstInput();
            firstInput = false;
        } else {
            phone = newBook(phone[phone.length - 1].length);
            fillingSecondInput();
        }

        for (int i = 0; i < phone.length; i++) {
            for (int j = 0; j < phone[i].length; j++) {
                if (j == 0) {
                    System.out.print(phone[i][j]);
                } else {
                    System.out.println(": " + phone[i][j]);
                }
            }
        }

        System.out.println();
    }

    public static void fillingFirstInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < phone.length; i++) {
            System.out.println(SOUT_INPUT);
            for (int j = 0; j < phone[i].length; j++) {
                String str = bf.readLine();

                if (j == 1 && !validOfNumber(str)) {
                    System.out.println("Not correct number");
                    phone[i][j] = "-";
                } else {
                    phone[i][j] = str;
                }
            }
        }

        System.out.println("Do you want to check your records?\n" +
                "1 - YES, 2 - NO");
        String check = bf.readLine();
        if (check.equals("1")) {
            System.out.println("Input phone or name, how you recorded it");
            String checkInput = bf.readLine();
            nameOrPhone(checkInput);
        }
    }

    public static void fillingSecondInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = phone.length - 1; i < phone.length; i++) {
            System.out.println(SOUT_INPUT);
            for (int j = 0; j < phone[i].length; j++) {
                String str = bf.readLine();

                if (j == 1 && !validOfNumber(str)) {
                    System.out.println("Not correct number");
                    phone[i][j] = "-";
                } else if (j == 0) {
                    nameOfValid(str);
                    System.out.println("Input phone");
                } else {
                    phone[i][j] = str;
                }
            }

            System.out.println("Do you want to check your records?\n" +
                    "1 - YES, 2 - NO");
            String check = bf.readLine();
            if (check.equals("1")) {
                System.out.println("Input phone or name, how you recorded it");
                String checkInput = bf.readLine();
                nameOrPhone(checkInput);
            }
        }
    }

    public static boolean validOfNumber(String string) {
        Pattern pattern = Pattern.compile("^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$");
        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }

    public static void nameOfValid(String str) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < phone.length - 1; i++) {
            for (int j = 0; j < phone[i].length; j++) {
                if (phone[i][0].equals(str)) {
                    System.out.println("Input a new name to your phonebook");
                    str = bf.readLine();
                    phone[i + 1][0] = str;
                } else {
                    phone[i + 1][0] = str;
                }
            }
        }
    }


    public static void nameOrPhone(String name) throws IOException {
        boolean checked = false;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < phone.length - 1; i++) {
            for (int j = 0; j < phone[i].length; j++) {
                if (phone[i][1].equals(name)) {
                    System.out.println("The searched phone - " + phone[i][0] + ": " + phone[i][1]);
                    checked = true;
                    break;
                }
            }
            if (checked) {
                System.out.println("Input name of phonebook");
                name = bf.readLine();
                for (int j = 0; j < phone[i].length; j++) {
                    if (phone[i][0].equals(name)) {
                        System.out.println("The searched name - " + phone[i][0] + ": " + phone[i][1]);
                    }
                }
            }
        }
    }

    public static String[][] newBook(int length) {
        String[][] newPhone = new String[phone.length + 1][length];

        for (int i = 0; i < phone.length; i++) {
            for (int j = 0; j < phone[i].length; j++) {
                newPhone[i][j] = phone[i][j];
            }
        }
        return newPhone;
    }
}