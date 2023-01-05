package com.shivam;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "userId", "firstName", "lastName", "email", "jobTitle" };

  public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public static List<CustomerData> csvToDatabase(InputStream is)
  {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) 
    {

      List<CustomerData> customerList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  CustomerData customerData = new CustomerData(
    			  Integer.parseInt(csvRecord.get("userId")),
    			  csvRecord.get("firstName"),
    			  csvRecord.get("lastName"),
    			  csvRecord.get("email"),
    			  csvRecord.get("jobTitle")
    			  );

    	  customerList.add(customerData);
      }

      return customerList;
    } 
    catch (IOException e) 
    {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
  
  public static ByteArrayInputStream databaseToCSV(List<CustomerData> customerList) 
  {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) 
    {
      for (CustomerData customerData : customerList)
      {
        List<String> data = Arrays.asList(
        		String.valueOf(customerData.getUserId()),
                customerData.getFirstName(),
                customerData.getLastName(),
                customerData.getEmail(),
                customerData.getJobTitle()
            );
        csvPrinter.printRecord(data);
      }
      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }
}