class product {
      private String name;
      private String price;

     // Fill your code here
}

 

class book extends product {
      private String author;
      private String publisher;

      // Fill your code here
} // book

 

class cd extends product {
      private String artist;

      // Fill your code here
} // cd

 

public class ex28 {

      public static void main (String args[]) {

            System.out.println("Welcome to our bookstore!");

            book b = new book("Effective Java", "30", "Addison Wesley", "Joshua Bloch");
            System.out.println("Titlos bibliou: " + b.getName() + "\nTimi: " + b.getPrice() + " \nEkdoseis:" + b.getPublisher()+"\nAuthor "+b.getAuthor());

            cd c = new cd ( "the wall", "20.99", "Pink Floyd");
            System.out.println("Titlos cd : " + c.getName() + "\nTimi: " + c.getPrice()+ "\nArtist: " + c.getArtist());

            c.setPrice("9,99");
            System.out.println("Titlos cd : " + c.getName() + "\nTimi me ektptwsi: " +c.getPrice()+ "\nArtist: " + c.getArtist());
      }
} // bookStore