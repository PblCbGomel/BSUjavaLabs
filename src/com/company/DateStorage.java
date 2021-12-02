package com.company;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.management.Attribute;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DateStorage implements SeaCruise {

  private List<Ship> storage;

  public DateStorage() {
    storage = new ArrayList<>();
  }

  @Override
  public void readFromFile(String fileName) {
    try (FileReader reader = new FileReader(fileName)) {
      Scanner read = new Scanner(reader);

      while (read.hasNextLine()) {
        String obj = read.nextLine();
        String[] arr = obj.split(" ");

        for (int i = 0; i < arr.length; ++i) {
          if (arr[i].matches("^\\+?[1-9][0-9]*$") == true) {
            String name = new String();

            for (int j = 0; j < i; ++j) {
              name += arr[j] + " ";
            }
            storage.add(new Ship(name.trim(), Integer.parseInt(arr[i]), arr[i + 1],
                Integer.parseInt(arr[i + 2])));
            break;
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeInFile(String fileName) {
    try (FileWriter writer = new FileWriter(fileName, false)) {
      for (int i = 0; i < storage.size(); ++i) {
        writer.write(storage.get(i) + "\n");
      }
      writer.close();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  @Override
  public void readFromBinary(String fileName) {
    try {
      FileInputStream fileInput = new FileInputStream(fileName);
      ObjectInputStream readObj = new ObjectInputStream(fileInput);
      Ship sh;
      while (fileInput.available() > 0) {
        sh = (Ship) readObj.readObject();
        if (sh == null) {
          break;
        }
        storage.add(sh);
      }
      readObj.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeInBinary(String fileName) {
    try {
      ObjectOutputStream writeObj = new ObjectOutputStream(new FileOutputStream(fileName));
      for (int i = 0; i < storage.size(); ++i) {
        Ship sh = new Ship(storage.get(i));
        writeObj.writeObject(sh);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void readFromXML(String fileName) {
    File xmlFile = new File(fileName);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    try {
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      InputStream in = new FileInputStream(fileName);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      Ship item = null;
      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();
        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          if (startElement.getName().getLocalPart().equals("Ship")) {
            item = new Ship();
            Iterator attributes = startElement
                .getAttributes();
            while (attributes.hasNext()) {
              Attribute attribute = (Attribute) attributes.next();
              if (attribute.getName().toString().equals("name")) {
                item.setDate((String) attribute.getValue());
              }
            }
          }
          if (event.asStartElement().getName().getLocalPart()
              .equals("name")) {
            event = eventReader.nextEvent();
            item.setName(event.asCharacters().getData());
            continue;
          }

          if (event.isStartElement()) {
            if (event.asStartElement().getName().getLocalPart()
                .equals("num")) {
              event = eventReader.nextEvent();
              item.setNumber(Integer.parseInt(event.asCharacters().getData()));
              continue;
            }
          }
          if (event.asStartElement().getName().getLocalPart()
              .equals("date")) {
            event = eventReader.nextEvent();
            item.setDate(event.asCharacters().getData());
            continue;
          }
          if (event.asStartElement().getName().getLocalPart()
              .equals("life")) {
            event = eventReader.nextEvent();
            item.setLife(Integer.parseInt(event.asCharacters().getData()));
            continue;
          }
        }

        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart().equals("Ship")) {
            storage.add(item);
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeInXML(String fileName) {
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      Document doc = docBuilder.newDocument();
      org.w3c.dom.Element rootElement = doc.createElement("Storage");
      doc.appendChild(rootElement);

      for (int i = 0; i < storage.size(); ++i) {
        Element staff = doc.createElement("Ship");
        rootElement.appendChild(staff);

        Ship s = storage.get(i);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(s.getName()));
        staff.appendChild(name);

        Element num = doc.createElement("num");
        num.appendChild(doc.createTextNode(String.valueOf(s.getNumber())));
        staff.appendChild(num);

        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode(s.getDate()));
        staff.appendChild(date);

        Element life = doc.createElement("life");
        life.appendChild(doc.createTextNode(String.valueOf(s.getLife())));
        staff.appendChild(life);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);

      }
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void readPartOfFile(String fileName, Integer stringNumber) {
    try {
      RandomAccessFile raf = new RandomAccessFile(fileName, "r");
      int count = 0;
      for (int i = 0; i < stringNumber; ++i) {
        count += raf.readLine().length() + 1;
      }
      raf.seek(count);
      while (raf.getFilePointer() != raf.length()) {
        String obj = raf.readLine();
        String[] arr = obj.split(" ");

        for (int i = 0; i < arr.length; ++i) {
          if (arr[i].matches("^\\+?[1-9][0-9]*$") == true) {
            String name = new String();

            for (int j = 0; j < i; ++j) {
              name += arr[j] + " ";
            }
            storage.add(new Ship(name.trim(), Integer.parseInt(arr[i]), arr[i + 1],
                Integer.parseInt(arr[i + 2])));
            break;
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeOnConsole() {
    for (int i = 0; i < storage.size(); ++i) {
      System.out.println(storage.get(i));
    }
  }

  @Override
  public void writeXMLWithEncoder(String fileName) {
    try {
      FileOutputStream os = new FileOutputStream(fileName);
      XMLEncoder encoder = new XMLEncoder(os);
      for (Ship sh : storage) {
        encoder.writeObject(sh);
      }
      encoder.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void readXMLWithDecoder(String fileName) {
    try {
      XMLDecoder xmle = new XMLDecoder(new FileInputStream(fileName));
      while (true) {
        Ship sh = (Ship) xmle.readObject();
        storage.add(sh);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
    } catch (ArrayIndexOutOfBoundsException e) {
    }
  }

  @Override
  public DateStorage sort() {
    storage.sort((a, b) -> a.registerNumber - b.registerNumber);
    return this;
  }
}
