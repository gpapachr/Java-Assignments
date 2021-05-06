class product {
      private String name;
      private String price;
      public String getName() {
            return name;
      }
      public String getPrice() {
            return price;
      }
      public void setPrice(String price) {
            this.price = price;
      }
      
      public void setName(String name) {
            this.name = name;
      }


     // Fill your code here
}

 

class book extends product {
      private String author;
      private String publisher;

      public book(String name, String price, String publisher, String author) {
            setName(name);
            setPrice(price);
            this.publisher = publisher;
            this.author = author;
      }
      
      
      public String getPublisher() {
            return publisher;
      }
      public String getAuthor() {
            return author;
      }

      // Fill your code here
} // book

 

class cd extends product {
      public cd(String name, String price, String artist) {
            setName(name);
            setPrice(price);
            this.artist = artist;
      }

      private String artist;

      public String getArtist() {
            return artist;
      }      

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