import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

class comm_func {
	private int r;
	
	int Search(double det, int sn) throws IOException {
		String excelFilePath = ".\\data\\testex1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		XSSFSheet sheet = workbook.getSheetAt(sn);
		for(r = 1; r <= sheet.getLastRowNum(); r++) { 
			XSSFRow row = sheet.getRow(r);
			XSSFCell cell = row.getCell(2);
			double num = cell.getNumericCellValue();
			if(num == det) {
				return r;
			}
		}
		return -1;
	}
	
	void display(int row) throws IOException {
		String excelFilePath = ".\\data\\testex1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow rowm = sheet.getRow(row);
		for(int c = 0; c < rowm.getLastCellNum(); c++) {
			XSSFCell cell = rowm.getCell(c);
			if(c == 2 || c == 4 || c==5) 
				System.out.print(cell.getNumericCellValue() + " ");
			else 
				System.out.print(cell.getStringCellValue() + " ");
			
			
		}
		System.out.println();
	}
	
	int Searchid(double det, int sn) throws IOException {
		String excelFilePath = ".\\data\\testex1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		XSSFSheet sheet = workbook.getSheetAt(sn);
		for(r = 1; r <= sheet.getLastRowNum(); r++) { 
			XSSFRow row = sheet.getRow(r);
			XSSFCell cell = row.getCell(0);
			double num = cell.getNumericCellValue();
			if(num == det) {
				return r;
			}
		}
		return -1;
	}

	int  Difference(int d1,int m1,int y1, int d2,int m2,int y2)
	   {
		LocalDate start_date= LocalDate.of(y1, m1, d1);
	      LocalDate end_date = LocalDate.of(y2, m2, d2);
	     Period diff = Period.between(start_date, end_date);
	     int daysdiff=diff.getDays()+diff.getMonths()*12+diff.getYears()*365;
	     return daysdiff;
	   }
	
	int fine(int d1,int m1,int y1,int d2,int m2,int y2) {
		 int e=0,f=0;
	        e= Difference(d1,m1,y1,d2, m2,y2);
	        e=e-15;
	        if(e>0 &&e<=5) {
	        	f=e;
	        }
	        else if(e>5 && e<=10) {
	        	f+=5+(e-5)*2; 
	        }
	        else if(e>10 && e<=20) {
	        	f+=15+(e-10)*5;
	        }
	        else if(e>20 && e<=30) {
	        	f+=65+(e-20)*10;
	        }
	        else if(e>30) {
	        	f+=165+(e-30)*20;
	        }
	        return f;
	}
	
}

class Login {
	Boolean search(int s1,String s2) throws IOException {
		 String excelFilePath=".\\data\\testex1.xlsx";
	        FileInputStream inputstream=new FileInputStream(excelFilePath);
	        XSSFWorkbook workbook= new XSSFWorkbook(inputstream);
	        XSSFSheet sheet=workbook.getSheetAt(2); 
	        for(int r=1;r<=sheet.getLastRowNum();r++) {
	        	XSSFRow row=sheet.getRow(r);
	        	XSSFCell cell=row.getCell(0);
	        	int word=(int)cell.getNumericCellValue();
	        	cell=row.getCell(1); 
	        	String word1=(String)cell.getStringCellValue();
	        	cell=row.getCell(3);
	        	int check=(int)cell.getNumericCellValue();
	        	if(word==s1 && word1.equals(s2) && check==1) {
	                return true;
	        	}
	        }
		return false;
	}
	
}

class Admin extends comm_func{
	void Add_Student() throws IOException {
	        
			String excelFilePath = ".\\data\\testex1.xlsx";
			
			FileInputStream inputstream=new FileInputStream(excelFilePath);
			
			XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		
			XSSFSheet sheet = workbook.getSheetAt(2);
			
			int r=sheet.getLastRowNum();
			r++;
			XSSFRow row=sheet.createRow(r);
			
		    XSSFCell cell=row.createCell(0);
		    System.out.print("Enter UserId: ");
			 Scanner s1=new Scanner(System.in);
			 String s2=s1.nextLine();
			 cell.setCellValue((String) s2);
			 
			 cell=row.createCell(1);
			 System.out.print("Enter Password: ");
			 s2=s1.nextLine(); 
			 cell.setCellValue((String) s2);
			 
			 cell=row.createCell(2);
			 System.out.print("Enter Student Name: ");
			 s2=s1.nextLine(); 
			 cell.setCellValue((String) s2);
			 
			 cell=row.createCell(3);
			 cell.setCellValue((int)(1));
			
			 FileOutputStream outstream = new FileOutputStream(excelFilePath);
			 workbook.write(outstream);
			outstream.close();
		  }
	
