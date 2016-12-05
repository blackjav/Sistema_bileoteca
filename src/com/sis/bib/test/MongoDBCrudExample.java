package com.sis.bib.test;
import java.io.IOException;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jackson.map.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
 
public class MongoDBCrudExample {
 
  public static void main(String[] args) {
    mongoTestCRUDOperations();
  }
 
  public static void mongoTestCRUDOperations() {
    MongoClient mongoClient = null;
 
    try {
      System.out .println("Using mongoTestCRUDOperations() to 'test' database...");
      mongoClient = new MongoClient();
      MongoDatabase db = mongoClient.getDatabase("prueba");
 
      MongoCollection<Document> collection = db.getCollection("inventory");
 
      // Mostrar todos
      System.out.println("+++++++++++++++++++++++++	MUESTRA TODOS LOS ELEMENTOS DE LA COLECCIÓN++++++++++++++++++++++++++++++++++");
      showAllDocuments(collection);
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
 
      // INSERTA ITEM
      System.out.println("*************************************INSERTA UN ITEM *************************************************");
      final Item item1 = new Item();
      item1.setId("1454163351");
      item1.setItemId("B0037ZG3DS");
      item1.setDescription("Mr. Coffee BVMC-PSTX91 Optimal Brew 10-Cup Thermal Coffeemaker, Black/Stainless Steel");
      item1.setManufacturer("Mr. Coffee");
      item1.setDepartment("kitchen");
      item1.setCategory("Coffee Machines");
      item1.setSubCategory("Thermal Carafe");
      item1.setListPrice(89.99);
      item1.setPrice(69.00);
      item1.setQuantity(3);
      boolean status = insertIntoCollection(collection, item1);
      System.out.println("status del insert : " + status);
      System.out.println("*******************************************************************************************************");
 
//      ACTUALIZA UN ITEM
      System.out.println("*************************************ACTUALIZA UN ITEM *************************************************");
      final Item item2 = new Item();
      item2.setId("1454163352");
      item2.setItemId("B00DUHACEE");
      item2.setDescription("SterlingPro French Coffee Press -- 8 Cup/4 Mug (1 liter, 34 oz), Chrome");
      item2.setManufacturer("SterlingPro");
      item2.setDepartment("kitchen");
      item2.setCategory("Coffee Machines");
      item2.setSubCategory("French Press");
      item2.setListPrice(68.00);
      item2.setPrice(29.97);
      item2.setQuantity(8);
 
      status = updateCollection(collection, item2);
      System.out.println("Status de actualizacion: " + status);
      System.out.println("************************************* *************************************************");
      
      //Muestra un documento por id
      System.out.println("*************************************MUESTRA UN DOCUMENTO POR ID*************************************************");
      showDocumentByID(collection, item2.getId());
      System.out.println("**************************************************************************************");
 
      //ACTUALIZANDO DE NUEVO EL ITEM2
      System.out.println("*************************************ACTUALIZA DE NUEVO UN ITEM *************************************************");
      item2.setPrice(31.99);
      status = updateCollection(collection, item2);
      System.out.println("Status del update: " + status);
      showDocumentByID(collection, item2.getId());
      System.out.println("*************************************INSERTA UN ITEM *************************************************");
 
      //intentando actualizar un item que no exisitia
      System.out.println("*************************************UPSERT DE NUEVO UN ITEM *************************************************");
      final Item item3 = new Item();
      item3.setId("1454163352");
      item3.setDescription("SterlingPro French Coffee Press");
      item3.setCategory("Coffee Machines");
      item3.setSubCategory("French Press");
      status = replaceUpsertCollection(collection, item3);
      System.out.println("Status de update: " + status);
      showDocumentByID(collection, item3.getId());
      System.out.println("**************************************************************************************");
 
      
      //HACIENDO UPDATE DEL ELEMENTO ANTERIOR
      System.out.println("*************************************ACTUALIZA ELEMENTO ANTERIOR *************************************************");
      item3.setPrice(31.99);
      status = replaceUpsertCollection(collection, item3);
      System.out.println("Status de update: " + status);
      showDocumentByID(collection, item3.getId());
      System.out.println("************************************* *************************************************");
 
      System.out.println("************************************* *************************************************");
      item3.setListPrice(72.99);
      status = updateCollection(collection, item3);
      System.out.println("Status de update: " + status);
      showDocumentByID(collection, item3.getId());
      System.out.println("************************************* *************************************************");

 
      System.out.println("************************************* BUSCA Y ACTUALIZA *************************************************");
      item3.setQuantity(12);
      Document updatedDoc = findAndUpdateCollection(collection, item3);
      System.out.println("Actualizar documento: " + updatedDoc);
      showDocumentByID(collection, item3.getId());
      System.out.println("************************************* *************************************************");

 
      // mostrar todos los documentos de la coneccion antes de borrarlos
      System.out.println("*************************************ELEMENTOS ANTES DE SER BORRADOS *************************************************");
      showAllDocuments(collection);
      System.out.println("************************************* *************************************************");

 
      // BUSCA POR ID Y LO BORRA
      System.out.println("************************************* BUSCA ID Y BORRA *************************************************");
      Document deletedDoc = findOneAndDeleteCollection(collection,
          "1454163350");
      System.out.println("Deleted Document: " + deletedDoc);
      System.out.println("************************************* *************************************************");

 
      System.out.println("************************************* BORRA UNO DE LA COLECCION*************************************************");
      DeleteResult deletedResult = deleteOneFromCollection(
          collection, "1454163349");
      System.out.println("Deleted Document: " + deletedResult);
      System.out.println("************************************* *************************************************");

 
      // MUESTRA LOS DOCUMENTOS DESPUES DE SER BORRADOS
      System.out.println("*************************************DOCUEMNTOS DESPUES DE LOS DELETE'S *************************************************");
      showAllDocuments(collection);
      System.out.println("************************************* *************************************************");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mongoClient.close();
    }
  }
 
  
  // TODO inserta uno en la collection
  public static boolean insertIntoCollection(final MongoCollection<Document> collection, final Item item) {
    // usar jackson para convertir un objeto a JSON
    ObjectMapper mapper = new ObjectMapper();
    String jsonString;
    boolean status = true;
 
    try {
      jsonString = mapper.writeValueAsString(item);
      // inserta json en mongo 
      System.out.println(String.format("Item #%s: %s", item.getId(),jsonString));
      Document doc = Document.parse(jsonString);
      collection.insertOne(doc);
    } catch (MongoWriteException mwe) {
      status = false;
    } catch (IOException e) {
      status = false;
    }
    return status;
  }
 
  
//  TODO remplaza uno
  public static boolean replaceUpsertCollection(final MongoCollection<Document> collection, final Item item) {
    boolean status = true;
    ObjectMapper mapper = new ObjectMapper();
    String jsonString;
 
    try {
      jsonString = mapper.writeValueAsString(item);
      // Inserta JSON en  MongoDB
      System.out.println(String.format("Item #%s: %s", item.getId(),jsonString));
      BasicDBObject query = new BasicDBObject();
      query.append("_id", item.getId());
      Document doc = Document.parse(jsonString);
      UpdateResult result = collection.replaceOne(query, doc,(new UpdateOptions()).upsert(true));
      System.out.println("Replace Matched Count....: "+ result.getMatchedCount());
      System.out.println("Replace Modified Count...: "+ result.getModifiedCount());
    } catch (MongoWriteException mwe) {
      status = false;
    } catch (IOException e) {
      status = false;
    }
 
    return status;
  }
 
