package chucknorris;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static void encode(String inputToEncode) {
        int numOfSame = 1;
        String allBinary = "";
        for (int i = 0; i < inputToEncode.length(); i++) {
            char character = inputToEncode.charAt(i);
            int intOfChar = character;
            String binaryOfChar = Integer.toBinaryString(intOfChar);
            if (binaryOfChar.length() < 7) {
                binaryOfChar = "0" + binaryOfChar;
            }
            allBinary = allBinary + binaryOfChar;
        }
        System.out.println("Encoded string:");

        for (int j = 0; j < allBinary.length(); j++) {
            if (j != allBinary.length() - 1) {
                if (allBinary.charAt(j) == allBinary.charAt(j + 1)) {
                    numOfSame += 1;
                } else {
                    if (allBinary.charAt(j) == '1') {
                        System.out.print("0 ");
                    } else {
                        System.out.print("00 ");
                    }
                    for (int n = 0; n < numOfSame; n++) {
                        System.out.print("0");
                    }
                    System.out.print(' ');
                    numOfSame = 1;
                }
            } else {
                if (allBinary.charAt(j) == '1') {
                    System.out.print("0 ");
                } else {
                    System.out.print("00 ");
                }
                for (int n = 0; n < numOfSame; n++) {
                    System.out.print("0");
                }
                System.out.print(' ');
                numOfSame = 1;
            }
        }
        System.out.println("\n");
    }

    private static void checkNdecode(String inputToDecode) {
        int error = 0;
        int countOfBin = 0;

        String encoded[] = inputToDecode.split(" ");
        //check if odd
        if (encoded.length % 2 != 0) {
            error++;
        }
        //check if only zeros and spaces
        for (int i = 0; i < inputToDecode.length(); i++) {
            if (inputToDecode.charAt(i) == '0' || inputToDecode.charAt(i) == ' ') {
                //nothing
            } else {
                error++;
            }
        }
        //check if first block of each sequence is 0 or 00
        for (int j = 0; j < encoded.length; j += 2) {
            if (encoded[j].equals("0") || encoded[j].equals("00")) {
                //nothing
            } else {
                error++;
            }
        }
        //check if there are 7 binary nums in each sequence
        for (int n = 1; n < encoded.length; n += 2) {
            countOfBin += encoded[n].length();
        }

        if (countOfBin % 7 != 0) {
            error++;
        }
        //decode or print error
        if (error == 0) {
            realDecode(encoded);
        } else {
            System.out.println("Encoded string is not valid.\n");
        }
    }

    private static void realDecode(String[] encoded) {
        //convert to binary
        String fullBinary = "";
        for (int i = 0; i < encoded.length; i += 2) {
            int lengthOfBin = encoded[i+1].length();
            String addedStr;
            if (encoded[i].equals("0")) {
                addedStr = "1";
            } else {
                addedStr = "0";
            }
            for (int j = 0; j < lengthOfBin; j++) {
                fullBinary += addedStr;
            }
        }

        //convert binary to char
        String decodedString = "";
        for (int i = 0; i < fullBinary.length() / 7; i++) {
            int decodedInt = Integer.parseInt(fullBinary.substring(i*7,(i+1)*7), 2);
            decodedString += (char)(decodedInt);
        }
        System.out.println("Decoded string:");
        System.out.println(decodedString + "\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        while (exit) {
            System.out.println("Please input operation (encode/decode/exit):");
            String input = sc.nextLine();
            if (input.equals("encode")) {
                System.out.println("Input string:");
                String inputToEncode = sc.nextLine();
                encode(inputToEncode);
            } else if (input.equals("decode")) {
                System.out.println("Input encoded string:");
                String inputToDecode = sc.nextLine();
                checkNdecode(inputToDecode);
            } else if (input.equals("exit")) {
                System.out.println("Bye!");
                exit = false;
            } else {
                System.out.println("There is no '" + input + "' operation\n");
            }
        }
    }
}