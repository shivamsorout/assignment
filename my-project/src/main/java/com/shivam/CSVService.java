package com.shivam;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService 
{
  @Autowired
  CustomerRepository repository;

  public void save(MultipartFile file)
  {
    try 
    {
      List<CustomerData> list = CSVHelper.csvToDatabase(file.getInputStream());
      repository.saveAll(list);
    } 
    catch (IOException e) 
    {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() 
  {
    List<CustomerData> list = repository.findAll();

    ByteArrayInputStream in = CSVHelper.databaseToCSV(list);
    return in;
  }

  public List<CustomerData> getAllList()
  {
    return repository.findAll();
  }
}