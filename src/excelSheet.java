import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelSheet {
    private FileInputStream inputFile;
    private String excelPath;
    private Workbook workbook;
    private Sheet sheet;

    public excelSheet(){
        excelPath = "C:/Users/karim/Desktop/test.xlsx";
        try{
            System.out.println("Opening excelSheet...");
            inputFile = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(inputFile);
            sheet = workbook.getSheetAt(0);
            System.out.println("excelsheet opened successfully!");
        }catch(IOException e){
            System.out.println("error opening excelsheet: "+e.getMessage());
            
            try{
                System.out.println("Creating ExcelSheet...");
                FileOutputStream outputStream = new FileOutputStream(excelPath);
                System.out.println("ExcelSheet created successfully");
                outputStream.close();
            }catch(IOException err){
                System.out.println("error creating excelsheet: "+err.getMessage());
            }
        }
    }

    void insert(ResultSet Query){
        Row row1 = sheet.getRow(0);
        Cell cell = row1.createCell(2);
        cell.setCellValue("test");
        try(FileOutputStream outputStream = new FileOutputStream(excelPath)){
            workbook.write(outputStream);
            System.out.println("inserting excelSheet successfully!");
        }catch(IOException e){
            System.out.println("error opening excelsheet: "+e.getMessage());
        }
    }

    void read(){
        for (Row row : sheet) {
            for (Cell Cell : row) {
                switch (Cell.getCellType()) {
                    case STRING:
                        System.out.println(Cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        System.out.println(Cell.getNumericCellValue());
                        break;
                    case FORMULA:
                        try {
                            switch (Cell.getCachedFormulaResultType()) {
                                case STRING:
                                    System.out.print("Formula String: " + Cell.getStringCellValue() + "\t");
                                    break;
                                case NUMERIC:
                                    System.out.print("Formula Numeric: " + Cell.getNumericCellValue() + "\t");
                                    break;
                                case BOOLEAN:
                                    System.out.print("Formula Boolean: " + Cell.getBooleanCellValue() + "\t");
                                    break;
                                default:
                                    System.out.print("Formula Unknown\t");
                                    break;
                            }
                        } catch (IllegalStateException e) {
                            System.out.print("Error: " + e.getMessage() + "\t");
                        }
                        break;
                    case null:
                        break;
                    default:
                        System.out.println("unknown value!");
                        break;
                }
            }
            System.out.println();
        } 
    }
}