	void RemoveStudent(int id) throws IOException {
		String excelFilePath = ".\\data\\testex1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		XSSFSheet sheet = workbook.getSheetAt(2);
		int row_num = Searchid(id, 2);
		XSSFRow row = sheet.getRow(row_num);
		XSSFCell cell = row.getCell(3);
		cell.setCellValue(0);
		
		FileOutputStream outstream = new FileOutputStream(excelFilePath);
		workbook.write(outstream);
		outstream.close();
		System.out.println("Removed id "+ id);
		
	}
	
void Add_Book() throws IOException {
        
		String excelFilePath= ".\\data\\testex1.xlsx";
		
		FileInputStream inputstream=new FileInputStream(excelFilePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
	
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int r=sheet.getLastRowNum();
		r++;
		XSSFRow row=sheet.createRow(r);
		
	    XSSFCell cell=row.createCell(0);
	    System.out.print("Enter Author Name: ");
		 Scanner s1=new Scanner(System.in);
		 String s2=s1.nextLine();
		 cell.setCellValue((String) s2);
		 
		 cell=row.createCell(1);
		 System.out.print("Enter Book Name: ");
		 s2=s1.nextLine(); 
		 cell.setCellValue((String) s2);
		 
		 cell=row.createCell(2);
		 System.out.print("Enter ISBN number: ");
		 s2=s1.nextLine(); 
		 cell.setCellValue((String) s2);
		 
		 cell=row.createCell(3);
		 System.out.print("Enter Genre: ");
		 s2=s1.nextLine(); 
		 cell.setCellValue((String) s2);
		 
		 cell=row.createCell(4);
		 System.out.print("Enter Release Year: ");
		 s2=s1.nextLine(); 
		 cell.setCellValue((String) s2);
		 
		 cell=row.createCell(5);
		 cell.setCellValue((int)(1));
		 
		 FileOutputStream outstream = new FileOutputStream(excelFilePath);
		 workbook.write(outstream);
		outstream.close();
	}

	void RemoveBook(int isbn) throws IOException {
		String excelFilePath = ".\\data\\testex1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int row_num = Search(isbn, 0);
		XSSFRow row = sheet.getRow(row_num);
		XSSFCell cell = row.getCell(5);
		cell.setCellValue(0);
		
		FileOutputStream outstream = new FileOutputStream(excelFilePath);
		workbook.write(outstream);
		outstream.close();
		System.out.println("BOOK REMOVED!");
	}
	
	void Search_book(String book_name) throws IOException {
		int r;
		String excelFilePath = ".\\data\\testex1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		for(r = 1; r <= sheet.getLastRowNum(); r++) { 
			XSSFRow row = sheet.getRow(r);
			XSSFCell cell = row.getCell(0);
			String word = cell.getStringCellValue();
			if(word.equals(book_name)) {
				System.out.println("THE BOOK IS AVAILABLE");
				return ;
			}
		}
		System.out.println("THE BOOK IS NOT AVAILABLE");
	}
	
	void student_det(int s1,int date,int month,int year) throws IOException {
		String excelFilePath= ".\\data\\testex1.xlsx";
        FileInputStream inputstream=new FileInputStream(excelFilePath);
        XSSFWorkbook workbook= new XSSFWorkbook(inputstream);
        XSSFSheet sheet=workbook.getSheetAt(1); 
        for(int r=0;r<= sheet.getLastRowNum();r++) {
        	XSSFRow row=sheet.getRow(r);
        	XSSFCell cell=row.getCell(5);//0 for user name
        	int word= (int)cell.getNumericCellValue();
        	if(word == s1) {
        		  cell=row.getCell(9);
                  int f=(int) cell.getNumericCellValue();
                  if(f==1) {
        		   for(int col=0; col< 5;col++) {
        			cell=row.getCell(col);
        			if(col == 2 || col == 4) 
        				System.out.print(cell.getNumericCellValue() + " ");
        				else 
    					System.out.print(cell.getStringCellValue() + " ");
        		}
        	   cell=row.getCell(6); //get for date.
      		   int d=(int) cell.getNumericCellValue();
               cell=row.getCell(7);// for month.
               int  b=(int) cell.getNumericCellValue();
               cell=row.getCell(8); //for year.
               int c=(int) cell.getNumericCellValue();
             
               int e= fine(d, b,c,date,month,year);
        		System.out.print(d+" "+b+" "+ c+" "+e+"  "+"\n");
                  }
        	 }
        }
	}

	
}

