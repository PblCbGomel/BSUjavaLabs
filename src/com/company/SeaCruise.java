package com.company;

public interface SeaCruise {
  void readFromFile(String fileName);
  void writeInFile(String fileName);

  void readFromBinary(String fileName);
  void writeInBinary(String fileName);

  void readFromXML(String fileName);
  void writeInXML(String fileName);

  void writeOnConsole();

  void readPartOfFile(String fileName, Integer stringNumber);

  void writeXMLWithEncoder(String fileName);
  void readXMLWithDecoder(String fileName);

  DateStorage sort();
}
