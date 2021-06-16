package impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDriven {

    @Test
    public ArrayList doDDT() throws Exception{
        FileInputStream fis = new FileInputStream("src/test/java/data/restDataDrivenPOI.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(0);
        int shCount = wb.getNumberOfSheets();
        ArrayList<String> arr = new ArrayList<>();

        for(int i=0;i<shCount;i++)
        {
            if(wb.getSheetName(i).equalsIgnoreCase("testdata"))
            {
                //XSSFSheet sh = wb.getSheetAt(i);
                Iterator<Row> itRow = sh.rowIterator();
                Row rw = itRow.next();
                Iterator<Cell> itCell = rw.cellIterator();
                int k=0,coloumn=0;

                    while(itCell.hasNext())
                    {
                        Cell cl= itCell.next();

                        if(cl.getStringCellValue().equalsIgnoreCase("TCs"))
                        {
                            coloumn=k;
                            break;
                        }
                        k++;
                    }

                    while(itRow.hasNext())
                    {
                        rw= itRow.next();

                        if(rw.getCell(coloumn).getStringCellValue().equalsIgnoreCase("RestAssured"))
                        {
                            Iterator<Cell> cAns = rw.cellIterator();

                            while(cAns.hasNext())
                            {
                                Cell cAns2 = cAns.next();

                                if(cAns2.getCellType()==Cell.CELL_TYPE_STRING)
                                {
                                    arr.add(cAns2.getStringCellValue());
                                }
                                else
                                {
                                    arr.add(NumberToTextConverter.toText(cAns2.getNumericCellValue()));
                                }
                            }
                        }
                    }

                }
            }
        System.out.println(arr);

        return arr;
        }

    }
