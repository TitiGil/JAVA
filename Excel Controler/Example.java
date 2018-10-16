
/** ADD YOUR PACKAGE NAME ***/


import java.util.Arrays;

/**
 *
 * @author AMG
 */
public class Example {
    
    public static void main(String[] arg){
       
        String sourceFile="C://source.xlsx";
        /** the source file is an excel file like below 
         * 
         * {1,2,3}
         * {4,5,6}
         * {7,8,9}
         */
        
        String destFile="C://Dest.xlsx";
        
        try{
          
            
            /* create a 2d objective array to save the result*/ 
            Object[][] result;
            
           readEx reader=new readEx(sourceFile);
           
           
           /* read the  whole data in file*/
           int[] dimension=new int[2];
            result=reader.getVal(dimension);
            int width=dimension[0];
            int height=dimension[1];
          
            for(int i=0;i<height;i++)
          System.out.println(Arrays.toString(result[i]));
            
            /** result is
             *  
             *  [1.0, 2.0, 3.0]
                [4.0, 5.0, 6.0]
                [7.0, 8.0, 9.0]
             */
          
            /*read specified cell  from row 2 to 3  and column 2 to 3  */
            reader=new readEx(sourceFile,new int[][]{{2,3},{2,3}});
             dimension=new int[2];
             result=reader.getVal(dimension);
              width=dimension[0];
             height=dimension[1];
             for(int i=0;i<height;i++)
          System.out.println(Arrays.toString(result[i]));
             
             
             /** result is 
              * 
              * [5.0, 6.0]
                [8.0, 9.0]
              */
             
             
             /**WRITING A EXCEL FILE ***/
             
          
            
            writeEx writer=new writeEx();
            writer.openFile(sourceFile, 0);
            
            
              /*** replace a value in specified cell (3,3) with new value "changed" **///
              
            writer.replaceEx(3, 3,"changed");
            writer.closeFile();
            
            /** the result is 
             * 
             * [1.0, 2.0, 3.0]
               [4.0, 9.0, 6.0]
               [7.0, 8.0, changed]
             */
            
        
            //** to add a new value you should first create a row with createNewRow(the row number) and then add data to specific cell of that row 
            //** by using writeNew(the cell number)**//
            
            
                /*** add new value to data file  in row 4 and cell 2**///
            writer.openFile(sourceFile, 0);
            writer.creteNewRow(4);
            writer.writeNew(2, "new Value");
            writer.closeFile();
            
            
            /*** the result is 
             * [1.0, 2.0, 3.0]
               [4.0, 9.0, 6.0]
               [7.0, 8.0, changed]
               [   ,new Value, ]   
             * 
             */
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
