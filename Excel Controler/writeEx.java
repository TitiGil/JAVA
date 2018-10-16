/**** SET YOUR PACKAGE NAME *****/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Amin.MG
 */
public   class writeEx {
    
    private static File file;
 
    private  static XSSFWorkbook wb;
     private static XSSFSheet sheet;
     
     private static FileInputStream is;
              
     ///ُspecify the target file 
     /// taeen makan zakhire sazi
     
    public static void openFile( String pathname ,int sheetNumber){
        try {
            
            file=new File(pathname);
              is=new FileInputStream(file);
              wb=new XSSFWorkbook(is);
              sheet= wb.getSheetAt(sheetNumber);
              
               
               
        } catch (Exception e) {JOptionPane.showMessageDialog(null,"openfile exception : " + e.getMessage());
        if(e.getMessage()==pathname+"(The system cannot find the file specified)"){
            try {
                 file.createNewFile();
                   is=new FileInputStream(file);
              wb=new XSSFWorkbook(is);
              sheet= wb.createSheet();
            } catch (Exception ex) {JOptionPane.showMessageDialog(null,"openfile exception : " + ex.getMessage());
            }
           
        }
        }
    }
    
    
    
    public static void closeFile(){
        try {
            is.close();
       FileOutputStream os=new FileOutputStream(file);
        wb.write(os);
           wb.close(); 
           os.close();
        } catch (Exception e) {JOptionPane.showMessageDialog(null, "close file writing : " +e.getMessage());
        }
        
    }
    
    /** Replace a specified cell by new value **///
    /** jaygozinye ye meghdar moshakhas dar yek cell moshakhas**///
    
    public static  void replaceEx( int width,int height,Object value){
         //System.err.println("Stsrt!");
        try{
         
     
      
          Cell ce=sheet.getRow(width-1).getCell(height-1);
        if(value instanceof String){
          ce.setCellValue((String)value);
          
        }else if(value instanceof Double)
        {
            ce.setCellValue((Double)value);
        }
        else if(value instanceof Integer){
            ce.setCellValue((Integer)value);
        }
        
          
            
       // System.err.println("Write");
        }catch(Exception e){ System.err.println("witing class exception : " +e.getMessage());}
        
    }
    
    
    
  private static Row rowWr;
    public static void creteNewRow(int r){
       rowWr  =sheet.createRow(r-1);
    }
    
    /** insert a new value in excle file **//
    /** darj meghdar jadid **//
    
    
    public static void writeNew(int c,Object value){
       
        Cell cell=rowWr.createCell(c-1);
         if(value instanceof String){
          cell.setCellValue((String)value);
          
        }else if(value instanceof Double)
        {
            cell.setCellValue((Double)value);
        }
        else if(value instanceof Integer){
            cell.setCellValue((Integer)value);
        }
        else{
            cell.setCellValue("Unknown");
        }
                    
    }
       public static void writeNew(int c,Double value){
       
        Cell cell=rowWr.createCell(c-1);
       
            cell.setCellValue((Double)value);
       
                    
    }
    
