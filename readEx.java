/***** Set Your Package Name******/


import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Amin.MG
 */
public class readEx {

    private String pathname;
    private int sheetNumber=0;
    private Object[][] val;
    private int[][] rowcol;
    
    /// Reading the whole File
    //file ra kamel mikhanad
    public readEx(String pathname  ) throws Exception{
        this.pathname = pathname;
        priRead();
    }
    
    
    // Read the whole data in specific sheet
    //sheet moshakhas az file ra mikhanad
    
    
      public readEx(String pathname , int sheetNumber ) throws Exception{
        this.pathname = pathname;
        this.sheetNumber=sheetNumber;
        priRead();
    }
      
      //Read the data in specific row and column
      //setr va sotoun moshakahs az file mikhanad
      
      
         public readEx(String pathname ,int[][] rowcol ) throws Exception{
        this.pathname = pathname;
        this.width=(rowcol[0][1]-rowcol[0][0])+1;
        this.height=(rowcol[1][1]-rowcol[1][0])+1;
        priRead(rowcol);
    }
         //Read the data in specific sheet and coordinate
         //setro o sotoun moshakhas az sheet khas file ra mikhanad
         
      public readEx(String pathname , int sheetNumber ,int[][] rowcol ) throws Exception{
        this.pathname = pathname;
        this.sheetNumber=sheetNumber;
             this.width=rowcol[0][1]-rowcol[0][0]+1;
        this.height=rowcol[1][1]-rowcol[1][0];
        priRead(rowcol);
    }
    
      //Return the readen data as object and the dimension as a 2d array
      //andaze object zakhire konande ra mifrestad dar araye size va khod zakhire konanade ra ham mifrestad
      public Object[][] getVal(int[] size){
          size[0]=width;
          size[1]=height;
          return val;
      }

      int width;
      int height;
      
      //Reading Function
      //tavabe khanande file
    private void priRead() throws Exception{
 
    File file = new File(pathname);
        FileInputStream is=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(is);
        XSSFSheet sheet=wb.getSheetAt(sheetNumber);
        Iterator<org.apache.poi.ss.usermodel.Row> it=sheet.iterator();
        
        //Size of table
         Iterator<org.apache.poi.ss.usermodel.Row> iter=sheet.iterator();
       width =sheet.getLastRowNum()+1;
        height=columnSize(iter)+1;
         val=new Object[width][height];
       
        while (it.hasNext()) {
  
            org.apache.poi.ss.usermodel.Row row=it.next();
            
            Iterator<Cell> cell=row.cellIterator();
            while (cell.hasNext()) {
            
                Cell ce=cell.next();
               
                
                
                switch(ce.getCellTypeEnum()){
                    case NUMERIC:
                        val[ce.getRowIndex()][ce.getColumnIndex()]=ce.getNumericCellValue();
                        break;
                    case STRING:
                        val[ce.getRowIndex()][ce.getColumnIndex()]=ce.getStringCellValue();
                        break;
                      
                     
                }
                
            }             
       
    }
        
        
        is.close();
 
    }
    
    private void priRead(int[][] rowcol) throws Exception{

    File file = new File(pathname);
        FileInputStream is=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(is);
        XSSFSheet sheet=wb.getSheetAt(sheetNumber);
      
        
        //Size of table
          // System.out.println("rowcal check");
         
         val=new Object[width][height];
            org.apache.poi.ss.usermodel.Row row;
            Cell ce;    
      for(int i=rowcol[0][0]-1;i<rowcol[0][1];i++){
            
            
            for(int j=rowcol[1][0]-1;j<rowcol[1][1];j++){
              try{
              row=sheet.getRow(i);
                ce=row.getCell(j);
               
                 
                
               switch(ce.getCellTypeEnum()){
                    case NUMERIC:
                      val[i-(rowcol[0][0]-1)][j-(rowcol[1][0]-1)]=ce.getNumericCellValue();
                        break;
                    case STRING:
                        val[i-(rowcol[0][0]-1)][j-(rowcol[1][0]-1)]=ce.getStringCellValue();
                        break;
                     default:
                         val[i-(rowcol[0][0]-1)][j-(rowcol[1][0]-1)]="unknown";
                         break;
                }
                   }catch(Exception e){  val[i-(rowcol[0][0]-1)][j-(rowcol[1][0]-1)]=e.getMessage();  }
                                                                                                
            }  
          
    }
        
 
  is.close();
    }
private int columnSize(Iterator<org.apache.poi.ss.usermodel.Row> iter){
       int max=0;
       
    while (iter.hasNext()) {
        if(iter.next().getLastCellNum()>max)
           max= iter.next().getLastCellNum();
        }
        return max;
}
  
    
}
