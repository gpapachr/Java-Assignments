abstract class Animal {

      String name;
      int animals = 0;

      public Animal(String n) {
            name = n;
            animals++;
      }

      public abstract void speak();

      public int numberOfAnimals() {
            return animals;
      }

} // Animal 

 

class Dog extends Animal {

      String sound = "woof";
      int dogs = 0;

       

} //Dog 

 

class Cat extends Animal {

      String sound = "miaou";
      int cats = 0;

     
	  
} // Cat 

 

public class ex27 {

    public static void main(String[] args) {

        Animal[] animal = { new Cat("stella"), new Cat("ziggy"), new Dog("azor") };

        System.out.println("cats: "+Cat.numberOfCats());
        System.out.println("dogs: "+Dog.numberOfDogs());
        System.out.println("animals: "+Animal.numberOfAnimals());

        for ( int i = 0; i < animal.length; i++ )
            animal[i].speak();
    }
}