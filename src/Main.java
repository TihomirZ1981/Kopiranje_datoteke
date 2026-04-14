import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Unesite putanju do originalne datoteke:");
        String izvorPutanja = input.nextLine();

        System.out.println("Unesite putanju i naziv destinacijske datoteke:");
        String destinacijaPutanja = input.nextLine();

        File izvor = new File(izvorPutanja);
        File destinacija = new File(destinacijaPutanja);

        if (!izvor.exists()) {
            System.out.println("Izvorna datoteka ne postoji.");
            return;
        }

        if (destinacija.exists()) {
            System.out.println("Datoteka već postoji. Želite li je prepisati (D/N)?");
            String odgovor = input.nextLine();

            if (!odgovor.equalsIgnoreCase("D")) {
                System.out.println("Unesite novu putanju datoteke:");
                destinacijaPutanja = input.nextLine();
                destinacija = new File(destinacijaPutanja);
            }
        }

        try (FileInputStream ulaz = new FileInputStream(izvor);
             FileOutputStream izlaz = new FileOutputStream(destinacija)) {

            int bajt;

            while ((bajt = ulaz.read()) != -1) {
                izlaz.write(bajt);
            }

            System.out.println("Kopiranje završeno.");

        } catch (IOException e) {
            System.out.println("Greška: " + e.getMessage());
        }

        if (destinacija.exists()) {
            System.out.println("Kopija je uspješno napravljena.");
        }

        System.out.println("Želite li izbrisati kopiju datoteke (D/N)?");
        String odgovor = input.nextLine();

        if (odgovor.equalsIgnoreCase("D")) {
            if (destinacija.exists() && destinacija.delete()) {
                System.out.println("Datoteka obrisana.");
            } else {
                System.out.println("Datoteka se ne može obrisati.");
            }
        }

        input.close();
    }
}