import java.util.Scanner;

class Patient {
    String name;
    int age;
    String disease;

    Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    void display() {
        System.out.println("\n--- Patient Details ---");
        System.out.println("Name    : " + name);
        System.out.println("Age     : " + age);
        System.out.println("Disease : " + disease);
    }
}

public class HospitalManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== HOSPITAL MANAGEMENT SYSTEM =====");

        System.out.print("Enter patient name: ");
        String name = sc.nextLine();

        System.out.print("Enter patient age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter disease: ");
        String disease = sc.nextLine();

        Patient p = new Patient(name, age, disease);

        p.display();

        System.out.println("\nPatient registered successfully!");

        sc.close();
    }
}
