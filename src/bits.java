public class bits {

    String[] StringBits(String[] data){
        String[] stringBits = new String[data.length]; 
        StringBuilder digit = new StringBuilder();
        int count = 0;
        for (String string : data) {
            for (char c : string.toCharArray()) {
                String StringBinary = String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ","0");
                digit.append(StringBinary).append(" ");
            }
            stringBits[count] = digit.toString();
            count++;
            digit.delete(0, digit.length());
        }
        return stringBits;
    }
    
}