class Student extends Admin{
	void Issue(int isbn, int id, int d, int m, int y) throws IOException{
		String excelFilePath = ".\\data\\testex1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		XSSFSheet sheet1 = workbook.getSheetAt(0);
		XSSFSheet sheet2 = workbook.getSheetAt(1);
		int row_num = Search(isbn, 0);
		XSSFRow row1 = sheet1.getRow(row_num);
		XSSFCell cell = row1.getCell(5);
		if(cell.getNumericCellValue() == 1) {
			XSSFRow row2 = sheet2.createRow(sheet2.getLastRowNum()+ 1);
			for(int c = 0; c < row1.getLastCellNum() - 1; c++) {
				XSSFCell cell1 = row1.getCell(c);
				XSSFCell cell2 = row2.createCell(c);
				if(c == 2 || c == 4) 
					cell2.setCellValue(cell1.getNumericCellValue());
				else 
					cell2.setCellValue(cell1.getStringCellValue());
			}
			
			XSSFCell cell2 = row2.createCell(5);
			cell2.setCellValue(id);
			cell2 = row2.createCell(6);
			cell2.setCellValue(d);
			cell2 = row2.createCell(7);
			cell2.setCellValue(m);
			cell2 = row2.createCell(8);
			cell2.setCellValue(y);
			cell2 = row2.createCell(9);
			cell2.setCellValue(1);
			
			FileOutputStream outstream = new FileOutputStream(excelFilePath);
			workbook.write(outstream);
			outstream.close();
			RemoveBook(isbn);
			System.out.println("Issued");
		}
		else
			System.out.println("BOOK NOT AVAILABLE");
	}
	
	
	void Return(int isbn) throws IOException{
		String excelFilePath = ".\\data\\testex1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		XSSFSheet sheet1 = workbook.getSheetAt(0);
		XSSFSheet sheet2 = workbook.getSheetAt(1);
		int r1_num = Search(isbn, 0);
		int r2_num = Search(isbn, 1);
		XSSFRow row1 = sheet1.getRow(r1_num);
		XSSFRow row2 = sheet2.getRow(r2_num);
		XSSFCell cell1 = row1.getCell(5);
		XSSFCell cell2 = row2.getCell(9);
		cell1.setCellValue(1);
		cell2.setCellValue(0);
		
		FileOutputStream outstream = new FileOutputStream(excelFilePath);
		workbook.write(outstream);
		outstream.close();
		System.out.println("Returned");
	}
	
	void total_fine(double s1,int date,int month,int year) throws IOException {
		int a=0;
		String excelFilePath= ".\\data\\testex1.xlsx";
        FileInputStream inputstream=new FileInputStream(excelFilePath);
        XSSFWorkbook workbook= new XSSFWorkbook(inputstream);
        XSSFSheet sheet=workbook.getSheetAt(1); 
       for(int r=0;r<=sheet.getLastRowNum();r++) {
      	XSSFRow row=sheet.getRow(r);
      	XSSFCell cell=row.getCell(5);
      	double word=cell.getNumericCellValue();
          if(word==s1) {
        	cell=row.getCell(9);
        	int abcd=(int)cell.getNumericCellValue();
        	if(abcd == 1) {
          	cell=row.getCell(6); //get for date.
     		   int d=(int) cell.getNumericCellValue();
             cell=row.getCell(7);// for month.
             int  b=(int) cell.getNumericCellValue();
             cell=row.getCell(8); //for year.
             int c=(int) cell.getNumericCellValue();
      	      a+=fine(d,b,c,date,month,year);
        	}
            }
      }

		System.out.println("Total Fine is: "+a);
	}
	
	void searchbook() throws IOException {
		int i=0;
		System.out.println("1. Name of the book");
		System.out.println("2. Author");
		System.out.println("3. ISBN");
		System.out.println("4. Genre");
		System.out.println("5. Release Year");
		Scanner s1=new Scanner(System.in);
		System.out.println("Enter Your Choice");
		int b=s1.nextInt();
		b--;
		System.out.println("Enter Keyword Name: ");
		int c=0;
		String st = null;
		if(b==2 || b==4) {
			Scanner s3 = new Scanner(System.in);
			 c=s3.nextInt();
		}
		else {
			Scanner s2 = new Scanner(System.in);
			st=s2.nextLine();
		}
		
		String excelFilePath= ".\\data\\testex1.xlsx";
        FileInputStream inputstream=new FileInputStream(excelFilePath);
        XSSFWorkbook workbook= new XSSFWorkbook(inputstream);
        XSSFSheet sheet=workbook.getSheetAt(0); 
        XSSFRow row=sheet.getRow(0);
        for(int r=1;r<=sheet.getLastRowNum();r++) {
        	row=sheet.getRow(r);
        	XSSFCell cell=row.getCell(b);//Match the keyword.
        	if(b==2 || b==4) {
        		int Is=(int)cell.getNumericCellValue();
        		if(c==Is) {
        			display(r);
        		}
        	}
        	else {
        		
        		String St1=cell.getStringCellValue();
        		if(st.equals(St1)) {
        			display(r);
        		}
        	}
        }
        
      }
	
}