  public static boolean updateCollection(
      final MongoCollection<Document> collection, final Item item) {
    boolean status = true;
    ObjectMapper mapper = new ObjectMapper();
    String jsonString;
 
    try {
      jsonString = mapper.writeValueAsString(item);
      // update/upsert using JSON into MongoDB
      System.out.println(String.format("Item #%s: %s", item.getId(),
          jsonString));
      BasicDBObject query = new BasicDBObject();
      query.append("_id", item.getId());
      BasicDBObject doc = BasicDBObject.parse(jsonString);
      Bson newDocument = new Document("$set", doc);
      UpdateResult result = collection.updateOne(query, newDocument,
          (new UpdateOptions()).upsert(true));
      System.out.println("Update Matched Count....: "+ result.getMatchedCount());
      System.out.println("Update Modified Count...: "+ result.getModifiedCount());
    } catch (MongoWriteException mwe) {
      status = false;
    } catch (IOException e) {
      status = false;
    }
 
    return status;
  }
 
  public static Document findAndUpdateCollection(
      final MongoCollection<Document> collection, final Item item) {
    ObjectMapper mapper = new ObjectMapper();
    Document resultDocument = null;
    String jsonString;
 
    try {
      // findOneAndUpdate using JSON into MongoDB
      jsonString = mapper.writeValueAsString(item);
      System.out.println(String.format("Item #%s: %s", item.getId(),
          jsonString));
      BasicDBObject query = new BasicDBObject();
      query.append("_id", item.getId());
      BasicDBObject doc = BasicDBObject.parse(jsonString);
      Bson newDocument = new Document("$set", doc);
      resultDocument = collection.findOneAndUpdate(query,
          newDocument, (new FindOneAndUpdateOptions()).upsert(true));
    } catch (IOException e) {
      e.printStackTrace();
    }
 
    return resultDocument;
  }
 
  public static Document findOneAndDeleteCollection(
      final MongoCollection<Document> collection, final String id) {
    Document resultDocument = null;
 
    // findOneAndDelete from MongoDB
    System.out.println(
      "Using findOneAndDeleteCollection to delete ID: " + id);
    BasicDBObject query = new BasicDBObject();
    query.append("_id", id);
    resultDocument = collection.findOneAndDelete(query);
 
    return resultDocument;
  }
 
  public static DeleteResult deleteOneFromCollection(
      final MongoCollection<Document> collection, final String id) {
    DeleteResult resultDocument = null;
 
    // findOneAndDelete from MongoDB
    System.out.println(
        "Using deleteOneFromCollection to delete ID: " + id);
    BasicDBObject query = new BasicDBObject();
    query.append("_id", id);
    resultDocument = collection.deleteOne(query);
 
    return resultDocument;
  }
 
  public static void showDocumentByID(
      final MongoCollection<Document> collection, final String id) {
    BasicDBObject query = new BasicDBObject();
    query.append("_id", id);
 
    for (Document doc : collection.find(query)) {
      System.out.println(doc.toJson());
    }
  }
 
  //<TODO Muestra todos los docuemntos de la colleccion
  public static void showAllDocuments(
      final MongoCollection<Document> collection) {
    System.out.println("----[Muestra todos lod documentos en la coleccion inventory]----");
    for (Document doc : collection.find()) {
    	System.out.println(doc.toJson());
    }
  }
 
  public static void showAllDocs(final DBCollection collection) {
    DBCursor cursor = (DBCursor) collection.find().iterator();
    try {
      while (cursor.hasNext()) {
        System.out.println(cursor.next().toString());
      }
    } finally {
      cursor.close();
    }
  }
}