package MP06.Ip.Richarte.Raul.Jouman.MP06.UF04.Java.Acces.Dades.PostgreSQL;
import java.sql.*;
import java.util.Scanner;
public class VehicleCRUD {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("Menú vehicles");
            System.out.println("[1] Crear nou vehicle");
            System.out.println("[2} Llistar vehicles actuals");
            System.out.println("[3] Actualitzar un vehicle");
            System.out.println("[4] Eliminar un vehicle");
            System.out.println("[5] Sortir");
            System.out.print("Opcio escullida: ");
            int opcio = Integer.parseInt(sc.nextLine());
            if (opcio == 1) {
                crearVehicle();
            } else if (opcio == 2) {
                llistaVehicles();
            } else if (opcio == 3) {
                actualitzarVehicle();
            } else if (opcio == 4) {
                eliminarVehicle();
            } else if (opcio == 5) {
                System.out.println("Adeuu");
                break;
            } else {
                System.out.println("Error, opcio no valida");
            }
        }
    }
    public static void crearVehicle() {
        System.out.print("Introdueix la marca: ");
        String marca = sc.nextLine();
        System.out.print("Introdueix el model: ");
        String model = sc.nextLine();
        System.out.print("Introdueix la capacitat del maleter: ");
        int capacitat = Integer.parseInt(sc.nextLine());
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO vehicles (marca, model, capacitat_maleter) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, marca);
            ps.setString(2, model);
            ps.setInt(3, capacitat);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("El vehicle s'ha creat");
            } else {
                System.out.println("Hi ha hagut un error");
            }
        } catch (SQLException ex) {
            System.out.println("Error en crear el nou vehicle: " + ex.getMessage());
        }
    }
    public static void llistaVehicles() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles")) {
            System.out.println("Llista de vehicles actuals");
            while (rs.next()) {
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String model = rs.getString("model");
                int capacitat = rs.getInt("capacitat_maleter");
                System.out.println("ID: " + id + " || Marca: " + marca + " || Model: " + model + " || Capacitat: " + capacitat);
            }
        } catch (SQLException ex) {
            System.out.println("Error en llistar els vehicles: " + ex.getMessage());
        }
    }
    public static void actualitzarVehicle() {
        System.out.print("Introdueix l'ID del vehicle a actualitzar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Introdueix la nova marca: ");
        String nuevaMarca = sc.nextLine();
        System.out.print("Introdueix el nou model: ");
        String nuevoModel = sc.nextLine();
        System.out.print("Introdueix la nova capacitat del maleter: ");
        int nuevaCapacitat = Integer.parseInt(sc.nextLine());
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE vehicles SET marca = ?, model = ?, capacitat_maleter = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevaMarca);
            ps.setString(2, nuevoModel);
            ps.setInt(3, nuevaCapacitat);
            ps.setInt(4, id);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("El vehicle s'actualitzat correctament.");
            } else {
                System.out.println("No s'ha pogut actualitzar el vehicle.");
            }
        } catch (SQLException ex) {
            System.out.println("Error en actualitzar vehicle: " + ex.getMessage());
        }
    }
    public static void eliminarVehicle() {
        System.out.print("Introdueix l'ID del vehicle a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM vehicles WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Vehicle eliminat correctament.");
            } else {
                System.out.println("No s'ha pogut eliminar el vehicle.");
            }
        } catch (SQLException ex) {
            System.out.println("Error en eliminar vehicle: " + ex.getMessage());
        }
    }
}