public class oop_p1 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("\n\n\n");
	    System.out.print("\t\t\t**\n");
	    System.out.print("\t\t\t*                1. ADMIN            *\n");
	    System.out.print("\t\t\t*                2. STUDENT          *\n");
	    System.out.print("\t\t\t*                3. EXIT             *\n");                                               
	    System.out.print("\t\t\t**\n");
	    System.out.println("\n\n\n\n");
	    
	    System.out.print("Enter Your Choice: ");
	    
	    Scanner scan = new Scanner(System.in);
	    boolean c = false;
	    int b=scan.nextInt();
	    if(b==2) {
	    	
	    	
	    	while(!c) {
	    		System.out.print("Enter ID: ");
	    		Scanner str=new Scanner(System.in);
	    		int id=str.nextInt();
	    		System.out.print("Enter Password: ");
	    		Scanner str1=new Scanner(System.in);
	    		String Password=str1.nextLine();
	    		Login l1=new Login();
	    		c=l1.search(id, Password);
	    		if(!c) System.out.println("Entered your Details is wrong");
	    	}
	    }
	   if(b==1) {
		   String pass="OOP";
		   while(!c) {
	    		System.out.print("Enter Password: ");
	    		Scanner str3=new Scanner(System.in);
	    		String Password=str3.nextLine();
	    		System.out.println(Password);
	    		if(Password.equals(pass)) {
	    			c=true;
	    			break;
	    		}
	    		else {
	    			System.out.println("Entered your Details is wrong");
	    		}
	    	}

	   }
	    
	    if(b == 1) {
	    	while(c) {
	    	System.out.println("\n\n\n");
	        System.out.print("\t\t\t***\n");
	        System.out.print("\t\t\t*                  1. ADD STUDENT                     *\n");
	        System.out.print("\t\t\t*                  2. REMOVE STUDENT                  *\n");
	        System.out.print("\t\t\t*                  3. ADD BOOK                        *\n");
	        System.out.print("\t\t\t*                  4. REMOVE BOOK                     *\n");
	        System.out.print("\t\t\t*                  5. SEARCH BOOK                     *\n");
	        System.out.print("\t\t\t*                  6. STUDENT DETAILS                 *\n");
	        System.out.print("\t\t\t*                  7. EXIT                            *\n");
	        System.out.print("\t\t\t***\n");
	        System.out.println("\n\n\n\n");
	        Admin a1=new Admin();
	        int n = scan.nextInt();
	        switch(n) {
	         
	        case 1:
	        	 a1.Add_Student();
	        	break;
	        case 2:
	        	a1.RemoveStudent(202103029);
	        	break;
	        case 3:
	        	a1.Add_Book();
	        	break;
	        case 4:
	        	a1.RemoveBook(1115);
	        	break;
	        case 5:
	        	a1.Search_book("Introduction to Sports Medicine and Athletic Training");
	        	break;
	        case 6:
	        	a1.student_det(202103001, 19, 7, 2022);
	        	break;
	        case 7:
	        	c=false;
	        	break;
	        }
	    	}
	    }
	    else if(b == 2) {
	    	while(c) {
	    	System.out.println("\n\n\n");
	        System.out.print("\t\t\t***\n");
	        System.out.print("\t\t\t*                  1. DUE (FINE)                      *\n");
	        System.out.print("\t\t\t*                  2. SEARCH BOOK                     *\n");
	        System.out.print("\t\t\t*                  3. ISSUE BOOK                      *\n");
	        System.out.print("\t\t\t*                  4. RETURN BOOK                     *\n");
	        System.out.print("\t\t\t*                  5. SHOW ISSUED BOOKS               *\n");
	        System.out.print("\t\t\t*                  6. EXIT                            *\n");
	        System.out.print("\t\t\t***\n");
	        System.out.println("\n\n\n\n");
	        
	        Student s = new Student();
	        int n = scan.nextInt();
	        switch(n) {
	        case 1:
	        	s.total_fine(202103001, 19, 7, 2022);
	        	break;
	        case 2:
	        	s.searchbook();
	        	break;
	        case 3:
	        	s.Issue(1112, 2020202, 2, 7, 2022);
	        	break;
	        case 4:
	        	s.Return(1114);
	        	break;
	        case 5:
	        	s.student_det(202103001, 19, 7, 2022);
	        	break;
	        case 6:
	        	c=false;
	        	break;
	        
	        }
	    	}
	        }
	         
	        
	    
	    
	    System.out.println("Thank You!");

	    
	    }
	    
		
	}


