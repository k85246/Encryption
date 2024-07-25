import java.util.Scanner;

public class Cipher {

     String[] encryption(String[] data){
        StringBuilder tempEncryptred = new StringBuilder();
        String[] encryptedString = new String[data.length];
        Scanner input = new Scanner(System.in);
        System.out.print("enter the shift Required: ");
        int shift = input.nextInt();
        int count = 0;
        for (String line : data) {
            for (char c : line.toCharArray()) {
                char encryptedChar;
                if(Character.isUpperCase(c)){
                    encryptedChar =  (char) ((((int) c) + shift - 65) % 26 +65);
                }
                else{
                    encryptedChar =  (char) ((((int) c) + shift - 97) % 26 +97);
                }
                tempEncryptred.append(encryptedChar);
            }
            encryptedString[count] = tempEncryptred.toString();
            count++;
            tempEncryptred.delete(0, tempEncryptred.length());
        }
        return encryptedString;
    }
}
